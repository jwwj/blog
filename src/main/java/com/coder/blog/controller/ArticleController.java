package com.coder.blog.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.blog.model.Article;
import com.coder.blog.model.Cmt;
import com.coder.blog.model.User;
import com.coder.blog.service.IArticleService;
import com.coder.blog.service.IArticleServiceImpl;
import com.coder.blog.service.IUserHbaseService;
import com.coder.blog.service.IUserService;
import com.coder.blog.utils.AjaxRedirect;
import com.coder.blog.utils.Msg;

@Controller
public class ArticleController {

	@Autowired
	IUserService userservice;
	@Autowired
	IArticleService articleService;
	@Autowired
	IUserHbaseService userhaseservice;

	// @ResponseBody用于返回对象不是页面时
	@RequestMapping(value = "/article/{art_id}", method = RequestMethod.GET)
	public String getArticlePage(@PathVariable String art_id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("art_id:" + art_id);
		int ur = articleService.updateReading(art_id);
		Article ac = articleService.getArticleById(art_id);
		if (ac == null||ur==0) {
			return "error";
		} else {
			ac.setUser_name(userservice.getUserById(ac.getUser_id()).getUser_name());
			System.out.println("ac.getUser_name()"+ac.getUser_name());
			request.setAttribute("article", ac);
			System.out.println("ac.getAbs():" + ac.getAbs());
			System.out.println("ac.getCont():" + ac.getCont());
			System.out.println("ac.getLike():" + ac.getLike());
			System.out.println("ac.getReading():" + ac.getReading());
			System.out.println("ac.getTitle():" + ac.getTitle());
			System.out.println("ac.getTopic():" + ac.getTopic());
			System.out.println("ac.getUser_id():" + ac.getUser_id());//返回用户名
			System.out.println("ac.getTime():" + new Date(ac.getTime()));
			for (Cmt cmt : ac.getCmtlist()) {
				cmt.setUser_name(userservice.getUserById(cmt.getUser()).getUser_name());
				System.out.println("cmt.getUser_name()"+cmt.getUser_name());
				System.out.println("cmt.getColnum():" + cmt.getColnum());
				System.out.println("cmt.getCont():" + cmt.getCont());
				System.out.println("cmt.getUser():" + cmt.getUser());
				System.out.println("cmt.getTime():" + new Date(cmt.getTime()));
			}
			for (Cmt cmt : ac.getRepcmtlist()) {
				cmt.setUser_name(userservice.getUserById(cmt.getUser()).getUser_name());
				System.out.println("repcmt.getUser_name()"+cmt.getUser_name());
				System.out.println("repcmt.getColnum():" + cmt.getColnum());
				System.out.println("repcmt.getRepnum():" + cmt.getRepnum());
				System.out.println("repcmt.getCont():" + cmt.getCont());
				System.out.println("repcmt.getUser():" + cmt.getUser());
				System.out.println("repcmt.getTime():" + new Date(cmt.getTime()));
			}
			request.getRequestDispatcher("/WEB-INF/views/article.jsp").forward(request, response);
		}
		// return "forward:/body_matter";
		return null;
	}

	@RequestMapping(value = "/addarticle", method = RequestMethod.POST)
	public @ResponseBody Msg AddArticle(@RequestBody Article article, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println(article.getTitle());
		String art_id = articleService.addArticle(article);
		userhaseservice.addUserArticle(article.getUser_id(), art_id);
		System.out.println("addarticle:" + art_id);
		if (art_id != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("addarticle", art_id);
			AjaxRedirect.url("/article/" + art_id, request, response);
			return Msg.msg(301, "发布文章成功");
		} else
			return Msg.msg(302, "发布文章失败");
	}
	@RequestMapping(value = "/addcmt", method = RequestMethod.POST)
	public @ResponseBody Msg AddCmt(@RequestBody Cmt cmt, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//System.out.println();
		String art_id = articleService.addArticle(article);
		userhaseservice.addUserArticle(article.getUser_id(), art_id);
		System.out.println("addarticle:" + art_id);
		if (art_id != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("addarticle", art_id);
			AjaxRedirect.url("/article/" + art_id, request, response);
			return Msg.msg(301, "发布文章成功");
		} else
			return Msg.msg(302, "发布文章失败");
	}

}
