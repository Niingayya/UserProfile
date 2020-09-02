package com.modestack.services;

import java.util.List;
import java.util.Map;

import com.modestack.model.Article;
import com.modestack.model.UserProfile;

public interface ModestackService {

	public UserProfile userRegistory(UserProfile userProfile) throws InterruptedException;
	
	public UserProfile updateUserProfile(UserProfile userProfile,int userId);
	
	public UserProfile getUser(int userId);
	
	public Map<String, String> userLogin(String userName,String password);
	
	public List<UserProfile> getAllUsers();
	
	public Article createArticle(Article article);
	
	public List<Article> getArticles();
	
}
