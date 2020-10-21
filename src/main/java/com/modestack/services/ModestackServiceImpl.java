package com.modestack.services;

import java.text.ParseException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modestack.ReadValue;
import com.modestack.model.Article;
import com.modestack.model.Pepper;
import com.modestack.model.SocialLinks;
import com.modestack.model.UserProfile;
import com.modestack.repository.ArticleRepository;
import com.modestack.repository.ModestackRepository;
import com.modestack.repository.PepperRepository;
import com.modestack.util.DateConvertion;

@Service
public class ModestackServiceImpl implements ModestackService {

	@Autowired
	ModestackRepository modestackRepository;

	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	PepperRepository pepperRepository;
	
	@Autowired
	ReadValue readValue;
	
	private int pepperId=1;
	
	

	public UserProfile userRegistory(UserProfile userProfile) throws InterruptedException {
		UserProfile profile = null;
		Pepper pepper=pepperRepository.findByPepperId(pepperId);
		String pepperName=pepper.getPepperName();
		String saltName = readValue.getSaltName();
		System.out.println("salt name = " + saltName);
		System.out.println("pepper name = " + pepperName);
		String token = Base64.getEncoder()
				.encodeToString((userProfile.getUserName() + userProfile.getPassword()).getBytes());
		String encryptedPassword = Base64.getEncoder()
				.encodeToString((saltName + userProfile.getPassword() + pepperName).getBytes());
		UserProfile user = modestackRepository.findByUserName(userProfile.getUserName());

		if (user != null) {
			return profile;
		}

		else {
			String userName = userProfile.getUserName();
			try {
				userProfile.setDob(DateConvertion.dateConvertion(userProfile.getDob()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			userProfile.setAccessToken(token);
			userProfile.setPassword(encryptedPassword);
			profile = modestackRepository.save(userProfile);
		}
		return profile;
		
	}
	
	public UserProfile updateUserProfile(UserProfile userProfile, int userId) {
		UserProfile profile = null;

		UserProfile user = modestackRepository.findById(userId).get();

		if (user != null) {
			Pepper pepper = pepperRepository.findByPepperId(pepperId);
			String pepperName = pepper.getPepperName();
			String saltName = readValue.getSaltName();
			String token = Base64.getEncoder()
					.encodeToString((userProfile.getUserName() + userProfile.getPassword()).getBytes());
			String encryptedPassword = Base64.getEncoder()
					.encodeToString((saltName + userProfile.getPassword() + pepperName).getBytes());
			user.setUserName(userProfile.getUserName());
			try {
				user.setDob(DateConvertion.dateConvertion(userProfile.getDob()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			user.setEmail(userProfile.getEmail());
			user.setAddress(userProfile.getAddress());
			user.setAccessToken(token);
			user.setPhoneNo(userProfile.getPhoneNo());
			user.setPassword(encryptedPassword);
			Set<SocialLinks> links = (Set<SocialLinks>) user.getSocialLinks();
			Set<SocialLinks> socialLinks = (Set<SocialLinks>) userProfile.getSocialLinks();
			for (SocialLinks socialLinks2 : socialLinks) {
				String faceBookUrl = socialLinks2.getFacebookUrl();
				String twitterUrl = socialLinks2.getTwitterUrl();
				String instagramUrl = socialLinks2.getInstagramUrl();
				for (SocialLinks socialLinkss : links) {
					socialLinkss.setFacebookUrl(faceBookUrl);
					socialLinkss.setTwitterUrl(twitterUrl);
					socialLinkss.setInstagramUrl(instagramUrl);
					socialLinkss.setUserProfile(userProfile);
					links.add(socialLinkss);

				}
			}

			user.setSocialLinks(links);
			profile = modestackRepository.save(user);

		}

		return profile;

	}

	public UserProfile getUser(int userId) {
		UserProfile user = modestackRepository.findById(userId).get();
		return user;

	}
	
	public Map<String, String> userLogin(String userName, String password) {
		String accessToken = null;
		Pepper pepper=pepperRepository.findByPepperId(pepperId);
		String pepperName=pepper.getPepperName();
		String saltName = readValue.getSaltName();
		String encryptedPassword = Base64.getEncoder()
				.encodeToString((saltName + password + pepperName).getBytes());
		UserProfile profile = modestackRepository.findByUserNameAndPassword(userName, encryptedPassword);
		Map<String, String> response = null;
		if (profile != null) {
			response = new HashMap<String, String>();
			accessToken = profile.getAccessToken();
			response.put("message", "success");
			response.put("accessToken", accessToken);
		}
		return response;
	}

	public List<UserProfile> getAllUsers() {
		List<UserProfile> users = (List<UserProfile>) modestackRepository.findAll();

		return users;

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
