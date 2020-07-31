package com.modestack.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.model.Article;
import com.modestack.model.UserProfile;
import com.modestack.services.ModestackService;

import io.swagger.annotations.ApiOperation;


@RestController
public class ModestackController {

	@Autowired
	ModestackService modestackService;

	@ApiOperation(httpMethod = "POST", value = "Creates a new user.")
	@PostMapping(path ="/register", consumes = "application/json")
	private ResponseEntity<Object> userRegistory(@RequestBody UserProfile userProfile) {

		UserProfile profile = modestackService.userRegistory(userProfile);

		return new ResponseEntity<Object>("new user created",HttpStatus.CREATED);

	}

	@ApiOperation(httpMethod = "POST", value = "login success.")
	@PostMapping(path = "/login", consumes = "application/json")
	private ResponseEntity<Object> userLogin(@RequestBody UserProfile userProfile) {
		Map<String, String> rsponse = modestackService.userLogin(userProfile.getUserName(), userProfile.getPassword());
		if (rsponse != null) {
			return new ResponseEntity<Object>(rsponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
		
	}

	@ApiOperation(httpMethod = "POST", value = "Creates a new article.")
	@PostMapping(path = "/articles", consumes = "application/json")
	private ResponseEntity<Object> createArticle(@RequestBody Article article) {
		Article articleCreated = modestackService.createArticle(article);
		if (articleCreated != null) {
			return new ResponseEntity<Object>("new article created",HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
		
	}

	@ApiOperation(httpMethod = "GET", value = "Get the List of article.")
	@GetMapping(path = "/articles", produces = "application/json")
	private ResponseEntity<Object> getArticles() {
		Map<String, List<Article>> map = new HashMap<String, List<Article>>();
		List<Article> articles = (List<Article>) modestackService.getArticles();
		map.put("data", articles);
		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

}
