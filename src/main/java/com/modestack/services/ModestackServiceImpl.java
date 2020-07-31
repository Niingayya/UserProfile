package com.modestack.services;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modestack.model.Article;
import com.modestack.model.UserProfile;
import com.modestack.repository.ArticleRepository;
import com.modestack.repository.ModestackRepository;

@Service
public class ModestackServiceImpl implements ModestackService {

	@Autowired
	ModestackRepository modestackRepository;

	@Autowired
	ArticleRepository articleRepository;

	public UserProfile userRegistory(UserProfile userProfile) {
		String userName = userProfile.getUserName();
		String password = userProfile.getPassword();
		String token = Base64.getEncoder().encodeToString((userName + password).getBytes());
		userProfile.setAccessToken(token);
		UserProfile profile = modestackRepository.save(userProfile);

		return profile;
	}

	public Map<String, String> userLogin(String userName, String password) {
		String accessToken = null;
		UserProfile profile = modestackRepository.findByUserNameAndPassword(userName, password);
		Map<String, String> response = null;
		if (profile != null) {
			response = new HashMap<String, String>();
			accessToken = profile.getAccessToken();
			response.put("message", "success");
			response.put("accessToken", accessToken);
		}
		return response;
	}

	public Article createArticle(Article article) {
		UserProfile profile = modestackRepository.findByUserNameAndAccessToken(article.getAuthor(), article.getAccessToken());
		Article articleCreated = null;
		if (profile !=null) {
			article.setUserId(profile.getUserId());
			articleCreated = articleRepository.save(article);
		}
		return articleCreated;
	}

	public List<Article> getArticles() {
		List<Article> articles = (List<Article>) articleRepository.findAll();
		return articles;

	}

}
