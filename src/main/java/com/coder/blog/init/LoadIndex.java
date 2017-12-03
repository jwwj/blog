package com.coder.blog.init;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.client.ResultScanner;

import com.coder.blog.dao.HbaseDao2;

public class LoadIndex extends Thread {
	private HttpSession session;
	public LoadIndex(HttpSession session) {
		this.session = session;
	}

	@Override
	public void run() {
		 try {
				ResultScanner rs = HbaseDao2.scanInRegin("ArticleTable");
				if(rs!=null) 
					session.setAttribute("IndexResultScanner", rs);
				else
					session.setAttribute("IndexResultScanner", "none");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
