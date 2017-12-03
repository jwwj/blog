package com.coder.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.client.ResultScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.init.LoadIndex;
import com.coder.blog.model.User;
import com.coder.blog.service.IUserService;
import com.coder.blog.utils.AjaxRedirect;
import com.coder.blog.utils.Msg;
import com.jcraft.jsch.Session;

@Controller

public class UserController {

	@Autowired
	IUserService userService;

	


	@RequestMapping(value = "/sin", method = RequestMethod.POST)
	public @ResponseBody Msg UserSin1(@RequestBody User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User userFromDataBase = userService.getUserByPhoneNum(user.getPhone_num());
		if (userFromDataBase == null) {
			return Msg.msg(103, "该用户不存在");
		} else {
			if (userFromDataBase.getUser_pwd().equals(user.getUser_pwd())) {
				HttpSession session = request.getSession(true);
				session.setAttribute("logined", "success");
				session.setAttribute("user", userFromDataBase);
				User us = (User) session.getAttribute("user");
				System.out.println("user" + us.getUser_name());
			
				AjaxRedirect.url("/index", request, response);
				return Msg.msg(101, "登陆成功");
			} else {
				System.out.println(userFromDataBase.getUser_pwd());
				System.out.println(user.getUser_pwd());
				return Msg.msg(102, "密码错误");
			}
		}
	}

	@RequestMapping(value = "/sup", method = RequestMethod.POST)
	public @ResponseBody Msg UserSup(@RequestBody User user, HttpServletRequest request, HttpServletResponse response)
			throws IOException {	

		if (userService.getUserByPhoneNum(user.getPhone_num()) != null) {
			return Msg.msg(203, "该用户已注册，请直接登陆");
		} else {
			int result = userService.setUser(user);
			System.out.println("signup:" + result);
			if (result == 1) {
				HttpSession session = request.getSession(true);
				session.setAttribute("logined", "success");
				User userFromDataBase = userService.getUserByPhoneNum(user.getPhone_num());
				session.setAttribute("user", userFromDataBase);
				AjaxRedirect.url("/index", request, response);
				return Msg.msg(101, "注册成功");
			} else
				return Msg.msg(202, "注册失败");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody Msg UserLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		System.out.println("logout:success");
		session.removeAttribute("logined");
		session.removeAttribute("user");
		AjaxRedirect.url("/login.html", request, response);
		return Msg.msg(301, "已退出");
	}

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public @ResponseBody User UserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		
		System.out.println("userinfo:" + user.getUser_name());
		return user;
	
	}
	
	
}
