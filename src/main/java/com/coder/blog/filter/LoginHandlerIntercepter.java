package com.coder.blog.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.coder.blog.controller.UserController;
import com.coder.blog.model.User;

/**
 * 登陆拦截器 场景：用户点击查看的时候，我们进行登陆拦截器操作，判断用户是否登陆？ 登陆，则不拦截，没登陆，则转到登陆界面；
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		String requestURI = request.getRequestURI();
		System.out.println("filter:doing..." + requestURI);

		// 登陆
		if (requestURI.indexOf("sin.do") > 0)
			return true;
		// 注册
		if (requestURI.indexOf("sup.do") > 0)
			return true;

		HttpSession session = request.getSession();
		String logined = (String) session.getAttribute("logined");
		User user = (User) session.getAttribute("user");
		if (logined != null&&user!=null) {
			// 已经登陆
			System.out.println("filter:login success");
			if (requestURI.indexOf("login") > 0) {
				
				response.sendRedirect("index");
				return false;//若需要跳转必须return false;不知为何。。
			} else {
				return true;
			}
		} else {
			// 未登陆用户
			System.out.println("filter:login fail");
			if (requestURI.indexOf("blog/login") > 0) {
				return true;
			} else {
				response.sendRedirect("/blog/login.html");
//				request.getRequestDispatcher("login.html").forward(request, response);
				return false;
			}
		}

	
	}

}