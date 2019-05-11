package Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import Client.Client;
import Request.MyRequest;
import Request.MyRequest.METHOD;

public class IndexService {
	public static final String URL_UPDATE_IMAGE="http://localhost:8080/mat-hang-chi-nhanh/dong-bo-anh";
	public static final String URL_DOWLOAD_FILE="http://localhost:8080/image/downloadFile";
	public static final String PARAM_NAME_FILE="fileName";
	public ArrayList<String> isUpdateImage(String idBranch) {
		ArrayList<String> listFileName= new ArrayList<>();
		MyRequest myRequest= new MyRequest.Builder(METHOD.GET, URL_UPDATE_IMAGE).build();
		
		Response response=Client.client.target(URL_UPDATE_IMAGE).register(HttpAuthenticationFeature
				.basic(Client.name.toString(), Client.pass.toString())).queryParam("maChinhanh",idBranch)
				.request(MediaType.APPLICATION_JSON)
				.get();
		listFileName =(ArrayList<String>) response.readEntity(new GenericType<ArrayList<String>>() {});
		if(listFileName.size()>0) return listFileName;
		return null;
	}
	public void DowloadFile(ArrayList<String> listFilename) {
		// TODO Auto-generated method stub
		listFilename.forEach(item-> {
			MyRequest myRequest= new MyRequest.Builder(METHOD.GET, URL_DOWLOAD_FILE).build();
			Response response=Client.client.target(URL_DOWLOAD_FILE)
					.register(HttpAuthenticationFeature.basic(Client.name.toString(), Client.pass.toString()))
					.queryParam(PARAM_NAME_FILE,item)
					.request(MediaType.APPLICATION_OCTET_STREAM_TYPE)
					.get();
			InputStream in=null;
			in=response.readEntity(InputStream.class);
			System.out.println(response.getMediaType().toString());
			String path= getClass().getResource("../Resoucre/image/").getPath()+item;
				System.out.println(getClass().getResource("../Resoucre/image/").getPath());
	
			if(in!=null) {
				File file= new File(path);
				System.out.println("file: "+ file.toPath().toString());
				try {
					Files.copy(in, file.toPath(),StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
