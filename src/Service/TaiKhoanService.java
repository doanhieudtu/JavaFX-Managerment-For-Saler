package Service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import Client.Client;
import Entity.ChiNhanh;
import Entity.NguoiDung;
import Entity.TaiKhoan;
import Request.MyRequest;
import Request.MyRequest.METHOD;

public class TaiKhoanService {
	static final String URL_DANG_NHAP="http://localhost:8080/tai-khoan/dang-nhap";
	static final String URL_GET_NGUOI_DUNG="http://localhost:8080/tai-khoan/nguoi-dung";
	static final String URL_GET_CHI_NHANH="http://localhost:8080/tai-khoan/chi-nhanh";
	static final String PARAM_MA_TK="maTK";
	static final String PARAM_TEN_TK="tenTK";
	static final String PARAM_MAT_KHAU="matKhau";
	public 	TaiKhoan DangNhap(String uername, String pass) {
		int k=0;
		try {
			Map<String,String> mapParams= new HashMap<String, String>();
			mapParams.put(PARAM_TEN_TK, uername);
			mapParams.put(PARAM_MAT_KHAU, pass);
			MyRequest request= new MyRequest.Builder(METHOD.POST,URL_DANG_NHAP).build();
			request.setParams(mapParams);
			Response response=Client.send(request);
			TaiKhoan result= response.readEntity(TaiKhoan.class);
			if(response.getStatus()==200) return result ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public ChiNhanh getChiNhanh() {
		MyRequest myRequest= new MyRequest.Builder(METHOD.POST,URL_GET_CHI_NHANH).build();
		Map<String,String> mapParams= new HashMap<String, String>();
		try {
			mapParams.put(PARAM_MA_TK, Client.id.toString());
			myRequest.setParams(mapParams);
			Response response= Client.send(myRequest);
			ChiNhanh branch=response.readEntity(ChiNhanh.class);
			return branch;
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return null;
	}
	public NguoiDung getNguoiDung() {
		MyRequest myRequest= new MyRequest.Builder(METHOD.POST,URL_GET_NGUOI_DUNG).build();
		Map<String,String> mapParams= new HashMap<String, String>();
		try {
			mapParams.put(PARAM_MA_TK, Client.id.toString());
			myRequest.setParams(mapParams);
			Response response= Client.send(myRequest);
			return response.readEntity(NguoiDung.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public void logOut() {
		Client.name.delete(0, Client.name.length());
		Client.pass.delete(0, Client.pass.length());
		Client.branch.delete(0, Client.branch.length());
		Client.id.delete(0, Client.id.length());
		Client.username.delete(0, Client.username.length());
		Client.address.delete(0, Client.address.length());
	}
}
