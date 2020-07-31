package com.modestack.services;

import java.util.List;
import java.util.Map;

import com.modestack.model.Article;
import com.modestack.model.UserProfile;

public interface ModestackService {

	public UserProfile userRegistory(UserProfile userProfile);
	
	public Map<String, String> userLogin(String userName,String password);
	
	public Article createArticle(Article article);
	
	public List<Article> getArticles();
	
}
