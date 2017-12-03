package com.coder.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.init.LoadIndex;
import com.coder.blog.model.Article;
import com.coder.blog.service.IUserService;
import com.coder.blog.utils.Msg;

@Controller
public class TopicController {

	@Autowired
	IUserService userservice;

	@RequestMapping(value = "/topicnextpage", method = RequestMethod.GET)
	public @ResponseBody Object welcomeFile(String topic,int change,HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		//change==1表示需重新请求一个topic指针，==0表示topic没有切换
		if (session.getAttribute("TopicResultScanner"+topic) == null||change==1) {

			ResultScanner rs = HbaseDao2.scanInReginWithValue("ArticleTable",topic);
			if(rs!=null) 
				session.setAttribute("TopicResultScanner"+topic, rs);
			else
				session.setAttribute("TopicResultScanner"+topic, "none");
		}
		if (session.getAttribute("TopicResultScanner"+topic).equals("none")) {
			return Msg.msg(401, "没有更多文章");
		} else {
			System.out.println("查询成功！");
			ResultScanner rs = (ResultScanner) session.getAttribute("TopicResultScanner"+topic);
			Article[] ac = new Article[5];

			int j = 0;
			for (Result r : rs.next(5)) {
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
