package com.elcuto.service;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class TPPHTTPRequest {
	private static String url ="";
	
	public static HttpResponse sendRequest(RequestData data) throws IOException {
		HttpResponse  response = null;
		//set the url to send request to
			if("mtn".equals(data.getNetwork().toLowerCase())) {
				url = "https://mtn6dcb.elcutoconsult.com/api/3pp";
			}else if("vodafone".equals(data.getNetwork().toLowerCase())) {
				url = "https://vftelenity.elcutoconsult.com/api/3pp";
			}else if("airteltigo".equals(data.getNetwork().toLowerCase())) {
				url = "https://timwewin.elcutoconsult.com/api/3pp";
			}else {
				System.out.println("Network not found: "+data.getNetwork().toLowerCase());
				return null;
			}
	
		//send HTTP request for subscription to be processed
		try {
			Gson gson = new Gson();
			HttpClient httpClient    = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			StringEntity postingString = new StringEntity(gson.toJson(data));//gson.tojson() converts your pojo to json
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			
			String content = EntityUtils.toString(entity);
			
			//map response to class
			TPPResponse tppres = gson.fromJson(content, TPPResponse.class);
			System.out.println(tppres.status);
			
			if("pending".equals(tppres.status)) {
				DBOperations.deleteRecord(data.getId());
			}
			
			return response;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		
		return response;
	}
	
}
