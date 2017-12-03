package com.coder.blog.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import com.coder.blog.dao.HbaseDao;
import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.dao.UserHbaseDao;
import com.coder.blog.model.UserHbase;
import com.coder.blog.utils.NumUtil;

public class TestUserHbase {

	public void TestGetAtrical() {

		try {
			HbaseDao hbase = new HbaseDao();

			hbase.deleteTable("User");
//			rowkey 用户ID
//			article 文章ID 用户写的文章
//			history 文章ID 用户的浏览记录
			hbase.createTable("User", new String[] { "user_inf" },99999);
			
			String user_id = "d576f726-ccb8-11e7-8055-000c29452861";
			
			hbase.addOneRecord("User", user_id,"user_inf", "article", Bytes.toBytes("用户写的文章ID1"));
			hbase.addOneRecord("User", user_id,"user_inf", "article", Bytes.toBytes("用户写的文章ID2"));
			hbase.addOneRecord("User", user_id,"user_inf", "article", Bytes.toBytes("用户写的文章ID3"));
			
			hbase.addOneRecord("User", user_id,"user_inf", "history", Bytes.toBytes("用户看的文章ID1"));
			hbase.addOneRecord("User", user_id,"user_inf", "history", Bytes.toBytes("用户看的文章ID2"));
			
			UserHbaseDao uhd = new UserHbaseDao();
			uhd.selectUserHistoryById(user_id);
			
		
			
			
			//后台返回全部记录，前台做分页显示
//			Result rs = hbase.getValueByQuafilier("User", user_id, "user_inf", "article",3);
//			if(rs!=null){
//			List<KeyValue> list = rs.list();
//			for (KeyValue keyValue : list) {
//				System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
//				System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
//				System.out.println("Column:"+ Bytes.toString(keyValue.getQualifier()));
//				System.out.println("Value:"+ Bytes.toString(keyValue.getValue()));
//				System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
//			}
//			}
//	
//			Result rs2 = hbase.getValueByQuafilier("User", user_id, "user_inf", "history",5);
//			if(rs2!=null) {
//			List<KeyValue> list2 = rs2.list();
//			for (KeyValue keyValue : list2) {
//				System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
//				System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
//				System.out.println("Column:"+ Bytes.toString(keyValue.getQualifier()));
//				System.out.println("Value:"+ Bytes.toString(keyValue.getValue()));
//				System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
//			
//			}
//			}

	//		hbase.deleteRecord("User", "article");

	//		hbase.deleteTable("User");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void newUserTable() throws Exception {
		HbaseDao hbase = new HbaseDao();
		hbase.deleteTable("User");
//		rowkey 用户ID
//		article 文章ID 用户写的文章
//		history 文章ID 用户的浏览记录
		hbase.createTable("User", new String[] { "user_inf" },99999);
	}
	
	public void addUserhistroy() throws IOException {
		UserHbaseDao uh = new UserHbaseDao();
		uh.addUserHistory("d576f726-ccb8-11e7-8055-000c29452861", "222");
	}
	public void deleteWithTimeStamp() {
		HbaseDao2 hbase = new HbaseDao2();
		hbase.deleteRecordWithTimeFilter("User", "d576f726-ccb8-11e7-8055-000c29452861", "user_inf","history", Long.parseLong("1511502021052"));
	}
	
	public void scan() throws Exception {
		HbaseDao2 hbase = new HbaseDao2();
		hbase.getAllData("User");
	}
	public void printMap() {
		UserHbase uh = new UserHbase();
		//Map输出方式(倒序)
	    Map<String, Object> map = uh.getHistory();   
	    ListIterator<Entry<String,Object>> i=new ArrayList<Entry<String,Object>>(map.entrySet()).listIterator(map.size());  
	    while(i.hasPrevious()) {
	    	Entry<String, Object> entry=i.previous();  
            System.out.println(entry.getKey()+":"+entry.getValue());  
      
	    }
	    //Map输出方式（正序）
	    for (Entry<String, Object> entry : map.entrySet()) {  
	        System.out.println(entry.getKey());  
	        System.out.println(entry.getValue().toString());  
	    }  
		
	}
}
