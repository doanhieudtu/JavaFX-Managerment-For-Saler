package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Client.Client;
import Entity.ChiNhanh;
import Entity.NguoiDung;
import Entity.TaiKhoan;
import Service.TaiKhoanService;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable, ControlledScreen {
	ScreenController myController;
	@Override
	public void setScreenParent(ScreenController screenPage) {
		myController=screenPage;
	}
	private TaiKhoanService taiKhoanService;
	
	@FXML
	TextField txtTenDangNhap;
	
	@FXML
	TextField txtMatKhau;
	
	@FXML
	Button btnDangNhap;
	
	@FXML
	ProgressIndicator progresDangNhap;
	
	@FXML
	ProgressBar progressLoadingData;
	
	@FXML
	Button btnDong;
	
	@FXML
	VBox vbox;
	
	@FXML
	HBox hbox;
	
	public void DangNhap() {
		taiKhoanService= new TaiKhoanService();
		taiKhoanService.logOut();
		if(txtMatKhau.getText().isEmpty()||txtTenDangNhap.getText().isEmpty()) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setTitle("Thông Báo");
			alert.setHeaderText("Lỗi Người Dùng");
			alert.setContentText("Không được để trống Tên Đăng Nhập và Mật Khẩu.");
			alert.showAndWait();
		}
		else {
			taiKhoanService= new TaiKhoanService();
			TaiKhoan tk= new TaiKhoan();
			Client.name.append(txtTenDangNhap.getText());
			Client.pass.append(txtMatKhau.getText());
			tk=taiKhoanService.DangNhap(txtTenDangNhap.getText(), txtMatKhau.getText());
			if(tk==null)
			{
				Alert alert= new Alert(AlertType.ERROR);
				alert.setTitle("Thông Báo");
				alert.setHeaderText("Đăng nhập thất bại");
				alert.setContentText("Sai tài khoản hoặc mật khẩu");
				alert.showAndWait();
				taiKhoanService.logOut();
				txtMatKhau.clear();
				txtTenDangNhap.clear();
			}
			else {
				Client.id.append(tk.getMatk());
				taiKhoanService= new TaiKhoanService();
				NguoiDung nd= new NguoiDung();
				nd=taiKhoanService.getNguoiDung();
				Client.username.append(nd.getHoTen());
				ChiNhanh br= new ChiNhanh();
				br= taiKhoanService.getChiNhanh();
				Client.branch.append(br.getMachinhanh());
				Client.address.append(br.getDiachi());
				LoadIndex();
			}
		}
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		vbox.getStyleClass().add("vbox");
		btnDangNhap.getStyleClass().add("btn");
		btnDong.getStyleClass().add("btn");
		btnDangNhap.setOnKeyPressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				DangNhap();
			}
		});
		btnDong.setOnKeyPressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				DongForm();
			}
			
		});
	}
	public void DongForm() {
		Stage stag= (Stage) btnDong.getScene().getWindow();
		stag.close();
	}
	public void LoadIndex() {
		myController.loadScreen(ScreenFramework.screenIDindex,ScreenFramework.screenIndex);
		myController.setScreen(ScreenFramework.screenIDindex);
		Stage stag= (Stage) btnDong.getScene().getWindow();
		stag.setMaximized(true);
	    }
}
