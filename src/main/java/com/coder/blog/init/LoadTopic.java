package com.coder.blog.init;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.client.ResultScanner;

import com.coder.blog.dao.HbaseDao2;

public class LoadTopic  extends Thread {
		private HttpSession session;
		private String topic;
		public LoadTopic(HttpSession session,String topic) {
			this.session = session;
			this.topic=topic;
		}

		@Override
		public void run() {
			 try {
					ResultScanner rs = HbaseDao2.scanInReginWithValue("ArticleTable",topic);
					if(rs!=null) 
						session.setAttribute("TopicResultScanner", rs);
					else
						session.setAttribute("TopicResultScanner", "none");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

