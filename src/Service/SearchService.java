package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import Client.Client;
import Entity.MatHang_ChiNhanh;
import Request.MyRequest;
import Request.MyRequest.METHOD;

public class SearchService {
	private static final String URL_SERCH_PRODUCT="http://localhost:8080/mat-hang-chi-nhanh/tim-kiem";
	private static final String PARAM_ID_BRANCH="maChinhanh";
	private static final String PARAM_CONTENT="noiDung";
	public ArrayList<MatHang_ChiNhanh>Seacrh(String noiDung){
		if(!noiDung.isEmpty()) {
			try {
				MyRequest request= new MyRequest.Builder(METHOD.POST,URL_SERCH_PRODUCT).build();
				Map<String, String> map= new HashMap<String, String>();
				map.put(PARAM_ID_BRANCH, "1");
				map.put(PARAM_CONTENT, noiDung);
				request.setParams(map);
				Response response= Client.send(request);
				ArrayList<MatHang_ChiNhanh> ls= new ArrayList<>();
				ls=(ArrayList<MatHang_ChiNhanh>)response.readEntity(new GenericType<List<MatHang_ChiNhanh>> (){});
				if(ls!= null)
					return ls;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
}
