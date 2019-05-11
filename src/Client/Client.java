package Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;

import Request.MyRequest;
import util.MapUtil;

public class Client {
	public static final StringBuffer name= new StringBuffer() ;
	public static final StringBuffer username= new StringBuffer() ;
	public static  final StringBuffer pass= new StringBuffer() ;
	public static  final StringBuffer branch= new StringBuffer() ;
	public static  final StringBuffer id= new StringBuffer() ;
	public static  final StringBuffer address= new StringBuffer() ;
	public static final javax.ws.rs.client.Client client= (javax.ws.rs.client.Client) ClientBuilder
			.newClient(new ClientConfig()
			.register(LoggingFilter.class));
	public static Response send(MyRequest request){
		//set params cho resquest
		WebTarget webTarget = ((javax.ws.rs.client.Client) client).target(request.getUrl());
		try {
			if(request.getParams()!=null) {
				for (String key  : request.getParams().keySet()) {
					webTarget=webTarget.queryParam(key, request.getParams().get(key));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Invocation.Builder invocationBuilder =  webTarget
												.register(HttpAuthenticationFeature.basic(name.toString(),pass.toString()))
												.request()
												.accept(request.getAccept())
												.headers(MapUtil.toMultivaluedMap(request.getHeaders()));
	Response response = invocationBuilder.method(request.getMethod());
	return response;
	}
	
}
