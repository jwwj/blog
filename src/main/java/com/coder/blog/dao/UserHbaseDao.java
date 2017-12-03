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

	// �����û�������ʷ��¼
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
	//��ѯ�û�д�����¼�¼
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

	
	//����û���ʷ��¼(�Ȳ�ѯԭ���Ƿ������ͬart_id��¼����������ɾ���ٱ��棬�������ڣ�ֱ�ӱ���)
	public void addUserHistory(String user_id,String art_id) throws IOException {
		Result rs = HbaseDao2.getValueByQuafilierWithMaxVersion("User", user_id, "user_inf", "history", 99999);
		if (rs != null) {
			List<KeyValue> list = rs.list();
			for (KeyValue keyValue : list) {
				if(Bytes.toString(keyValue.getValue()).equals(art_id)) {
					//������ͬid
					System.out.println("���������鿴��,����ɾ��ԭ��ʷ��¼");
					HbaseDao2.deleteRecordWithTimeFilter("User", user_id, "user_inf", "history", keyValue.getTimestamp());
					System.out.println("ɾ���ɹ�");
					break;
				}
				//System.out.println(Bytes.toString(keyValue.getValue()) + "," + keyValue.getTimestamp());
			}
			
		}
		System.out.println("��������µ���ʷ��¼");
		HbaseDao2.addOneRecord("User", user_id,"user_inf", "history", Bytes.toBytes(art_id));
		System.out.println("�����ʷ��¼�ɹ�");
	}
	

	
	//����û����¼�¼
	public void addUserArticle(String user_id, String art_id) throws IOException {
		System.out.println("��������µ����¼�¼");
		HbaseDao2.addOneRecord("User", user_id,"user_inf", "article", Bytes.toBytes(art_id));
		System.out.println("������¼�¼�ɹ�");
	}


	
	
}
