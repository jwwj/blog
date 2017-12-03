package com.coder.blog.test;

import org.junit.Test;

import com.coder.blog.dao.HbaseDao2;

public class TestHbase {
	
	@Test
	public void Test() throws Exception {
		HbaseDao2.getAllTables();
		HbaseDao2.getAllData("ArticleTable");
	}
}
