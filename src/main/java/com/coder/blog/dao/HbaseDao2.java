package com.coder.blog.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.TimestampsFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

@Repository
public class HbaseDao2 {
	private static final String QUORUM = "hadoopsrv";// 集群中所有机器
	private static final String CLIENTPORT = "2182";// hbase端口
	private static Configuration conf = null;
	private static Connection conn = null;
	private static Admin admin = null;
	static {
		System.out.println("连接hbase中...");
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", QUORUM);
		conf.set("hbase.zookeeper.property.clientPort", CLIENTPORT);
		conf.setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY, 120000);
		try {
			conn = ConnectionFactory.createConnection(conf);
			admin = conn.getAdmin();
			System.out.println("连接hbase成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接hbase失败！");
		}

	}

	// 创建一张表，指定表名，列族
	public static void createTable(String tableName, String[] columnFarily) throws Exception {
		System.out.println("正在创建表：" + tableName);
		if (admin.tableExists(TableName.valueOf(tableName))) {
			System.out.println(tableName + "存在！");
			System.exit(0);
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
			for (String column : columnFarily)
				tableDesc.addFamily(new HColumnDescriptor(column));
			admin.createTable(tableDesc);
			System.out.println("创建表成功！");
		}
	}

	// Hbase获取所有的表信息
	public static List getAllTables() {
		System.out.println("正在获取所有的表信息...");
		List<String> tables = null;
		if (admin != null) {
			try {
				HTableDescriptor[] allTable = admin.listTables();
				if (allTable.length > 0)
					tables = new ArrayList<String>();
				for (HTableDescriptor hTableDescriptor : allTable) {
					tables.add(hTableDescriptor.getNameAsString());
					System.out.println(hTableDescriptor.getNameAsString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("获取所有的表信息成功！");
		return tables;
	}

	// Hbase中往某个表中添加一条记录
	public static boolean addOneRecord(String table, String key, String family, String col, byte[] dataIn) {
		System.out.println("正在往某个表中添加一条记录...");
		Put put = new Put(key.getBytes());
		put.add(family.getBytes(), col.getBytes(), dataIn);
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			tb.put(put);
			tb.close();
			System.out.println("插入数据条" + key + "成功！！！");
			return true;
		} catch (IOException e) {
			System.out.println("插入数据条" + key + "失败！！！");
			return false;
		}
	}

	// Hbase表中记录信息的查询
	public static Result getValueFromKey(String table, String key) {
		System.out.println("正在查询表中关于\"" + key + "\"的记录...");
		Get get = new Get(key.getBytes());
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("不存在关键字为" + key + "的行！!");
				return null;
			} else {
				return rs;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Hbase表中记录信息的查询
	public static Result getValueByQuafilier(String table, String key, String family, String quafilier) {
		System.out.println("正在查询表中关于\"" + key + "," + quafilier + "\"的记录...");
		Get get = new Get(key.getBytes());
		get.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier));

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("不存在关键字为" + key + "," + quafilier + "的行！!");
				return rs;
			} else {
				return rs;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Result getValueByFamily(String table, String key, String family) {
		System.out.println("正在查询表中关于\"" + key + "," + family + "\"的记录...");
		Get get = new Get(key.getBytes());
		get.addFamily(Bytes.toBytes(family));

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("不存在关键字为" + key + "," + family + "的行！!");
				return rs;
			} else {
				return rs;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询maxvesion条最新的quafilier数据
	public static Result getValueByQuafilierWithMaxVersion(String table, String key, String family, String quafilier,
			int maxversion) throws IOException {
		System.out.println("正在查询表中关于\"" + key + "," + quafilier + "\"的记录...");
		Get get = new Get(key.getBytes());
		get.setMaxVersions(maxversion);
		get.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier));

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("不存在关键字为" + key + "," + quafilier + "的行！!");
			} else {
				return rs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 时间戳过滤器 未测试
	public Result getValueByFamilyWithTimeFilter(String table, String key, String family, long timestamps)
			throws IOException {
		System.out.println("正在查询" + table + "表中关于" + family + "的记录...");
		Get get = new Get(key.getBytes());
		get.addFamily(Bytes.toBytes(family));
		List<Long> ts = new ArrayList<Long>();
		ts.add(new Long(timestamps));
		Filter filter = new TimestampsFilter(ts);
		get.setFilter(filter);

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("不存在关键字为" + family + "," + timestamps + "的行！!");
			} else {
				return rs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 显示所有数据，通过HTable Scan类获取已有表的信息
	public static ResultScanner getAllData(String tableName) throws Exception {
		System.out.println("通过HTable Scan类获取已有表的信息中..");
		// HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan();
		Table tb = conn.getTable(TableName.valueOf(tableName));
		ResultScanner rs = tb.getScanner(scan);
		tb.close();
		for (Result r : rs) {
			List<KeyValue> list = r.list();
			for (KeyValue keyValue : list) {
				System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
				System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
				System.out.println("Column:" + Bytes.toString(keyValue.getQualifier()));
				System.out.println("Value:" + Bytes.toString(keyValue.getValue()));
				System.out.println("TimeStamp:" + new Date(keyValue.getTimestamp()));
			}
		}
		return rs;
	}

	public static ResultScanner scanInRegin(String tableName) throws IOException {
		System.out.println("正在查找5条记录。。");
		Table table = conn.getTable(TableName.valueOf(tableName));
		FamilyFilter ff = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("art")) // 表中不存在art列族，过滤结果为空
		);

		Scan scan = new Scan();
		scan.setFilter(ff);

		ResultScanner rs = table.getScanner(scan);
		if (rs != null)
			// System.out.println("查找5条记录成功");
			return rs;
		else
			return null;
	}

	public static ResultScanner scanInReginWithValue(String tableName, String value) throws IOException {
		System.out.println("正在查找5条记录。。");
		Table table = conn.getTable(TableName.valueOf(tableName));
		
		//List：存放过滤条件
		List<Filter> filters = new ArrayList<Filter>();
		//过滤条件一：只查找art列，不返回cmt
		FamilyFilter filter1 = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("art")) // 表中不存在art列族，过滤结果为空
		);
		filters.add(filter1);
		//过滤条件二：只查找列值为value的列
		SingleColumnValueFilter filter2 = new SingleColumnValueFilter(Bytes.toBytes("art"),
				Bytes.toBytes("topic"), CompareOp.EQUAL, Bytes.toBytes(value));
		filters.add(filter2);
		//过滤List
		FilterList filterList = new FilterList(filters);
		
		Scan scan = new Scan();
		scan.setFilter(filterList);

		ResultScanner rs = table.getScanner(scan);
		if (rs != null)
			// System.out.println("查找5条记录成功");
			return rs;
		else
			return null;
	}

	// Hbase表中记录信息的删除
	public static boolean deleteRecordWithTimeFilter(String table, String key, String family, String quafilier,
			long timestamp) {
		System.out.println("正在删除表中\"" + key + "\"," + quafilier + "," + timestamp + "的信息..");
		Delete de = new Delete(key.getBytes());
		// de.setTimestamp(timestamp);
		de.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier), timestamp);
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			tb.delete(de);
			tb.close();
			return true;
		} catch (IOException e) {
			System.out.println("删除记录" + key + "异常！！！");
			return false;
		}
	}

	// Hbase表中记录信息的删除
	public static boolean deleteRecord(String table, String key) {
		System.out.println("正在删除表中\"" + key + "\"的信息..");
		Delete de = new Delete(key.getBytes());
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			tb.delete(de);
			tb.close();
			return true;
		} catch (IOException e) {
			System.out.println("删除记录" + key + "异常！！！");
			return false;
		}
	}

	// Hbase中表的删除
	public static boolean deleteTable(String table) {
		System.out.println("正在删除表...");
		try {
			if (admin.tableExists(TableName.valueOf(table))) {
				admin.disableTable(TableName.valueOf(table));
				admin.deleteTable(TableName.valueOf(table));
				System.out.println("删除表" + table + "!!!");
			}
			return true;
		} catch (IOException e) {
			System.out.println("删除表" + table + "异常!!!");
			return false;
		}
	}

}