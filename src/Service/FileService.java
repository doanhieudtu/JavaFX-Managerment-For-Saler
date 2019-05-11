package Service;

import java.io.File;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import Entity.Authentication;

public class FileService {
	final static String POST_URL ="http://localhost:8080/image/uploadFile";
	
	public Response postFile(String path) {
		System.out.println("Begin /POST request!");
		 javax.ws.rs.client.Client client = ClientBuilder.newBuilder()
		            .register(MultiPartFeature.class)
		            .register(HttpAuthenticationFeature.basic(Authentication.getName(),Authentication.getPass()))
		            .build();
		        WebTarget webTarget = client.target(POST_URL);		        
		        MultiPart multiPart = new MultiPart();
		        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

		        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
		            new File(path),
		            MediaType.APPLICATION_OCTET_STREAM_TYPE);
		        multiPart.bodyPart(fileDataBodyPart);
		        Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
		            .post(Entity.entity(multiPart, multiPart.getMediaType()));
		            ;
		            return response;
	}
}
