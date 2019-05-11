package util;

import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;


public class MapUtil {

	public static MultivaluedMap<String, Object> toMultivaluedMap(Map<String,String> map){
		MultivaluedMap<String, Object> mvm = new MultivaluedHashMap<>();
		map.entrySet().forEach(entry -> mvm.putSingle(entry.getKey(), entry.getValue()));
		return mvm;
	}

}
