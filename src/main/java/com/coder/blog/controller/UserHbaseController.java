package com.coder.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.model.Article;
import com.coder.blog.model.User;
import com.coder.blog.model.UserHbase;
import com.coder.blog.service.IUserHbaseService;
import com.coder.blog.service.IUserService;
import com.coder.blog.utils.Msg;

@Controller
public class UserHbaseController {
	@Autowired
	IUserService userservice;
	@Autowired
	IUserHbaseService userhaseservice;

	@RequestMapping(value = "/adduserhistory", method = RequestMethod.GET)
	public @ResponseBody Msg AddUserHistory(String user_id, String art_id) throws IOException {
		System.out.println("��¼�û���ʷ:" + user_id + "," + art_id);// ����ԭ���ļ�¼�����������ͬ��value����ɾ���ٲ����µļ�¼
		userhaseservice.addUserHistory(user_id, art_id);
		return Msg.msg(501, "������ʷ��¼�ɹ�");
	}

	@RequestMapping(value = "/userhistroy", method = RequestMethod.GET)
	public @ResponseBody Article[] UserHistory(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("��ѯ�û�����ʷ�����¼�¼");// ����ԭ���ļ�¼�����������ͬ��value����ɾ���ٲ����µļ�¼
		HttpSession session = request.getSession(true);
		User us = (User) session.getAttribute("user");
		UserHbase uh = userhaseservice.selectUserHistoryById(us.getUser_id());// �鵽�û���������������ʱ�������ȿ�����ǰ���󿴵��ں�
		if (uh == null) {
			return null;
		} else {
			Article[] ac = new Article[uh.getHistory().size()];
			int j = 0;
			// ����ȡ���û���ʷ��¼
			Map<String, Object> map = uh.getHistory();
			ListIterator<Entry<String, Object>> i = new ArrayList<Entry<String, Object>>(map.entrySet())
					.listIterator(map.size());
			while (i.hasPrevious()) {
				Entry<String, Object> entry = i.previous();
				System.out.println(entry.getKey() + ":" + entry.getValue());
				Result r = HbaseDao2.getValueByFamily("ArticleTable", entry.getKey(), "art");

				ac[j] = new Article();
				for (Cell cell : r.rawCells()) {
					String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
					String value = Bytes.toString(CellUtil.cloneValue(cell));
					String rowkey = Bytes.toString(r.getRow());
					long time = cell.getTimestamp();

					if (qualifier.equals("abs"))
						ac[j].setAbs(value);

					if (qualifier.equals("cont")) {
						
						ac[j].setCont(value);
						ac[j].setTime(time);
						ac[j].setArt_id(rowkey);
					}
					if (qualifier.equals("like"))
						ac[j].setLike(value);
					if (qualifier.equals("reading"))
						ac[j].setReading(value);
					if (qualifier.equals("title"))
						ac[j].setTitle(value);
					if (qualifier.equals("topic"))
						ac[j].setTopic(value);
					if (qualifier.equals("user_id")) {
						ac[j].setUser_id(value);
						ac[j].setUser_name(userservice.getUserById(ac[j].getUser_id()).getUser_name());
					}
				}
				j++;

			}

			return ac;
		}
	}

	@RequestMapping(value = "/userarticle", method = RequestMethod.GET)
	public @ResponseBody Article[] UserArticle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("��ѯ�û������¼�¼");// ����ԭ���ļ�¼�����������ͬ��value����ɾ���ٲ����µļ�¼
		HttpSession session = request.getSession(true);
		User us = (User) session.getAttribute("user");
		UserHbase uh = userhaseservice.selectUserArticleById(us.getUser_id());// �鵽�û�д����������ʱ�������ȿ�����ǰ���󿴵��ں�
		if (uh == null) {
			return null;
		} else {
			Article[] ac = new Article[uh.getHistory().size()];
			int j = 0;
			// ����ȡ���û���ʷ��¼
			Map<String, Object> map = uh.getHistory();
			ListIterator<Entry<String, Object>> i = new ArrayList<Entry<String, Object>>(map.entrySet())
					.listIterator(map.size());
			while (i.hasPrevious()) {
				Entry<String, Object> entry = i.previous();
				System.out.println(entry.getKey() + ":" + entry.getValue());
				Result r = HbaseDao2.getValueByFamily("ArticleTable", entry.getKey(), "art");

				ac[j] = new Article();
				for (Cell cell : r.rawCells()) {
					String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
					String value = Bytes.toString(CellUtil.cloneValue(cell));
					String rowkey = Bytes.toString(r.getRow());
					long time = cell.getTimestamp();

					if (qualifier.equals("abs"))
						ac[j].setAbs(value);

					if (qualifier.equals("cont")) {
						
						ac[j].setCont(value);
						ac[j].setTime(time);
						ac[j].setArt_id(rowkey);
					}
					if (qualifier.equals("like"))
						ac[j].setLike(value);
					if (qualifier.equals("reading"))
						ac[j].setReading(value);
					if (qualifier.equals("title"))
						ac[j].setTitle(value);
					if (qualifier.equals("topic"))
						ac[j].setTopic(value);
					if (qualifier.equals("user_id")) {
						ac[j].setUser_id(value);
						ac[j].setUser_name(userservice.getUserById(ac[j].getUser_id()).getUser_name());
					}
				}
				j++;

			}

			return ac;
		}
	}

}
