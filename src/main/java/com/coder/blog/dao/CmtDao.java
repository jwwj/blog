package com.coder.blog.dao;

import java.util.List;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.coder.blog.init.Hbase;
import com.coder.blog.model.Article;
import com.coder.blog.model.Cmt;

public class CmtDao {

	public Cmt selectByRowKey(String cmt_id) {

		HbaseDao hbase = Hbase.conn;

		// hbase.getValueFromKey("TableName","RowKey");
		Result rs = hbase.getValueFromKey("ArticleTable", cmt_id);

		List<KeyValue> list = rs.list();
		Cmt cmt = new Cmt();

		for (KeyValue keyValue : list) {
			// 解析hbase评论数据库
			if (Bytes.toString(keyValue.getFamily()).equals("cmt")) {
				String[] column = Bytes.toString(keyValue.getQualifier()).split("_");
				int cmtnum = Integer.parseInt(column[1]);// 第几个评论
				String cmtuser = Bytes.toString(keyValue.getValue()).substring(0, 36);
				String cmtcont = Bytes.toString(keyValue.getValue()).substring(36,
						Bytes.toString(keyValue.getValue()).length());
				if (column.length == 2) {
					// 一级评论
//					ac.addCmt(new Cmt(cmtnum, cmtcont, cmtuser));
				} else {
					// 二级评论
					int repnum = Integer.parseInt(column[3]);
//					ac.addReplyCmt(new Cmt(cmtnum, repnum, cmtcont, cmtuser));
				}

			}

		}

		return cmt;
	}
}
