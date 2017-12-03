package com.coder.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coder.blog.dao.ArticleDao;
import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.dao.UserMapper;
import com.coder.blog.model.User;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    


    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }


	@Override
	public int setUser(User user) {
		
		return userMapper.insert(user);
	}


	@Override
	public User getUserByPhoneNum(String phone_num) {
		
		return userMapper.selectByPhoneNum(phone_num);
	}


	

}