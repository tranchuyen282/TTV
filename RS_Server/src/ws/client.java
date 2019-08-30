package ws;

import java.awt.PageAttributes.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class client {
	static final String REST_URI = "http://localhost:8080/RS_Server/GetPhone/TTV/getPhone";
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(REST_URI);		
		ClientResponse response = service.accept("").get(ClientResponse.class);
		if(response.getStatus() != 200) {
			throw new RuntimeException("Failed: HTTP error code: " + response.getStatus());
		}
		String phone = response.getEntity(String.class);
		System.out.println(phone);
	}
	
}
