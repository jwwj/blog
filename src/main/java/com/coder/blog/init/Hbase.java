package com.coder.blog.init;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.coder.blog.dao.HbaseDao;

@Component  //
public class Hbase implements  ApplicationContextAware {

	public static HbaseDao conn;
	
//	public static Class hbase;
	  @Override
	    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
	        System.out.println("springmvc  正在初始化...");
	        try {
				 Class.forName("com.coder.blog.dao.HbaseDao2");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
//
//	private void createHbaseDao() {  
//		Thread thread = new Thread(){
//			   public void run(){
//				   try {
//					conn = new HbaseDao();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			   }
//			};
//			thread.start();
//    }  

}
