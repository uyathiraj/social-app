package com.example.demo.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social")
public class AppController {

	@Autowired
	private TwitterService twitterService;

	@Autowired
	private FaceBookService fbService;

	@PostMapping("/post")
	private ResponseEntity<?> postContent(@RequestHeader("token") String token,
			@RequestBody Map<String, Object> request) {
		Thread t1 = new Thread(() -> {
			twitterService.postTweet(request);
		});
		Thread t2 = new Thread(() -> {
			// fbService.postContent(request);
			fbService.post(token, request);
		});
		t1.start();
		t2.start();
		return new ResponseEntity<>("Message has been posted succesfully.", HttpStatus.OK);
	}

	@GetMapping("/test")
	private ResponseEntity<?> test() {
		return new ResponseEntity<>("Hellloooo", HttpStatus.OK);
	}

	@PostMapping("/token/store")
	private ResponseEntity<?> storeTokens(@RequestBody ConcurrentHashMap<String, String> request) {
		twitterService.setUpTokens(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/token")
	private ResponseEntity<?> getTokens() {
		return new ResponseEntity<>(Constants.hashMap, HttpStatus.OK);
	}

}
