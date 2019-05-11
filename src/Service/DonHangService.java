package Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Client.Client;
import Entity.ChiTietDonHang;
import Entity.DonHang;
import Entity.ItemBill;
import Request.MyRequest;
import Request.MyRequest.METHOD;

public class DonHangService {
	private static final String URL_CREATE_BILL="http://localhost:8080/don-hang/tao-moi";
	private static final String URL_DETAIL_BILL="http://localhost:8080/don-hang/chi-tiet-don-hang";
	private static final String PARAM_STRING_ID="idProduct";
	private static final String PARAM_STRING_COUNT="countProduct";
	private static final String  URL_GET_BILL_DATE="http://localhost:8080/don-hang/danh-sach-trong-ngay";
	private static final String PARAM_VAT="vat";
	private static final String PARAM_ID_BRANCH="idbranch";
	private static final String PARAM_ID_BILL="idBill";
	private static final String PARAM_CK="chietkhau";
	private static final String PARAM_NOTE="ghichu";
	private static final String PARAM_CRAETER="manv";
	
	public int SaveBill(String idProduct, String countProduct, String manv
		,String chietkhau,  String ghichu,  String vat  ) throws IOException{
			MyRequest myRequest= new MyRequest.Builder(METHOD.POST, URL_CREATE_BILL).build();
			Map<String, String> params= new HashMap<String, String>();
			params.put(PARAM_STRING_ID, idProduct);
			params.put(PARAM_STRING_COUNT, countProduct);
			params.put(PARAM_CK, chietkhau);
			params.put(PARAM_NOTE, ghichu);
			params.put(PARAM_VAT, vat);
			params.put(PARAM_CRAETER, manv);
			myRequest.setParams(params);
			Response response= Client.send(myRequest);
			int result= response.readEntity(Integer.class);
		return result;
	}
	public List<DonHang> getBillintDate() {
		MyRequest myRequest= new MyRequest.Builder(METHOD.POST, URL_GET_BILL_DATE).build();
		Map<String,String> mapParams= new HashMap<>();
		mapParams.put(PARAM_ID_BRANCH,Client.branch.toString());
		myRequest.setParams(mapParams);
		try {
			Response response= Client.send(myRequest);
			List<DonHang> result= new ArrayList<>();
			result=(List<DonHang>)response.readEntity(new GenericType<List<DonHang>>() {});
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<ChiTietDonHang> getDetailBill(String idBill){
		MyRequest myRequest= new MyRequest.Builder(METHOD.POST, URL_DETAIL_BILL).build();
		Map<String, String> mapPrams= new HashMap<>();
		mapPrams.put(PARAM_ID_BILL, idBill);
		myRequest.setParams(mapPrams);
		try {
			Response response= Client.send(myRequest);
			return (List<ChiTietDonHang>)response.readEntity(new GenericType<List<ChiTietDonHang>>() {});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
