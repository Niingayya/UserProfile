package com.modestack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.modestack.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {

	
}
