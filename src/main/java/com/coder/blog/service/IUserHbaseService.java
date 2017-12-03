package com.coder.blog.service;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;

import com.coder.blog.model.UserHbase;

public interface IUserHbaseService {
	public UserHbase selectUserHistoryById(String user_id)  throws IOException;
	public UserHbase selectUserArticleById(String user_id)  throws IOException;

	public void addUserHistory(String user_id, String art_id) throws IOException;
	
	public void addUserArticle(String user_id,String art_id) throws IOException;
}
