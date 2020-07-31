package com.modestack.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="articles")
@JsonIgnoreProperties(value={"articleId","userId"})
public class Article {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="article_id")
	private int articleId;
	
	
	@Column(name="title")
	private String title;
	
	@Column(name="body")
	private String body;
	
	@Column (name="author")
	private String author;
	
	@Column(name="access_token")
	private String accessToken;
	
	@Column(name="user_id")
	private int userId;
	

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", body=" + body + ", author=" + author
				+ ", accessToken=" + accessToken + ", userId=" + userId + "]";
	}
	
	

}
