package com.coder.blog.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxRedirect {
	public static void url(String url,HttpServletRequest request, HttpServletResponse response) throws IOException{
		  //��ȡ��ǰ�����·��
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
        //���request.getHeader("X-Requested-With") ���ص���"XMLHttpRequest"˵������ajax������Ҫ���⴦��
        System.out.println("Redirect:"+basePath+url);
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //����ajax�����ض���
            response.setHeader("REDIRECT", "REDIRECT");
            //����ajax���ض����·��
            response.setHeader("CONTENTPATH", basePath+url);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect(basePath + url);
        }
	}

}
