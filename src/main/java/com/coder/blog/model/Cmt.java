package com.coder.blog.model;

public class Cmt {

	private int colnum = 0; // ĳ�������µĵڼ�������
	
	private int repnum = 0; // ĳ�����۵ĵڼ����ظ�
	
	private String cont; //��������

	private String user; //���۵��û�
	
	private String user_name;
	


	private long time;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Cmt() {
		
	}
	
	public Cmt(int colnum, String cont,String user,long time) {
		//һ������
		this.colnum = colnum;
		this.cont = cont;
		this.user = user;
		this.time = time;
	}
	public Cmt(int colnum,int repnum, String cont,String user,long time) {
		//��������
		this.colnum = colnum;
		this.repnum = repnum;
		this.cont = cont;
		this.user = user;
		this.time = time;
	}
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getColnum() {
		return colnum;
	}

	public void setColnum(int colnum) {
		this.colnum = colnum;
	}

	public int getRepnum() {
		return repnum;
	}

	public void setRepnum(int repnum) {
		this.repnum = repnum;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
