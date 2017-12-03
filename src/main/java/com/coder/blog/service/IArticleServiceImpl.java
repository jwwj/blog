package com.coder.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coder.blog.dao.ArticleDao;
import com.coder.blog.model.Article;
import com.coder.blog.model.Cmt;

@Service("articleService")
@Transactional
public class IArticleServiceImpl implements IArticleService {

	ArticleDao articleDao = new ArticleDao();
	
	@Override
	public Article getArticleById(String art_id) {
		return articleDao.selectByRowKey(art_id);
	}

	@Override
	public String addArticle(Article article) {
		return articleDao.insert(article);
	}

	@Override
	public int updateReading(String art_id) {
		
		return articleDao.updateReading(art_id);
	}

	@Override
	public int addCmt(Cmt cmt) {
		
		return articleDao.addCmt(cmt);
	}


}
