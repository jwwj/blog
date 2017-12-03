package com.coder.blog.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.coder.blog.model.UserHbase;

public class UserHbaseDao {

	// 返回用户所有历史记录
	public UserHbase selectUserHistoryById(String user_id) throws IOException {
		Result rs = HbaseDao2.getValueByQuafilierWithMaxVersion("User", user_id, "user_inf", "history", 99999);
		if (rs != null) {
			List<KeyValue> list = rs.list();
			UserHbase uh = new UserHbase();
			uh.setUser_id(user_id);
			for (KeyValue keyValue : list) {
				uh.addHistory(Bytes.toString(keyValue.getValue()), keyValue.getTimestamp());
				System.out.println(Bytes.toString(keyValue.getValue()) + "," + keyValue.getTimestamp());
			}
			return uh;
		} else {
			return null;
		}
	}
	//查询用户写的文章记录
	public UserHbase selectUserArticleById(String user_id) throws IOException {
		Result rs = HbaseDao2.getValueByQuafilierWithMaxVersion("User", user_id, "user_inf", "article", 99999);
		if (rs != null) {
			List<KeyValue> list = rs.list();
			UserHbase uh = new UserHbase();
			uh.setUser_id(user_id);
			for (KeyValue keyValue : list) {
				uh.addHistory(Bytes.toString(keyValue.getValue()), keyValue.getTimestamp());
				System.out.println(Bytes.toString(keyValue.getValue()) + "," + keyValue.getTimestamp());
			}
			return uh;
		} else {
			return null;
		}
	}

	
	//添加用户历史记录(先查询原来是否存在相同art_id记录，若存在则删除再保存，若不存在，直接保存)
	public void addUserHistory(String user_id,String art_id) throws IOException {
		Result rs = HbaseDao2.getValueByQuafilierWithMaxVersion("User", user_id, "user_inf", "history", 99999);
		if (rs != null) {
			List<KeyValue> list = rs.list();
			for (KeyValue keyValue : list) {
				if(Bytes.toString(keyValue.getValue()).equals(art_id)) {
					//存在相同id
					System.out.println("该文章曾查看过,正在删除原历史记录");
					HbaseDao2.deleteRecordWithTimeFilter("User", user_id, "user_inf", "history", keyValue.getTimestamp());
					System.out.println("删除成功");
					break;
				}
				//System.out.println(Bytes.toString(keyValue.getValue()) + "," + keyValue.getTimestamp());
			}
			
		}
		System.out.println("正在添加新的历史记录");
		HbaseDao2.addOneRecord("User", user_id,"user_inf", "history", Bytes.toBytes(art_id));
		System.out.println("添加历史记录成功");
	}
	

	
	//添加用户文章记录
	public void addUserArticle(String user_id, String art_id) throws IOException {
		System.out.println("正在添加新的文章记录");
		HbaseDao2.addOneRecord("User", user_id,"user_inf", "article", Bytes.toBytes(art_id));
		System.out.println("添加文章记录成功");
	}


	
	
}
