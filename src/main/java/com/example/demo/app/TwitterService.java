package com.example.demo.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public class TwitterService {

	static String consumerKeyStr;
	static String consumerSecretStr;
	static String accessTokenStr;
	static String accessTokenSecretStr;

	public void setUpTokens(ConcurrentHashMap<String, String> tokenMap) {
		Constants.hashMap = tokenMap;
	}


	public void postTweet(Map<String, Object> request) {

		consumerKeyStr = Constants.hashMap.get("consumerKeyStr");
		consumerSecretStr = Constants.hashMap.get("consumerSecretStr");
		accessTokenStr = Constants.hashMap.get("accessTokenStr");
		accessTokenSecretStr = Constants.hashMap.get("accessTokenSecretStr");

		String message = (String) request.get("message");

		try {
			Twitter twitter = new TwitterFactory().getInstance();

			twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
			AccessToken accessToken = new AccessToken(accessTokenStr, accessTokenSecretStr);

			twitter.setOAuthAccessToken(accessToken);

			twitter.updateStatus(message);

			System.out.println("Successfully updated the status in Twitter.");
		} catch (TwitterException te) {
			te.printStackTrace();
		}
	}

	
}
