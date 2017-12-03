package com.coder.blog.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.coder.blog.controller.UserController;
import com.coder.blog.model.User;

/**
 * ��½������ �������û�����鿴��ʱ�����ǽ��е�½�������������ж��û��Ƿ��½�� ��½�������أ�û��½����ת����½���棻
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

		// ��½
		if (requestURI.indexOf("sin.do") > 0)
			return true;
		// ע��
		if (requestURI.indexOf("sup.do") > 0)
			return true;

		HttpSession session = request.getSession();
		String logined = (String) session.getAttribute("logined");
		User user = (User) session.getAttribute("user");
		if (logined != null&&user!=null) {
			// �Ѿ���½
			System.out.println("filter:login success");
			if (requestURI.indexOf("login") > 0) {
				
				response.sendRedirect("index");
				return false;//����Ҫ��ת����return false;��֪Ϊ�Ρ���
			} else {
				return true;
			}
		} else {
			// δ��½�û�
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