package com.modestack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="social_links")
public class SocialLinks {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="links_id")
	private int linksId;
	
	@Column(name="facebook_url")
	private String facebookUrl;
	
	@Column(name="twitter_url")
	private String twitterUrl;
	
	@Column(name="instagram_url")
	private String instagramUrl;
	
	@ManyToOne
	@JoinColumn(name="user_id")
    private UserProfile userProfile;

	public int getLinksId() {
		return linksId;
	}

	public void setLinksId(int linksId) {
		this.linksId = linksId;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getInstagramUrl() {
		return instagramUrl;
	}

	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	@JsonBackReference
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}	

}
