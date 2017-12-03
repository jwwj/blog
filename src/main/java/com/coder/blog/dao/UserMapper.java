package com.coder.blog.dao;

import com.coder.blog.model.User;

public interface UserMapper {

    int deleteByPrimaryKey(String user_id);

    int insert(User record);


    int insertSelective(User record);


    User selectByPrimaryKey(String user_id);
    
    User selectByPhoneNum(String phone_num);

 
    int updateByPrimaryKeySelective(User record);


    int updateByPrimaryKey(User record);
}