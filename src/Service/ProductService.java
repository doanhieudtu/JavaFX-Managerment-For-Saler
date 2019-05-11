package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import Client.Client;
import Entity.DonGiaXuat;
import Entity.ItemBill;
import Entity.LoaiHang;
import Entity.MatHang_ChiNhanh;
import Entity.NhaCungCap;
import Request.MyRequest;
import Request.MyRequest.METHOD;
import javafx.scene.control.ListCell;

public class ProductService {
	private static final String URL_GET_COST_OUT="http://localhost:8080/mat-hang/don-gia-xuat";
	private static final String URL_GET_CATEGORY="http://localhost:8080/mat-hang/danh-muc";
	private static final String URL_GET_DETAIL_PRODUCT="http://localhost:8080/loai-hang/mat-hang-chi-nhanh";
	private static final String URL_GET_DISTRIBUTOR="http://localhost:8080/loai-hang/nha-cung-cap";
	private static final String PARAM_ID_BRANCH="idbranch";
	private static final String PARAM_ID_CATEGORY="idcategory";
	private static final String PARAM="idProduct";
	public DonGiaXuat getDonGiaXuat(String idProduct) {
		if(!idProduct.isEmpty()) {
			MyRequest request= new MyRequest.Builder(METHOD.GET,URL_GET_COST_OUT).build();
			Map<String,String> map= new HashMap<>();
			map.put(PARAM, idProduct);
			request.setParams(map);
			Response respone= Client.send(request);
			if(respone.getStatus()==200)
				return (DonGiaXuat)respone.readEntity(DonGiaXuat.class);
		}
		return null;
	}
	public List<LoaiHang> getCategory(){
		MyRequest myRequest= new MyRequest.Builder(METHOD.GET, URL_GET_CATEGORY).build();
		try {
			Response respone= Client.send(myRequest);
			List<LoaiHang> ls= new ArrayList<>();
			ls=(List<LoaiHang>) respone.readEntity(new GenericType<List<LoaiHang>>() {});
			return ls;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<MatHang_ChiNhanh> getDetailproduct(String idcategoty){
		if(!idcategoty.isEmpty()) {
			List<MatHang_ChiNhanh> result= new ArrayList<>();
			MyRequest myRequest= new MyRequest.Builder(METHOD.POST, URL_GET_DETAIL_PRODUCT).build();
			Map<String,String> mapParams= new HashMap<>();
			mapParams.put(PARAM_ID_BRANCH, Client.branch.toString() );
			mapParams.put(PARAM_ID_CATEGORY, idcategoty);
			myRequest.setParams(mapParams);
			try {
				Response response= Client.send(myRequest);
				result= response.readEntity(new GenericType<List<MatHang_ChiNhanh>>() {});
				return result;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	public NhaCungCap getNhaCCap(String id_mahang) {
		if(!id_mahang.isEmpty()) {
			NhaCungCap result= null;
			MyRequest myRequest= new MyRequest.Builder(METHOD.POST, URL_GET_DISTRIBUTOR).build();
			Map<String, String> mapParams= new HashMap<>();
			mapParams.put(PARAM, id_mahang);
			myRequest.setParams(mapParams);
			try {
				Response response= Client.send(myRequest);
				result= new NhaCungCap();
				result= response.readEntity(NhaCungCap.class);
				return result;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
