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
	private static final String QUORUM = "hadoopsrv";// ��Ⱥ�����л���
	private static final String CLIENTPORT = "2182";// hbase�˿�
	private static Configuration conf = null;
	private static Connection conn = null;
	private static Admin admin = null;
	static {
		System.out.println("����hbase��...");
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", QUORUM);
		conf.set("hbase.zookeeper.property.clientPort", CLIENTPORT);
		conf.setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY, 120000);
		try {
			conn = ConnectionFactory.createConnection(conf);
			admin = conn.getAdmin();
			System.out.println("����hbase�ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����hbaseʧ�ܣ�");
		}

	}

	// ����һ�ű�ָ������������
	public static void createTable(String tableName, String[] columnFarily) throws Exception {
		System.out.println("���ڴ�����" + tableName);
		if (admin.tableExists(TableName.valueOf(tableName))) {
			System.out.println(tableName + "���ڣ�");
			System.exit(0);
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
			for (String column : columnFarily)
				tableDesc.addFamily(new HColumnDescriptor(column));
			admin.createTable(tableDesc);
			System.out.println("������ɹ���");
		}
	}

	// Hbase��ȡ���еı���Ϣ
	public static List getAllTables() {
		System.out.println("���ڻ�ȡ���еı���Ϣ...");
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
		System.out.println("��ȡ���еı���Ϣ�ɹ���");
		return tables;
	}

	// Hbase����ĳ���������һ����¼
	public static boolean addOneRecord(String table, String key, String family, String col, byte[] dataIn) {
		System.out.println("������ĳ���������һ����¼...");
		Put put = new Put(key.getBytes());
		put.add(family.getBytes(), col.getBytes(), dataIn);
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			tb.put(put);
			tb.close();
			System.out.println("����������" + key + "�ɹ�������");
			return true;
		} catch (IOException e) {
			System.out.println("����������" + key + "ʧ�ܣ�����");
			return false;
		}
	}

	// Hbase���м�¼��Ϣ�Ĳ�ѯ
	public static Result getValueFromKey(String table, String key) {
		System.out.println("���ڲ�ѯ���й���\"" + key + "\"�ļ�¼...");
		Get get = new Get(key.getBytes());
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("�����ڹؼ���Ϊ" + key + "���У�!");
				return null;
			} else {
				return rs;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Hbase���м�¼��Ϣ�Ĳ�ѯ
	public static Result getValueByQuafilier(String table, String key, String family, String quafilier) {
		System.out.println("���ڲ�ѯ���й���\"" + key + "," + quafilier + "\"�ļ�¼...");
		Get get = new Get(key.getBytes());
		get.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier));

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("�����ڹؼ���Ϊ" + key + "," + quafilier + "���У�!");
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
		System.out.println("���ڲ�ѯ���й���\"" + key + "," + family + "\"�ļ�¼...");
		Get get = new Get(key.getBytes());
		get.addFamily(Bytes.toBytes(family));

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("�����ڹؼ���Ϊ" + key + "," + family + "���У�!");
				return rs;
			} else {
				return rs;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ��ѯmaxvesion�����µ�quafilier����
	public static Result getValueByQuafilierWithMaxVersion(String table, String key, String family, String quafilier,
			int maxversion) throws IOException {
		System.out.println("���ڲ�ѯ���й���\"" + key + "," + quafilier + "\"�ļ�¼...");
		Get get = new Get(key.getBytes());
		get.setMaxVersions(maxversion);
		get.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier));

		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("�����ڹؼ���Ϊ" + key + "," + quafilier + "���У�!");
			} else {
				return rs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ʱ��������� δ����
	public Result getValueByFamilyWithTimeFilter(String table, String key, String family, long timestamps)
			throws IOException {
		System.out.println("���ڲ�ѯ" + table + "���й���" + family + "�ļ�¼...");
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
				System.out.println("�����ڹؼ���Ϊ" + family + "," + timestamps + "���У�!");
			} else {
				return rs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ��ʾ�������ݣ�ͨ��HTable Scan���ȡ���б����Ϣ
	public static ResultScanner getAllData(String tableName) throws Exception {
		System.out.println("ͨ��HTable Scan���ȡ���б����Ϣ��..");
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
		System.out.println("���ڲ���5����¼����");
		Table table = conn.getTable(TableName.valueOf(tableName));
		FamilyFilter ff = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("art")) // ���в�����art���壬���˽��Ϊ��
		);

		Scan scan = new Scan();
		scan.setFilter(ff);

		ResultScanner rs = table.getScanner(scan);
		if (rs != null)
			// System.out.println("����5����¼�ɹ�");
			return rs;
		else
			return null;
	}

	public static ResultScanner scanInReginWithValue(String tableName, String value) throws IOException {
		System.out.println("���ڲ���5����¼����");
		Table table = conn.getTable(TableName.valueOf(tableName));
		
		//List����Ź�������
		List<Filter> filters = new ArrayList<Filter>();
		//��������һ��ֻ����art�У�������cmt
		FamilyFilter filter1 = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("art")) // ���в�����art���壬���˽��Ϊ��
		);
		filters.add(filter1);
		//������������ֻ������ֵΪvalue����
		SingleColumnValueFilter filter2 = new SingleColumnValueFilter(Bytes.toBytes("art"),
				Bytes.toBytes("topic"), CompareOp.EQUAL, Bytes.toBytes(value));
		filters.add(filter2);
		//����List
		FilterList filterList = new FilterList(filters);
		
		Scan scan = new Scan();
		scan.setFilter(filterList);

		ResultScanner rs = table.getScanner(scan);
		if (rs != null)
			// System.out.println("����5����¼�ɹ�");
			return rs;
		else
			return null;
	}

	// Hbase���м�¼��Ϣ��ɾ��
	public static boolean deleteRecordWithTimeFilter(String table, String key, String family, String quafilier,
			long timestamp) {
		System.out.println("����ɾ������\"" + key + "\"," + quafilier + "," + timestamp + "����Ϣ..");
		Delete de = new Delete(key.getBytes());
		// de.setTimestamp(timestamp);
		de.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier), timestamp);
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			tb.delete(de);
			tb.close();
			return true;
		} catch (IOException e) {
			System.out.println("ɾ����¼" + key + "�쳣������");
			return false;
		}
	}

	// Hbase���м�¼��Ϣ��ɾ��
	public static boolean deleteRecord(String table, String key) {
		System.out.println("����ɾ������\"" + key + "\"����Ϣ..");
		Delete de = new Delete(key.getBytes());
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			tb.delete(de);
			tb.close();
			return true;
		} catch (IOException e) {
			System.out.println("ɾ����¼" + key + "�쳣������");
			return false;
		}
	}

	// Hbase�б��ɾ��
	public static boolean deleteTable(String table) {
		System.out.println("����ɾ����...");
		try {
			if (admin.tableExists(TableName.valueOf(table))) {
				admin.disableTable(TableName.valueOf(table));
				admin.deleteTable(TableName.valueOf(table));
				System.out.println("ɾ����" + table + "!!!");
			}
			return true;
		} catch (IOException e) {
			System.out.println("ɾ����" + table + "�쳣!!!");
			return false;
		}
	}

}