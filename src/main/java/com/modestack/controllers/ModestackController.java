package com.modestack.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.model.Article;
import com.modestack.model.UserProfile;
import com.modestack.services.ModestackService;

import io.swagger.annotations.ApiOperation;


@RestController
public class ModestackController {
	
	private static final Logger LOGGER = LogManager.getLogger(ModestackController.class);

	@Autowired
	ModestackService modestackService;

	@ApiOperation(httpMethod = "POST", value = "Creates a new user.")
	@PostMapping(path ="/register", consumes = "application/json")
	private ResponseEntity<Object> userRegistory(@RequestBody UserProfile userProfile) throws InterruptedException {

		LOGGER.info(":: in createUserRegistory() :: ");
		
		UserProfile profile = modestackService.userRegistory(userProfile);

		if (profile != null) {

			return new ResponseEntity<Object>(profile.getUserId(), HttpStatus.CREATED);

		} else {
			return new ResponseEntity<Object>("user name already exists ",HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(httpMethod = "PUT", value = "Update user.")
	@PutMapping(path = "/register/{userId}", consumes = "application/json")
	private ResponseEntity<Object> updateUserProfile(@RequestBody UserProfile userProfile, @PathVariable int userId) {
		
		LOGGER.info(":: in updateUserRegistory() :: ");
		
		UserProfile profile = modestackService.updateUserProfile(userProfile, userId);
    
		return new ResponseEntity<Object>(profile, HttpStatus.CREATED);
	}

	@ApiOperation(httpMethod = "GET", value = "getting user by userId.")
	@GetMapping(path = "/user/{userId}",produces = "application/json")
	private ResponseEntity<Object> getUser( @PathVariable int userId) {
		LOGGER.info(":: in getUserByUserId() :: ");
		UserProfile profile = modestackService.getUser(userId);
		return new ResponseEntity<Object>(profile, HttpStatus.OK);

	}
	

	@ApiOperation(httpMethod = "POST", value = "login success.")
	@PostMapping(path = "/login", consumes = "application/json")
	private ResponseEntity<Object> userLogin(@RequestBody UserProfile userProfile) {
		LOGGER.info(":: in userLogin() :: ");
		Map<String, String> rsponse = modestackService.userLogin(userProfile.getUserName(), userProfile.getPassword());
		if (rsponse != null) {
			return new ResponseEntity<Object>(rsponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
		
	}

	@ApiOperation(httpMethod = "GET", value = "Get the List of users.")
	@GetMapping(path = "/users", produces = "application/json")
	private ResponseEntity<Object> getAllUsers() {
		LOGGER.info(":: in getAllUser() :: ");
		List<UserProfile> users = modestackService.getAllUsers();

		return new ResponseEntity<Object>(users, HttpStatus.OK);

	}

	@ApiOperation(httpMethod = "POST", value = "Creates a new article.")
	@PostMapping(path = "/articles", consumes = "application/json")
	private ResponseEntity<Object> createArticle(@RequestBody Article article) {
		LOGGER.info(":: in createArticle() :: ");
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
		LOGGER.info(":: in getArticles() :: ");
		Map<String, List<Article>> map = new HashMap<String, List<Article>>();
		List<Article> articles = (List<Article>) modestackService.getArticles();
		map.put("data", articles);
		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

}
