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
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

@Repository
public class HbaseDao {
    private static final String QUORUM = "hadoopsrv";//��Ⱥ�����л���
    private static final String CLIENTPORT = "2182";//hbase�˿�
    private static Configuration conf = null;
    private static Connection conn = null;
    private static Admin admin = null;
	public HbaseDao() throws Exception {
		System.out.println("����hbase��...");
		   conf =  HBaseConfiguration.create();  
           conf.set("hbase.zookeeper.quorum", QUORUM);   
           conf.set("hbase.zookeeper.property.clientPort", CLIENTPORT);  
           conn = ConnectionFactory.createConnection(conf);
          
           admin = conn.getAdmin();
		System.out.println("����hbase�ɹ���");
	}

	// ����һ�ű�ָ������������
	public void createTable(String tableName, String[] columnFarily) throws Exception {
		System.out.println("���ڴ�����" + tableName);
		if (admin.tableExists(TableName.valueOf(tableName))) {
			System.out.println(tableName + "���ڣ�");
			System.exit(0);
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
			for(String column : columnFarily)
				tableDesc.addFamily(new HColumnDescriptor(column));
			admin.createTable(tableDesc);
			System.out.println("������ɹ���");
		}
	}
	// ����һ�ű�ָ�����������塢���汾��(5λ��)
	public void createTable(String tableName, String[] columnFarily,int maxversion) throws Exception {
		System.out.println("���ڴ�����" + tableName);
		if (admin.tableExists(TableName.valueOf(tableName))) {
			System.out.println(tableName + "���ڣ�");
			System.exit(0);
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
			for(String column : columnFarily)
				tableDesc.addFamily(new HColumnDescriptor(column).setMaxVersions(maxversion));
			admin.createTable(tableDesc);
			System.out.println("������ɹ���");
		}
	}
	// Hbase��ȡ���еı���Ϣ
	public List getAllTables() {
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
	public boolean addOneRecord(String table, String key, String family, String col, byte[] dataIn) {
		//System.out.println("������ĳ���������һ����¼...");	
		Put put = new Put(key.getBytes());
		put.add(family.getBytes(), col.getBytes(), dataIn);
		
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			 tb.put(put);
			 tb.close();
		//	System.out.println("����������" + key + "�ɹ�������");
			return true;
		} catch (IOException e) {
			System.out.println("����������" + key + "ʧ�ܣ�����");
			return false;
		}
	}

	// Hbase���м�¼��Ϣ�Ĳ�ѯ
	public Result getValueFromKey(String table, String key) {
		System.out.println("���ڲ�ѯ���й���\""+key+"\"�ļ�¼...");
			Get get = new Get(key.getBytes());
		try {
			Table tb = conn.getTable(TableName.valueOf(table));
			Result rs = tb.get(get);
			tb.close();
			if (rs.raw().length == 0) {
				System.out.println("�����ڹؼ���Ϊ" + key + "���У�!");
			} else {
				return rs;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Hbase���м�¼��Ϣ�Ĳ�ѯ
		public Result getValueByQuafilier(String table, String key,String family,String quafilier) {
			System.out.println("���ڲ�ѯ���й���\""+key+","+quafilier+"\"�ļ�¼...");
				Get get = new Get(key.getBytes());
				get.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier));
				
			try {
				Table tb = conn.getTable(TableName.valueOf(table));
				Result rs = tb.get(get);
				tb.close();
				if (rs.raw().length == 0) {
					System.out.println("�����ڹؼ���Ϊ" + key +","+quafilier+ "���У�!");
				} else {
					return rs;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		public Result getValueByQuafilier(String table, String key,String family,String quafilier,int maxversion) throws IOException {
			System.out.println("���ڲ�ѯ���й���\""+key+","+quafilier+"\"�ļ�¼...");
				Get get = new Get(key.getBytes());
				get.setMaxVersions(maxversion);
				get.addColumn(Bytes.toBytes(family), Bytes.toBytes(quafilier));
				
			try {
				Table tb = conn.getTable(TableName.valueOf(table));
				Result rs = tb.get(get);
				tb.close();
				if (rs.raw().length == 0) {
					System.out.println("�����ڹؼ���Ϊ" + key +","+quafilier+ "���У�!");
				} else {
					return rs;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	

	// ��ʾ�������ݣ�ͨ��HTable Scan���ȡ���б����Ϣ
	public ResultScanner getAllData(String tableName) throws Exception {
		System.out.println("ͨ��HTable Scan���ȡ���б����Ϣ��..");
	//	HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan();
		Table tb = conn.getTable(TableName.valueOf(tableName));
		ResultScanner rs = tb.getScanner(scan);
		tb.close();
		for (Result r : rs) {
			List<KeyValue> list = r.list();
			for (KeyValue keyValue : list) {
			System.out.println("Row:" + Bytes.toString(keyValue.getRow()));
			System.out.println("Family:" + Bytes.toString(keyValue.getFamily()));
			System.out.println("Column:"+ Bytes.toString(keyValue.getQualifier()));
			System.out.println("Value:"+ Bytes.toString(keyValue.getValue()));
			System.out.println("TimeStamp:"+new Date(keyValue.getTimestamp()));
			}
		}
		return rs;
	}
	public void scanInRegin(String tableName) throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner rs = table.getScanner(scan);
        for (Result r : rs.next(5)) {
        	 for (Cell cell : r.rawCells()) {
                 System.out.println("Rowkey : " + Bytes.toString(r.getRow())
                         + "   Familiy:Quilifier : "
                         + Bytes.toString(CellUtil.cloneQualifier(cell))
                         + "   Value : "
                         + Bytes.toString(CellUtil.cloneValue(cell))
                         + "   Time : " + cell.getTimestamp());
             }
        }
		
	}

	// Hbase���м�¼��Ϣ��ɾ��
	public boolean deleteRecord(String table, String key) {
		System.out.println("����ɾ������\""+key+"\"����Ϣ..");
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
	public boolean deleteTable(String table) {
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