package com.coder.blog.service;

import com.coder.blog.model.User;

public interface IUserService {
    public User getUserById(String id);
    public int setUser(User user);
    public User getUserByPhoneNum(String phone_num);
 
}
