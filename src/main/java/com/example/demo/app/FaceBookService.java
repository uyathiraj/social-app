package com.example.demo.app;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.User;

@Service
public class FaceBookService {

	

	private final String pageID = "349847305763654";

	public void post(String token, Map<String, Object> request) {
		String message = (String) request.get("message");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://graph.facebook.com/" + pageID + "/feed?message=\n" + message + "&access_token=" + token;
		Map res = restTemplate.postForObject(url, "hello", Map.class);
		System.out.println(res);
	}

}
