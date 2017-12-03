package com.coder.blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.client.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.init.LoadIndex;
import com.coder.blog.init.LoadTopic;
import com.coder.blog.model.User;
import com.coder.blog.utils.AjaxRedirect;

@Controller
public class RedirectController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void welcomeFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxRedirect.url("/login.html", request, response);
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String redirectIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		LoadIndex li = new LoadIndex(session);//加载index页面:预加载文章列表
		li.start();
		session.setAttribute("loadindex", li);//便于之后判断线程是否完成
		return "index";
	}
	
	@RequestMapping(value = "/topic", method = RequestMethod.GET)
	public String redirectTopic() throws IOException {
	
		return "topic";
	}
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public String redirectArticle() throws IOException {
		return "article";
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String redirectWrite() throws IOException {
		return "write";
	}
	@RequestMapping(value = "/developers_info", method = RequestMethod.GET)
	public String redirectDecelopers_info() throws IOException {
		return "developers_info";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String redirectEdit() throws IOException {
		return "edit";
	}
	
	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public String redirectPeoplee() throws IOException {
		//查询用户历史记录和文章记录

		return "people";
	}
	
}
