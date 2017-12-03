package com.coder.blog.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.coder.blog.init.Hbase;
import com.coder.blog.model.Article;
import com.coder.blog.model.Cmt;
import com.coder.blog.model.User;
import com.coder.blog.utils.NumUtil;
import com.coder.blog.utils.RandomUtil;

public class ArticleDao {

	//��ѯ����
	public Article selectByRowKey(String art_id) {

		Result rs = HbaseDao2.getValueFromKey("ArticleTable", art_id);
		if (rs != null) {
			List<KeyValue> list = rs.list();
			Article ac = new Article();
			ac.setArt_id(art_id);
			for (KeyValue keyValue : list) {

				// ����hbase�������ݿ�
				if (Bytes.toString(keyValue.getFamily()).equals("art")) {
					if (Bytes.toString(keyValue.getQualifier()).equals("abs"))
						ac.setAbs(Bytes.toString(keyValue.getValue()));
					if (Bytes.toString(keyValue.getQualifier()).equals("cont")) {
						ac.setCont(Bytes.toString(keyValue.getValue()));
						ac.setTime(keyValue.getTimestamp());// ��ȡ��������д��ʱ��
					}
					if (Bytes.toString(keyValue.getQualifier()).equals("like"))
						ac.setLike(Bytes.toString(keyValue.getValue()));
					if (Bytes.toString(keyValue.getQualifier()).equals("reading"))
						ac.setReading(Bytes.toString(keyValue.getValue()));
					if (Bytes.toString(keyValue.getQualifier()).equals("title"))
						ac.setTitle(Bytes.toString(keyValue.getValue()));
					if (Bytes.toString(keyValue.getQualifier()).equals("topic"))
						ac.setTopic(Bytes.toString(keyValue.getValue()));
					if (Bytes.toString(keyValue.getQualifier()).equals("user_id"))
						ac.setUser_id(Bytes.toString(keyValue.getValue()));
				} else if (Bytes.toString(keyValue.getFamily()).equals("cmt")) {
					String[] column = Bytes.toString(keyValue.getQualifier()).split("_");// cont_0000001;cont_00000002;cont_00000002_00000001;cont_00000003
					int cmtnum = Integer.parseInt(column[1]);// �ڼ�������
					String cmtuser = Bytes.toString(keyValue.getValue()).substring(0, 36);
					String cmtcont = Bytes.toString(keyValue.getValue()).substring(36,
							Bytes.toString(keyValue.getValue()).length());
					long time = keyValue.getTimestamp();
					if (column.length == 2) {
						// һ������
						
						ac.addCmt(new Cmt(cmtnum, cmtcont, cmtuser, time));
					} else {
						// ��������
						int repnum = Integer.parseInt(column[2]);
						ac.addReplyCmt(new Cmt(cmtnum, repnum, cmtcont, cmtuser, time));
					}
				}
			}
			return ac;
		} else {
			return null;
		}
	}

	//��������
	public String insert(Article article) {
		try {

			System.out.println("���ڷ�������" + article.getTitle());
			String art_id = UUID.randomUUID().toString();
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "title", Bytes.toBytes(article.getTitle()));

			// topic
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "topic", Bytes.toBytes(article.getTopic()));
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "abs", Bytes.toBytes(article.getAbs()));

			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "cont", Bytes.toBytes(article.getCont()));

			// Ϊ�˲��Է���˴������Ķ����͵�������Ϊ0-999�������
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "like",
					Bytes.toBytes("" + RandomUtil.creat(0, 1000)));
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "reading",
					Bytes.toBytes("" + RandomUtil.creat(0, 1000)));
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "user_id", Bytes.toBytes(article.getUser_id()));

			System.out.println("��������" + art_id + "�ɹ�");
			// hbase.getAllData("ArticleTable");
			return art_id;
		} catch (Exception e) {
			return null;
		}
	}

	// �����Ķ���
	public int updateReading(String art_id) {
		System.out.println("��������Ķ���" + art_id);
		Result rs = HbaseDao2.getValueByQuafilier("ArticleTable", art_id, "art", "reading");
		if (rs == null) {
			System.out.println("����Ķ���ʧ��");
			return 0;
		}else {
			List<KeyValue> list = rs.list();
			int reading = 0;
			for (KeyValue keyValue : list) {
				reading = Integer.parseInt(Bytes.toString(keyValue.getValue()));
				System.out.println("Value:" + reading);
			}
			reading++;
			HbaseDao2.addOneRecord("ArticleTable", art_id, "art", "reading", Bytes.toBytes(reading+""));
			return 1;
		}

	}
	
	public int addCmt(Cmt cmt) {
		 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_"+NumUtil.Format_8(cmt.getColnum()),
				 Bytes.toBytes("428e2a56-c952-11e7-9f1b-000c29452861һ������_"+NumUtil.Format_8(cmt.getColnum())));
		return 0;
		
	}

}
