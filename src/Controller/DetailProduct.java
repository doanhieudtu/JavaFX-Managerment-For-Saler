package Controller;

import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import Entity.CategoryListViewCell;
import Entity.DetailProductTableViewCell;
import Entity.DonGiaXuat;
import Entity.LoaiHang;
import Entity.MatHang;
import Entity.MatHang_ChiNhanh;
import Entity.NhaCungCap;
import Service.ProductService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class DetailProduct implements ControlledScreen, Initializable {
	private ScreenController myController;
	private ProductService productService;
	ObservableList<LoaiHang> categoryObservable;
	@FXML
	ListView<LoaiHang> listview_category;
	
	@FXML
	TableView<DetailProductTableViewCell> table_detail;
	
	@FXML
	TableColumn<DetailProductTableViewCell, SimpleStringProperty> col_id
	,col_inventory,col_having
	,col_price,col_date,col_expiry,col_adress ;
	@FXML
	TableColumn<DetailProductTableViewCell,MatHang> col_inform;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*fill data category for listview*/
		categoryObservable= FXCollections.observableArrayList();
		categoryObservable.addAll(getCategory());
		listview_category.setItems(categoryObservable);
		listview_category.setCellFactory(new Callback<ListView<LoaiHang>, ListCell<LoaiHang>>() {
			
			@Override
			public ListCell<LoaiHang> call(ListView<LoaiHang> param) {
				// TODO Auto-generated method stub
				return new CategoryListViewCell();
			}
		});
		listview_category.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LoaiHang>() {

			@Override
			public void changed(ObservableValue<? extends LoaiHang> observable, LoaiHang oldValue, LoaiHang newValue) {
				// TODO Auto-generated method stub
				productService= new ProductService();
				table_detail.getItems().clear();
				List<MatHang_ChiNhanh> ls= new ArrayList<>();
				ls= productService.getDetailproduct(String.valueOf(newValue.getMaloai()));
				List<DetailProductTableViewCell> result= new ArrayList<>();
				if(ls!=null) {
					ls.forEach(item->{
						DetailProductTableViewCell dp= new DetailProductTableViewCell();
						dp.setMa(item.getMatHang().getMahang());
						dp.setMathang(item.getMatHang());
						dp.setSlton(String.valueOf(item.getSoLuongTon()));
						dp.setSlhaving(String.valueOf(item.getSoLuongDangBan()));
						DonGiaXuat donDiaXuat=productService.getDonGiaXuat(String.valueOf(item.getMatHang().getMahang()));
						Locale locaeVN= new Locale("vi", "VN");
						NumberFormat vn= NumberFormat.getInstance(locaeVN);
						dp.setGiaban(String.valueOf(vn.format(donDiaXuat.getDonGia()))+" vnđ");
						SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
						dp.setNgaysx(sdf.format(item.getNgaysanxuat()));
						dp.setHansd(String.valueOf(item.getHansudung()+" ngày"));
						NhaCungCap ncc= new NhaCungCap();
						ncc= productService.getNhaCCap(item.getMatHang().getMahang());
						dp.setXuatxu(ncc.getTencongty());
						table_detail.getItems().add(dp);
						table_detail.refresh();
					});
				}
			}
		});
		/*fill data category for listview*/
		col_id.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("ma"));
		col_adress.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("xuatxu"));
		col_date.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("ngaysx"));
		col_having.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("slhaving"));
		col_inventory.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("slton"));
		col_price.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("giaban"));
		col_expiry.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, SimpleStringProperty>("hansd"));
		col_inform.setCellValueFactory(new PropertyValueFactory<DetailProductTableViewCell, MatHang>("mathang"));
		col_inform.setCellFactory(new Callback<TableColumn<DetailProductTableViewCell,MatHang>, TableCell<DetailProductTableViewCell,MatHang>>() {
			
			@Override
			public TableCell<DetailProductTableViewCell, MatHang> call(TableColumn<DetailProductTableViewCell, MatHang> param) {
				// TODO Auto-generated method stub
				TableCell<DetailProductTableViewCell, MatHang> cell= new TableCell<DetailProductTableViewCell, MatHang>() {
					@Override
					protected void updateItem(MatHang arg0, boolean arg1) {
						if(arg0!=null && !arg1) {
							Image image= new Image(getClass().getResourceAsStream("../Resoucre/image/"+arg0.getHinhAnh()));
							ImageView imageView= new ImageView(image);
							imageView.setFitWidth(100);
							imageView.setFitHeight(80);
							Text name= new Text();
							name.setText(arg0.getTenhang());
							VBox content= new VBox(imageView, name);
							setGraphic(content);
						}
					}
				};
				return cell;
				};
		});
	}

	@Override
	public void setScreenParent(ScreenController screenPage) {
		// TODO Auto-generated method stub
		myController= screenPage;	
	}
	public List<LoaiHang> getCategory(){
		productService= new ProductService();
		List<LoaiHang> ls= new ArrayList<>();
		ls= productService.getCategory();
		return ls;
	
	}
}
