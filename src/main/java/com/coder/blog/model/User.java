package com.coder.blog.model;

public class User {

    private String user_id;

    private String phone_num;

    private String user_name;

    private String user_pwd;

    private String user_head;

    private String sex;

    private String introduce;

    private String city;

    private String profession;

    private String career;

    private String edu;
    

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num == null ? null : phone_num.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd == null ? null : user_pwd.trim();
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head == null ? null : user_head.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getCareer() {
        return career;
    }
    public User() {}
    public User(String user_id, String phone_num, String user_name, String user_pwd, String user_head, String sex,
			String introduce, String city, String profession, String career, String edu) {
		super();
		this.user_id = user_id;
		this.phone_num = phone_num;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_head = user_head;
		this.sex = sex;
		this.introduce = introduce;
		this.city = city;
		this.profession = profession;
		this.career = career;
		this.edu = edu;
	}

	public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu == null ? null : edu.trim();
    }
}