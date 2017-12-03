package com.coder.blog.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coder.blog.dao.HbaseDao;
import com.coder.blog.dao.HbaseDao2;
import com.coder.blog.init.Hbase;
import com.coder.blog.model.Article;
import com.coder.blog.model.Cmt;
import com.coder.blog.model.User;
import com.coder.blog.service.IUserService;
import com.coder.blog.utils.NumUtil;
import com.coder.blog.utils.RandomUtil;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:springmvc.xml")
public class TestArtical {

	public void TestGetAtrical() {

		try {
			HbaseDao hbase = new HbaseDao();

			hbase.createTable("student", new String[] { "fam1" });
			Date d1 = new Date();
			hbase.getAllTables();
			hbase.addOneRecord("student", "id1", "fam1", "name", Bytes.toBytes("小明1"));
			hbase.addOneRecord("student", "id1", "fam1", "address", Bytes.toBytes("北京1"));
			hbase.addOneRecord("student", "id1", "fam2", "name2", Bytes.toBytes("小明1.2"));
			hbase.addOneRecord("student", "id1", "fam2", "address2", Bytes.toBytes("北京1.2"));
			hbase.addOneRecord("student", "id2", "fam1", "name", Bytes.toBytes("小明2"));
			hbase.addOneRecord("student", "id2", "fam1", "address", Bytes.toBytes("北京2"));
			hbase.addOneRecord("student", "id3", "fam1", "name", Bytes.toBytes("小明3"));
			hbase.addOneRecord("student", "id3", "fam1", "address", Bytes.toBytes("北京3"));
			hbase.getValueFromKey("student", "id1");
			hbase.getAllData("student");
			Date d2 = new Date();
			hbase.deleteRecord("student", "id1");
			hbase.deleteRecord("student", "id2");
			hbase.deleteRecord("student", "id3");

			Date d2_1 = new Date();
			// AddAtrical(hbase);
			Date d3 = new Date();
			hbase.deleteRecord("student", "id1");
			hbase.deleteRecord("student", "id2");
			hbase.deleteRecord("student", "id3");
			Date d4 = new Date();
			// AddAtrical(hbase);
			Date d5 = new Date();
			hbase.deleteRecord("student", "id1");
			hbase.deleteRecord("student", "id2");
			hbase.deleteRecord("student", "id3");

			hbase.deleteTable("student");

			System.out.println("1:" + new Date(d2.getTime() - d1.getTime()).getTime());
			System.out.println("2:" + new Date(d3.getTime() - d2_1.getTime()).getTime());
			System.out.println("3:" + new Date(d5.getTime() - d4.getTime()).getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ArticleTable: ROW COLUMN+CELL art_id column=art: title,
	 * timestamp=1510639797083, value= 文章标题 art_id column=art: tpoic,
	 * timestamp=1510639797083, value= 文章分类 art_id column=art: cont,
	 * timestamp=1510639797083, value= 文章内容 art_id column=art: like,
	 * timestamp=1510639818345, value= 点赞人数 art_id column=art: reading,
	 * timestamp=1510639813707, value= 阅读量 art_id column=art: user_id,
	 * timestamp=1510639806416, value= 作者uid art_id column=cmt: 1_cont,
	 * timestamp=1510639844307, value= 评论1的内容 art_id column=cmt: 1_user,
	 * timestamp=1510639836546, value= 评论1的用户 art_id column=cmt: 1_reply_cont_1,
	 * timestamp=1510639861185, value= 第一条回复评论1的内容 art_id column=cmt:
	 * 1_reply_user_1, timestamp=1510639855572, value= 第一条回复评论1的用户
	 * 
	 * 
	 */

	public void Test() {
		// System.out.println(new com.coder.blog.utils.UUID());

	}



	public void AddAtrical() {
		try {
			HbaseDao hbase = new HbaseDao();
			hbase.deleteTable("ArticleTable");
			hbase.createTable("ArticleTable", new String[]{"art","cmt"});
			 hbase.getAllTables();
//			 hbase.addOneRecord("表名字", 文章row_id, 列族名cf, 列名a, 值);
			 
			 for (int i=0 ; i<20; i++) {
			 System.out.println("正在插入第"+i+"条数据");
			 String art_id = UUID.randomUUID().toString();
			 hbase.addOneRecord("ArticleTable", art_id, "art", "title",
			 Bytes.toBytes(i+"文章标题文章标题文章标题文章标题"));
			 
			 //topic
			 hbase.addOneRecord("ArticleTable", art_id, "art", "topic",
			 Bytes.toBytes("lol"));
			 hbase.addOneRecord("ArticleTable", art_id, "art", "abs",
					 Bytes.toBytes("文章摘要"));
			 
			 hbase.addOneRecord("ArticleTable", art_id, "art", "cont",
			 Bytes.toBytes("文章内容"));
			 hbase.addOneRecord("ArticleTable", art_id, "art", "like",
			 Bytes.toBytes(""+RandomUtil.creat(0, 1000)));
			 hbase.addOneRecord("ArticleTable", art_id, "art", "reading",
			 Bytes.toBytes(""+RandomUtil.creat(0, 1000)));
			 hbase.addOneRecord("ArticleTable", art_id, "art", "user_id",
			 Bytes.toBytes("d576f726-ccb8-11e7-8055-000c29452861"));
			 
			 
			 addCmt(hbase,art_id,RandomUtil.creat(0, 20),RandomUtil.creat(0, 5));
//			//第一条评论的用户的uuid 和 内容
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_1",
//					 Bytes.toBytes("937fc335-ccb8-11e7-8055-000c29452861评论1的内容"));
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_2",
//					 Bytes.toBytes("937fc335-ccb8-11e7-8055-000c29452861评论2的内容"));
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_3",
//					 Bytes.toBytes("937fc335-ccb8-11e7-8055-000c29452861评论3的内容"));
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_4",
//					 Bytes.toBytes("937fc335-ccb8-11e7-8055-000c29452861评论4的内容"));
//			 
//				//第一条评论的第一个回复的用户的uuid和内容
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_1_1",
//					 Bytes.toBytes("428e2a56-c952-11e7-9f1b-000c29452861第一条回复评论1的内容"));
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_3_1",
//					 Bytes.toBytes("428e2a56-c952-11e7-9f1b-000c29452861第一条回复评论3的内容"));
//			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_3_2",
//					 Bytes.toBytes("428e2a56-c952-11e7-9f1b-000c29452861第二条回复评论3的内容"));
//		
			 
			 
			
			 }
			 System.out.println("插入20条数据成功");
			// hbase.getAllData("ArticleTable");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	

	public void addCmt(HbaseDao hbase,String art_id,int cmtnum,int repnum) throws Exception {
		//HbaseDao hbase = new HbaseDao();
		for(int i=0;i<cmtnum;i++) {
		 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_"+NumUtil.Format_8(i),
				 Bytes.toBytes("428e2a56-c952-11e7-9f1b-000c29452861一级评论_"+NumUtil.Format_8(i)));
		 for(int j=0;j<cmtnum;j++) {
			 hbase.addOneRecord("ArticleTable", art_id, "cmt", "cont_"+NumUtil.Format_8(i)+"_"+NumUtil.Format_8(i),
					 Bytes.toBytes("428e2a56-c952-11e7-9f1b-000c29452861二级评论_"+NumUtil.Format_8(i)+"_"+NumUtil.Format_8(i)));
		 }
		}
	}

	public void getCmtByQualifier() throws Exception {
		HbaseDao hbase = new HbaseDao();
		Result rs = hbase.getValueByQuafilier("ArticleTable", "277e2d1e-a68c-46ee-ae34-04f4c929e1e8", "cmt", "cont_2_1");
		List<KeyValue> list = rs.list();
		for (KeyValue keyValue : list) {
			System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
			System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
			System.out.println("Column:"+ Bytes.toString(keyValue.getQualifier()));
			System.out.println("Value:"+ Bytes.toString(keyValue.getValue()));
			System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
		
		}
	}
	

	public void pageCmt() throws Exception {
		HbaseDao hbase = new HbaseDao();
		int pageSize = 5;
		int pageNum = 2;
		int cmtStart = (pageNum-1)*pageSize+1;
		int repageSize = 5;
		int repageNum = 3;
		int repStart = (repageNum - 1)*repageSize+1;
		for(int i=cmtStart;i<cmtStart+pageSize;i++) {
			Result rs = hbase.getValueByQuafilier("ArticleTable", "d1c406d8-0fab-4190-b47c-67a9a2294c8e", "cmt", "cont_"+i);
			if(rs.isEmpty()) {
				continue;
			}else {
				System.out.println(Bytes.toString(rs.getValue(Bytes.toBytes("cmt"),Bytes.toBytes("cont_"+i))));	
			}
			for(int j=repStart;j<repStart+pageSize;j++) {//二级评论
				Result rs2 = hbase.getValueByQuafilier("ArticleTable", "d1c406d8-0fab-4190-b47c-67a9a2294c8e", "cmt", "cont_"+i+"_"+j);
			if(!rs2.isEmpty())
				System.out.println(Bytes.toString(rs2.getValue(Bytes.toBytes("cmt"),Bytes.toBytes("cont_"+i+"_"+j))));
		}
		}
		
	}
	@Test
	public void scan5() throws IOException {
		ResultScanner r = HbaseDao2.scanInReginWithValue("ArticleTable","food");
		for(Result rs:r.next(5)) {
		List<KeyValue> list = rs.list();
		Article ac = new Article();

		for (KeyValue keyValue : list) {
		//	System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
		//	System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
		//	System.out.println("Column:" + Bytes.toString(keyValue.getQualifier()));
			System.out.println("Row:" + Bytes.toString(keyValue.getRow())+"Column:" + Bytes.toString(keyValue.getQualifier())+"Value:" + Bytes.toString(keyValue.getValue()));
		//	 System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
			
	
		}
		}
	}
	
	
	
	
	
	public void readCmt() {
		Result rs = HbaseDao2.getValueFromKey("ArticleTable", "01b380f5-d8c3-4fd7-a3a4-26c473e7a397");

		List<KeyValue> list = rs.list();
		Article ac = new Article();

		for (KeyValue keyValue : list) {

			//解析hbase文章数据库
			if (Bytes.toString(keyValue.getFamily())  .equals("art")) {
				if(Bytes.toString(keyValue.getQualifier()).equals("abs")) ac.setAbs(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("cont")) ac.setCont(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("like")) ac.setLike(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("reading")) ac.setReading(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("title")) ac.setTitle(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("topic")) ac.setTopic(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("user_id")) ac.setUser_id(Bytes.toString(keyValue.getValue()));
			} else if (Bytes.toString(keyValue.getFamily()).equals("cmt")) {
				String[] column = Bytes.toString(keyValue.getQualifier()).split("_");//cont_1;cont_2;cont_2_1;cont_3
				int cmtnum = Integer.parseInt(column[1]);//第几个评论
				String cmtuser = Bytes.toString(keyValue.getValue()).substring(0, 36);
				String cmtcont = Bytes.toString(keyValue.getValue()).substring(36, Bytes.toString(keyValue.getValue()).length());	
				if(column.length==2) {
					//一级评论
					ac.addCmt(new Cmt(cmtnum,cmtcont,cmtuser));
				}else {
					//二级评论
					int repnum = Integer.parseInt(column[2]);
					ac.addReplyCmt(new Cmt(cmtnum,repnum,cmtcont,cmtuser));
				}
				
			
			}
			
			

		}
		
		System.out.println("ac.getAbs():"+ac.getAbs());
		System.out.println("ac.getCont():"+ac.getCont());
		System.out.println("ac.getLike():"+ac.getLike());
		System.out.println("ac.getReading():"+ac.getReading());
		System.out.println("ac.getTitle():"+ac.getTitle());
		System.out.println("ac.getTopic():"+ac.getTopic());
		System.out.println("ac.getUser_id():"+ac.getUser_id());
		
		for(Cmt cmt : ac.getCmtlist()) {
			  System.out.println("cmt.getColnum():"+cmt.getColnum());
			  System.out.println("cmt.getCont():"+cmt.getCont());
			  System.out.println("cmt.getUser():"+cmt.getUser());
			}
		for(Cmt cmt : ac.getRepcmtlist()) {
			  System.out.println("repcmt.getColnum():"+cmt.getColnum());
			  System.out.println("repcmt.getRepnum():"+cmt.getRepnum());
			  System.out.println("repcmt.getCont():"+cmt.getCont());
			  System.out.println("repcmt.getUser():"+cmt.getUser());
			}
		
	}
	

	
	public void scanAtrical() throws Exception {
		HbaseDao hbase = new HbaseDao();
		
		 hbase.getAllTables();

		 hbase.getAllData("ArticleTable");
	}
	

	public void removeArticle() throws Exception {
		HbaseDao hbase = new HbaseDao();
		hbase.deleteTable("ArticleTable");
//		ResultScanner rs = hbase.getAllData("ArticleTable");
//		for (Result r : rs) {
//			List<KeyValue> list = r.list();
//			for (KeyValue keyValue : list) {
//			System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
//		//	System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
//		//	System.out.println("Column:"+ Bytes.toString(keyValue.getQualifier()));
//		//	System.out.println("Value:"+ Bytes.toString(keyValue.getValue()));
//			//System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
//			}
//		}
//		hbase.deleteRecord("ArticleTable", "art");
//		hbase.deleteRecord("ArticleTable", "cmt");
//		hbase.deleteRecord("student", "id3");
	}
	

	public void readArticle() throws Exception {
		
		HbaseDao hbase = new HbaseDao();
		
		Result rs = hbase.getValueFromKey("ArticleTable", "ad6aa239-b3f0-48a3-a31f-7a37aa8c168c");

		List<KeyValue> list = rs.list();
		Article ac = new Article();

		for (KeyValue keyValue : list) {
//			System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
//			System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
//			System.out.println("Column:" + Bytes.toString(keyValue.getQualifier()));
//			System.out.println("Value:" + Bytes.toString(keyValue.getValue()));
			// System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
			
			//解析hbase文章数据库
			if (Bytes.toString(keyValue.getFamily())  .equals("art")) {
				if(Bytes.toString(keyValue.getQualifier()).equals("abs")) ac.setAbs(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("cont")) ac.setCont(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("like")) ac.setLike(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("reading")) ac.setReading(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("title")) ac.setTitle(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("topic")) ac.setTopic(Bytes.toString(keyValue.getValue()));
				if(Bytes.toString(keyValue.getQualifier()).equals("user_id")) ac.setUser_id(Bytes.toString(keyValue.getValue()));
			} else if (Bytes.toString(keyValue.getFamily()).equals("cmt")) {
				String[] column = Bytes.toString(keyValue.getQualifier()).split("_");
				int cmtnum = Integer.parseInt(column[1]);//第几个评论
				String cmtuser = Bytes.toString(keyValue.getValue()).substring(0, 36);
				String cmtcont = Bytes.toString(keyValue.getValue()).substring(36, Bytes.toString(keyValue.getValue()).length());	
				if(column.length==2) {
					//一级评论
					ac.addCmt(new Cmt(cmtnum,cmtcont,cmtuser));
				}else {
					//二级评论
					int repnum = Integer.parseInt(column[3]);
					ac.addReplyCmt(new Cmt(cmtnum,repnum,cmtcont,cmtuser));
				}
				
			
			}
			
//			if (Bytes.toString(keyValue.getFamily()) .equals("art") ) {
//				System.out.println("==================art==================");
//				System.out.println("Fam:"+Bytes.toString(keyValue.getQualifier()));
//				System.out.println("Value:"+Bytes.toString(keyValue.getValue()));
//				System.out.println("=======================================");
//			}else if(Bytes.toString(keyValue.getFamily()).equals("cmt")) {
//				System.out.println("==================cmt==================");
//				System.out.println("Fam:"+Bytes.toString(keyValue.getQualifier()));
//				System.out.println("Value:"+Bytes.toString(keyValue.getValue()));
//				System.out.println("=======================================");
//			}
			

		}
		
		System.out.println("ac.getAbs():"+ac.getAbs());
		System.out.println("ac.getCont():"+ac.getCont());
		System.out.println("ac.getLike():"+ac.getLike());
		System.out.println("ac.getReading():"+ac.getReading());
		System.out.println("ac.getTitle():"+ac.getTitle());
		System.out.println("ac.getTopic():"+ac.getTopic());
		System.out.println("ac.getUser_id():"+ac.getUser_id());
		
		for(Cmt cmt : ac.getCmtlist()) {
			  System.out.println("cmt.getColnum():"+cmt.getColnum());
			  System.out.println("cmt.getCont():"+cmt.getCont());
			  System.out.println("cmt.getUser():"+cmt.getUser());
			}
		for(Cmt cmt : ac.getRepcmtlist()) {
			  System.out.println("repcmt.getColnum():"+cmt.getColnum());
			  System.out.println("repcmt.getRepnum():"+cmt.getRepnum());
			  System.out.println("repcmt.getCont():"+cmt.getCont());
			  System.out.println("repcmt.getUser():"+cmt.getUser());
			}
		
	}

}
