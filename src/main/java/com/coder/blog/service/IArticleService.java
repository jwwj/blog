package com.coder.blog.service;

import com.coder.blog.model.Article;
import com.coder.blog.model.Cmt;

public interface IArticleService {
	public Article getArticleById(String art_id) ;
	public String addArticle(Article article);
	public int updateReading(String art_id);
	
	public int addCmt(Cmt cmt);
}
