package com.coder.blog.controller;

import java.io.IOException;
import java.util.Date;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.init.LoadIndex;
import com.coder.blog.model.Article;
import com.coder.blog.service.IUserService;
import com.coder.blog.utils.AjaxRedirect;
import com.coder.blog.utils.Msg;
import com.jcraft.jsch.Session;

@Controller
public class IndexController {
	@Autowired
	IUserService userservice;

	@RequestMapping(value = "/indexnextpage", method = RequestMethod.GET)
	public @ResponseBody Object welcomeFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("IndexResultScanner") == null) {

			LoadIndex li = (LoadIndex) session.getAttribute("loadindex");
			System.out.println("等待查询中。。");
			li.join();// 等待加载完成后再执行之后的操作

		}
		if (session.getAttribute("IndexResultScanner").equals("none")) {
			return Msg.msg(401, "没有更多文章");
		} else {
			System.out.println("查询成功！");
			ResultScanner rs = (ResultScanner) session.getAttribute("IndexResultScanner");
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
