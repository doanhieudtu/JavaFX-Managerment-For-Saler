package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.sound.midi.Sequence;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import Client.Client;
import Entity.ChiNhanh;
import Entity.DonGiaXuat;
import Entity.MatHang_ChiNhanh;
import Entity.NguoiDung;
import Entity.ProducListCell;
import Entity.ProductTableViewCell;
import Request.MyRequest;
import Request.MyRequest.METHOD;
import Service.DonHangService;
import Service.IndexService;
import Service.ProductService;
import Service.SearchService;
import Service.TaiKhoanService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class IndexController implements ControlledScreen,  Initializable{
	private IndexService indexService;
	private TaiKhoanService taiKhoanService;
	static final String URL_LIST_PRODUCT="http://localhost:8080/mat-hang-chi-nhanh/danh-sach";
	static final String PARAM_ID_BRANCH="maChinhanh";
	private ScreenController myController;
	SearchService searchService= new SearchService();
	@FXML
	TextField txt_content,txt_id_vat,txt_ck,txt_create_date,txt_total,txt_remain,txt_begin;
	
	@FXML
	Label lbBranch,lbStaff;
	
	@FXML
	TextArea txt_note;
	
	ObservableList<MatHang_ChiNhanh> matHangObserver ;
	
	@FXML
	ListView<MatHang_ChiNhanh> listViewProduct= new ListView<>();
	
	@FXML
	Button btnSearch;
	List<MatHang_ChiNhanh> lsTK= new ArrayList<>();
	@FXML
	Button btnReport,btnAll;
	
	@FXML
	Button btn_delete_item,btnDelete,btnCreate,btnSave,btnReload,btnLogout;
	
	@FXML
	Button btnCreateBill;
	@FXML
	Button btnBill ;
	
	@FXML
	TableView<ProductTableViewCell> tableProduct;
	
	@FXML
	TableColumn<ProductTableViewCell, String> colSTT;
	
	@FXML
	TableColumn<ProductTableViewCell, String> colName;
	
	@FXML
	TableColumn<ProductTableViewCell, String> colDVT;
	
	@FXML
	TableColumn<ProductTableViewCell, String> colQuantity;
	
	@FXML
	TableColumn<ProductTableViewCell, String> colPrice;
	
	@FXML
	TableColumn<ProductTableViewCell, Boolean> colDelete;

	public void Search() {
		String noiDung= txt_content.getText();
		if(!noiDung.isEmpty()) {
			List<MatHang_ChiNhanh> ls= new ArrayList<>();
			ls= searchService.Seacrh(noiDung);
			if(ls!=null) {
				matHangObserver.setAll(ls);
			}
		}
		else{
		Alert alert= new Alert(AlertType.WARNING);
		alert.setTitle("Chú ý");
		alert.setHeaderText("Nội dung tìm kiếm không hợp lệ");
		alert.setContentText("Cần nhập nội dung tìm kiếm ");
		alert.showAndWait();
		}
	}
	public void onTextChanged(){
		txt_content.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String noiDung= newValue;
				System.out.println(newValue);
				if(!noiDung.isEmpty()) {
					List<MatHang_ChiNhanh> ls= new ArrayList<>();
					ls= searchService.Seacrh(noiDung);
					if(ls!=null) {
						matHangObserver.clear();
						matHangObserver.setAll(ls);
					}
				}
			}
			
		});
	}
	public void deleteItem(ProductTableViewCell index) {
		if(index!=null) {
			Alert alert= new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Xác nhận");
			alert.setHeaderText("Bạn muốn xóa mặt hàng");
			alert.setContentText("nhấn YES để sóa mặt hàng");
			alert.showAndWait();
			if(alert.getResult().equals(ButtonType.OK)) {
				tableProduct.getItems().remove(index);
				Locale locaeVN= new Locale("vi", "VN");
				NumberFormat vn= NumberFormat.getInstance(locaeVN);
				txt_total.setText(vn.format(TongTien())+".000");
			}
		}
	}
	@Override
	public void setScreenParent(ScreenController screenPage) {
		myController= screenPage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*show infor staff*/
			lbStaff.setText(Client.username.toString());
			lbBranch.setText(Client.address.toString());
		/*show infor staff*/
		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		String stringDate=sdf.format(date);
		txt_create_date.setText(stringDate);
		/*load image for button*/
		ImageForButton();
		/*load image for button*/
		data.clear();
		tableProduct.setItems(data);
		/*load all data products for listview*/
		if(txt_content.getText().isEmpty()) {
			matHangObserver= FXCollections.observableArrayList();
			matHangObserver.addAll(lsProduct());
			listViewProduct.setItems(matHangObserver);
			/*set cell for list cell*/
			listViewProduct.setCellFactory(new Callback<ListView<MatHang_ChiNhanh>, ListCell<MatHang_ChiNhanh>>() {
				
				@Override
				public ListCell<MatHang_ChiNhanh> call(ListView<MatHang_ChiNhanh> param) {
					// TODO Auto-generated method stub
					return new ProducListCell();
				}
			});
			/*set cell for list cell*/
		}
		/*load all data products for listview*/
		/*event seting when accseeing element of listview  */
		listViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MatHang_ChiNhanh>() {

			@Override
			public void changed(ObservableValue<? extends MatHang_ChiNhanh> observable, MatHang_ChiNhanh oldValue,
				MatHang_ChiNhanh newValue) {
				Alert alert= new Alert(AlertType.CONFIRMATION);				
				alert.setTitle("Thêm mới mặt hàng");
				alert.setHeaderText("Xác nhận thêm mặt hàng");
				alert.setContentText("Mặt hàng "+ newValue.getMatHang().getTenhang());
				alert.showAndWait();
				if(alert.getResult().equals(ButtonType.OK))
				{
					AddItems(newValue);
				}
			}
		});
		/*event seting when accseeing element of listview  */
		indexService= new IndexService();
		ArrayList<String> listFilename= new ArrayList<>();
		listFilename=indexService.isUpdateImage("1");
		if(listFilename!=null) {
			indexService.DowloadFile(listFilename);
		}
		colSTT.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("sott"));
		colName.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("tenMatHang"));
		colPrice.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("giaBan"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("soLuong"));
		colDVT.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("dvt"));
		colQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
		tableProduct.setEditable(true);
		colQuantity.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<ProductTableViewCell,String>>() {
			@Override
			public void handle(CellEditEvent<ProductTableViewCell, String> event) {
				Alert alert= new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Hướng Dẫn");
				alert.setHeaderText("Nhấn Enter để xác nhận việc thay đổi số lượng.");
				alert.setContentText("Nhấn OK để tắt hộp thoại !!!");
				alert.showAndWait();
			}
		});
		colQuantity.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<ProductTableViewCell,String>>() {
			@Override
			public void handle(CellEditEvent<ProductTableViewCell, String> event) {
				List<MatHang_ChiNhanh> mh= matHangObserver.stream()
						.filter(item->item.getMa_Mathang_chiNhanh()== Integer.parseInt(event.getRowValue().getId()))
						.collect(Collectors.toList());
				if(Integer.parseInt(event.getNewValue())> mh.get(0).getSoLuongDangBan())
				{
					Alert alert= new Alert(AlertType.WARNING);
					alert.setTitle("Cảnh Báo");
					alert.setHeaderText("Số lượng của mặt hàng hiện tại không đủ.");
					alert.setContentText("Nhấn OK để tắt hộp thoại !!!");
					alert.showAndWait();
					((ProductTableViewCell) event.getTableView().getItems()
							.get(event.getTablePosition().getRow()))
							.setSoLuong(String.valueOf(mh.get(0).getSoLuongDangBan()));
				}
				else
				{
					((ProductTableViewCell) event.getTableView().getItems()
							.get(event.getTablePosition().getRow()))
							.setSoLuong(String.valueOf(Integer.parseInt(event.getNewValue())));
					Locale locaeVN= new Locale("vi", "VN");
					NumberFormat vn= NumberFormat.getInstance(locaeVN);
					txt_total.setText(vn.format(TongTien())+".000");
					tableProduct.refresh();
				}
			}
		});
		/*set css(font) cho từng col */
		colSTT.getStyleClass().add("Times New Roman,20");
		colName.getStyleClass().add("Times New Roman,20");
		colDVT.getStyleClass().add("Times New Roman,20");
		colPrice.getStyleClass().add("Times New Roman,20");
		colQuantity.getStyleClass().add("Times New Roman,20");
		/*set css(font) cho từng col */
		/* select row*/
		tableProduct.setRowFactory(value ->{
			TableRow<ProductTableViewCell> row= new TableRow<ProductTableViewCell>();
			row.setOnMouseClicked(click -> {
				if(!row.isEmpty()&& click.getClickCount()==1) {

						rowIndex= tableProduct.getSelectionModel().getSelectedItem();					
				}
			});
			return row;
		});
		/* select row*/
	}
	private ProductTableViewCell rowIndex;
	List<ProductTableViewCell> ls= new ArrayList<>();
	private ObservableList<ProductTableViewCell> data= FXCollections.observableArrayList();
	public void AddItems(MatHang_ChiNhanh matHang) {
		int size= matHang.getSoLuongDangBan();
		List<ProductTableViewCell> ls= new ArrayList<>();
		try {
			ls=data.stream()
					.filter(item-> Integer.parseInt(item.getId())== matHang.getMa_Mathang_chiNhanh())
					.collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(size>0) {
			if(ls.size()>0) {
				ls.get(0).setSoLuong(String.valueOf(Integer.parseInt(ls.get(0).getSoLuong())+1));
				Locale locaeVN= new Locale("vi", "VN");
				NumberFormat vn= NumberFormat.getInstance(locaeVN);
				txt_total.setText(vn.format(TongTien())+".000");
				tableProduct.refresh();
				
			}
			else
			{
				ProductService prs= new ProductService();
				ProductTableViewCell pr= new ProductTableViewCell();
				pr.setId(String.valueOf(matHang.getMa_Mathang_chiNhanh()));
				pr.setTenMatHang(matHang.getMatHang().getTenhang());
				pr.setDvt(matHang.getMatHang().getDonvitinh());
				pr.setSott(String.valueOf(data.size()+1));
				pr.setSoLuong(String.valueOf(1));
				DonGiaXuat donDiaXuat=prs.getDonGiaXuat(matHang.getMatHang().getMahang());
				Locale locaeVN= new Locale("vi", "VN");
				NumberFormat vn= NumberFormat.getInstance(locaeVN);
				System.out.println(donDiaXuat.getDonGia());
				pr.setGiaBan(vn.format(donDiaXuat.getDonGia()).toString());
				pr.setXoa(false);
				data.add(pr);
				tableProduct.refresh();
				txt_total.setText(vn.format(TongTien())+".000");
				System.out.println(data.size());
			}
			
		}
		btn_delete_item.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				deleteItem(rowIndex);
				tableProduct.getItems()
				 .forEach(action->action.setSott(String.valueOf(data.indexOf(action)+1)));
				tableProduct.refresh();
				rowIndex=null;
			}
		});
	}
	@FXML
	public void SaveBill() {
		if(!txt_id_vat.getText().isEmpty()||!txt_ck.getText().isEmpty()) {
			/*create string arr id and count*/
				StringBuilder stringID= new StringBuilder();
				StringBuilder stringCount= new StringBuilder();
				for (ProductTableViewCell productTableViewCell : tableProduct.getItems()) {
					stringID.append(String.valueOf(productTableViewCell.getId())+":");
					stringCount.append(String.valueOf(productTableViewCell.getSoLuong())+":");
				}
			/*create string arr id and count*/
			DonHangService donHangService= new DonHangService();
			try {
				int result = donHangService.SaveBill(stringID.toString(), stringCount.toString()
						, "1", txt_ck.getText()
						, txt_note.getText(), txt_id_vat.getText());
				if(result==1) {
					Alert alert= new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Thông báo");
					alert.setTitle("Lưu hóa đơn");
					alert.setContentText("Lưu hóa đơn thành công");
					alert.showAndWait();
				}
				DeleteBill();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Thông báo");
				alert.setTitle("Lưu hóa đơn");
				alert.setContentText("Lưu hóa đơn thất bại");
				alert.showAndWait();
			}
		}
	}
	public void addAll() {
		tableProduct.getItems().clear();
		lsProduct().forEach(item-> AddItems(item));
	}
	public void logOut() {
		taiKhoanService= new TaiKhoanService();
		taiKhoanService.logOut();
		myController.loadScreen(ScreenFramework.screenIDlogin,ScreenFramework.screenLogin);
		myController.setScreen(ScreenFramework.screenIDlogin);
		
		
	}
	public Float TongTien() {
		float sum = 0;
		for (ProductTableViewCell productTableViewCell : data) {
			sum += (Float.parseFloat(productTableViewCell.getGiaBan())*Integer.parseInt(productTableViewCell.getSoLuong()));
		}
		return sum;
	}
	public void DeleteBill() {
		tableProduct.getItems().clear();
		txt_begin.clear();
		txt_ck.clear();
		txt_id_vat.clear();
		txt_remain.clear();
		txt_total.clear();
		txt_note.clear();
	}
	public void CreateBill() {
		txt_begin.clear();
		txt_ck.clear();
		txt_id_vat.clear();
		txt_remain.clear();
		txt_total.clear();
		txt_note.clear();
	}
	public void bonusVAT() {
		float sum=TongTien()+(TongTien()*Float.parseFloat("0."+txt_id_vat.getText()));
		Locale locaeVN= new Locale("vi", "VN");
		NumberFormat vn= NumberFormat.getInstance(locaeVN);
		txt_total.setText(vn.format(sum)+".000");
	}
	public void subCK() {
		float sum=TongTien()+(TongTien()*Float.parseFloat("0."+txt_id_vat.getText()));
		Locale locaeVN= new Locale("vi", "VN");
		NumberFormat vn= NumberFormat.getInstance(locaeVN);
		txt_total.setText(vn.format(sum-(TongTien()*Float.parseFloat("0."+txt_ck.getText())))+".000");
	}
	public void pay(){
		Locale locaeVN= new Locale("vi", "VN");
		NumberFormat vn= NumberFormat.getInstance(locaeVN);
		txt_remain.setText(vn.format(Float.parseFloat(txt_begin.getText())-(Float.parseFloat(txt_total.getText())))+".000");
	}
	
	public void ImageForButton() {
		btnBill.setText("Hóa Đơn");
		javafx.scene.image.Image imge= new javafx.scene.image.Image(getClass()
				.getResourceAsStream("../Resoucre/image/folder-bills-50.png"));
		btnBill.setGraphic(new ImageView(imge));
		
		btnCreateBill.setText("Tạo Mới");
		javafx.scene.image.Image imgeCreate= new javafx.scene.image.Image(getClass()
				.getResourceAsStream("../Resoucre/image/create-bill48.png"));
		btnCreateBill.setGraphic(new ImageView(imgeCreate));
		
		btnReport.setText("Thông Tin Mặt Hàng");
		javafx.scene.image.Image imgerReport= new javafx.scene.image.Image(getClass()
				.getResourceAsStream("../Resoucre/image/data.png"));
		btnReport.setGraphic(new ImageView(imgerReport));
		javafx.scene.image.Image imgeLook = new javafx.scene.image.Image(getClass()
				.getResourceAsStream("../Resoucre/image/icons8-gsearch-24.png"));
		btnSearch.setGraphic(new ImageView(imgeLook));
		Image imgRemove= new Image(getClass().getResourceAsStream("../Resoucre/image/remove.png"));
		btn_delete_item.setGraphic(new ImageView(imgRemove));
		Image imgDelete= new Image(getClass().getResourceAsStream("../Resoucre/image/delete.png"));
		btnDelete.setGraphic(new ImageView(imgDelete));
		Image imgSave= new Image(getClass().getResourceAsStream("../Resoucre/image/save.png"));
		btnSave.setGraphic(new ImageView(imgSave));
		Image imgCreate= new Image(getClass().getResourceAsStream("../Resoucre/image/add.png"));
		btnCreate.setGraphic(new ImageView(imgCreate));
		Image imgLogout= new Image(getClass().getResourceAsStream("../Resoucre/image/logout.png"));
		btnLogout.setGraphic(new ImageView(imgLogout));

	} 
	public List<MatHang_ChiNhanh> lsProduct(){ 
		MyRequest request= new MyRequest.Builder(METHOD.GET,URL_LIST_PRODUCT).build();
		Map<String, String> param= new HashMap<String, String>();
		param.put(PARAM_ID_BRANCH,Client.branch.toString());
		request.setParams(param);
		Response response= Client.send(request);
		return (List<MatHang_ChiNhanh>) response.readEntity(new GenericType<ArrayList<MatHang_ChiNhanh>>() {});
	}
	public void getListBill() {
		   FXMLLoader fxmlLoader = new FXMLLoader();
	       fxmlLoader.setLocation(getClass().getResource(ScreenFramework.screenListBill));
	        /* 
	         * if "fx:controller" is not set in fxml
	         * fxmlLoader.setController(NewWindowController);
	         */
	       
			try {
					Scene scene = new Scene(fxmlLoader.load(), 848, 536);
			        Stage stage = new Stage();
			        stage.setTitle("Danh Sách Hóa Đơn");
			        stage.setMaxHeight(600);
			        stage.setMaxWidth(844);
			        stage.setScene(scene);
			        stage.show();
			        stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void getDetailProducts() {
		   FXMLLoader fxmlLoader = new FXMLLoader();
	       fxmlLoader.setLocation(getClass().getResource(ScreenFramework.screenDetailProduct));
	        /* 
	         * if "fx:controller" is not set in fxml
	         * fxmlLoader.setController(NewWindowController);
	         */
	       
			try {
					Scene scene = new Scene(fxmlLoader.load());
			        Stage stage = new Stage();
			        stage.setTitle("Thông Tin Chi Tiết Mặt Hàng");
//			        stage.setMaxHeight(600);
//			        stage.setMaxWidth(844);
			        stage.setScene(scene);
			        stage.show();
			        stage.setScene(scene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
