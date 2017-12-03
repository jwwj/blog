package com.coder.blog.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHbase {

	private String user_id;
	private Map<String , Object> history = new HashMap<String , Object>();   
	private Map<String , Object> article = new HashMap<String , Object>();
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Map getHistory() {
		return history;
	}
	public Object getHistoryById(String art_id) {
		return history.get(art_id);
	}
	public void addHistory(String art_id, long time) {
		this.history.put(art_id, time);
		
	}
	public Map getArticle() {
		return article;
	}
	public Object getArticleById(String art_id) {
		return article.get(art_id);
	}
	public void setArticle(String art_id, long time) {
		this.article.put(art_id, time);
	}
	

}
