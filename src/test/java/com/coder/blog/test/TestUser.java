package com.coder.blog.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.blog.model.User;
import com.coder.blog.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
public class TestUser {

    @Autowired
    IUserService userService;

    @Test
    public void TestGetUser()
    {
//        User user = userService.getUserById("12c46bd2-c952-11e7-9f1b-000c29452861");
//        assertTrue(user.getUser_name().equals("–°–°À÷"));
//        System.out.println(user.getUser_name());
    	System.out.println(userService.getUserByPhoneNum("18412341233").getUser_name());
    }
    

}
