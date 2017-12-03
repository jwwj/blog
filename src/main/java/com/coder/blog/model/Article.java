package com.coder.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article {
    private String title;

    private String topic;

    private String cont;
  
	private long time;
    
    private String like;

    private String reading;

    private String user_id;
    
    private String user_name;
    
	private String abs;
    
    private String art_id;

	private List<Cmt> cmtlist = new ArrayList<Cmt>();//评论

    private List<Cmt> repcmtlist = new ArrayList<Cmt>();//评论的回复
    
  
    public Article() {
    	
    }
    
    public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getArt_id() {
		return art_id;
	}

	public void setArt_id(String art_id) {
		this.art_id = art_id;
	}
    
    public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
    
    public void addCmt(Cmt cmt) {
    	this.cmtlist.add(cmt);
    } 
    
    public void addReplyCmt(Cmt repcmt) {
    	this.repcmtlist.add(repcmt);
    } 
    

    public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<Cmt> getCmtlist() {
		return cmtlist;
	}

	public void setCmtlist(List<Cmt> cmtlist) {
		this.cmtlist = cmtlist;
	}

	public List<Cmt> getRepcmtlist() {
		return repcmtlist;
	}

	public void setRepcmtlist(List<Cmt> repcmtlist) {
		this.repcmtlist = repcmtlist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}






}
