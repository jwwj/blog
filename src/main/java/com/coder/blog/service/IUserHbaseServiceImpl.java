package com.coder.blog.service;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coder.blog.dao.ArticleDao;
import com.coder.blog.dao.UserHbaseDao;
import com.coder.blog.model.UserHbase;
@Service("userHbaseService")
@Transactional
public class IUserHbaseServiceImpl implements IUserHbaseService{

	UserHbaseDao uh = new UserHbaseDao();
 
	@Override
	public UserHbase selectUserHistoryById(String user_id) throws IOException  {
		return uh.selectUserHistoryById(user_id);
	}
	
	@Override
	public UserHbase selectUserArticleById(String user_id) throws IOException {
		return uh.selectUserArticleById(user_id);
	}


	@Override
	public void addUserHistory(String user_id, String art_id) throws IOException {
		uh.addUserHistory(user_id, art_id);
	}

	@Override
	public void addUserArticle(String user_id, String art_id) throws IOException {
		uh.addUserArticle(user_id,art_id);
	}


}
