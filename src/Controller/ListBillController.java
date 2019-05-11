package Controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import Entity.BillListCell;
import Entity.ChiTietDonHang;
import Entity.DonGiaXuat;
import Entity.DonHang;
import Entity.ProductTableViewCell;
import Service.DonHangService;
import Service.ProductService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class ListBillController implements Initializable, ControlledScreen{
	ScreenController myController;
	@Override
	public void setScreenParent(ScreenController screenPage) {
		// TODO Auto-generated method stub
		myController= screenPage;
	}
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
	ListView<DonHang> list_bill= new ListView<DonHang>();
	@FXML
	TableView<ProductTableViewCell> table_detail;
	@FXML
	TextField txt_vat,txt_ck,txt_time,txt_creater,txt_total;
	@FXML
	TextArea txt_note;
	private DonHangService donHangService;
	ObservableList<DonHang> donHangObserver ;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colSTT.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("sott"));
		colName.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("tenMatHang"));
		colDVT.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("dvt"));
		colPrice.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("giaBan"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<ProductTableViewCell, String>("soLuong"));
		colQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
		// TODO Auto-generated method stub
		/*load all data products for listview*/
			donHangObserver= FXCollections.observableArrayList();
			donHangObserver.addAll(getDonHangDate());
			list_bill.setItems(donHangObserver);
			/*set cell for list cell*/
			list_bill.setCellFactory(new Callback<ListView<DonHang>, ListCell<DonHang>>() {
				
				@Override
				public ListCell<DonHang> call(ListView<DonHang> param) {
					// TODO Auto-generated method stub
					return new BillListCell();
				}
			});
			/*set cell for list cell*/
			ProductService prs= new ProductService();
			list_bill.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DonHang>() {
				
				@Override
				public void changed(ObservableValue<? extends DonHang> observable, DonHang oldValue, DonHang newValue) {
						// TODO Auto-generated method stub
					data.clear();
					List<ChiTietDonHang> lsBill= new ArrayList<>();
					lsBill= donHangService.getDetailBill(String.valueOf(newValue.getMadonhang()));
					double total=0;
					int i=1;
					for(ChiTietDonHang item : lsBill){
						ProductTableViewCell pr= new ProductTableViewCell();
						pr.setSott(String.valueOf(String.valueOf(i)));
						pr.setTenMatHang(String.valueOf(item.getMatHang_chiNhanh().getMatHang().getTenhang()));
						pr.setDvt(String.valueOf(item.getMatHang_chiNhanh().getMatHang().getDonvitinh()));
						pr.setSoLuong(String.valueOf(item.getSoLuong()));
						DonGiaXuat donDiaXuat=prs.getDonGiaXuat(item.getMatHang_chiNhanh().getMatHang().getMahang());
						pr.setGiaBan(String.valueOf(donDiaXuat.getDonGia()));
						data.add(pr);
						total +=(item.getSoLuong()*donDiaXuat.getDonGia());
						i++;
					}
					table_detail.setItems(data);
					table_detail.refresh();
					txt_vat.clear();
					txt_vat.setText(newValue.getVat()+" %");
					txt_ck.setText(newValue.getVat()+" %");
					txt_creater.setText(newValue.getNguoiDung().getHoTen());
					txt_note.setText(newValue.getGhiChu());
					txt_time.setText(newValue.getNgaylap().toString());
					Locale localeVN= new Locale("vi", "VN");
					NumberFormat vn= NumberFormat.getInstance(localeVN);
					txt_total.setText(vn.format(total)+" VNĐ");
				}
				
			});
	}
	private ObservableList<ProductTableViewCell> data= FXCollections.observableArrayList();
	public List<DonHang> getDonHangDate(){
		donHangService= new DonHangService();
		return  donHangService.getBillintDate();
	}
}
