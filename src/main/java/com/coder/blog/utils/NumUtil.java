package com.coder.blog.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumUtil {
	public static String Format_8(int number){
		NumberFormat f=new DecimalFormat("00000000");//��ʽ������Ϊ8λ�����Ա���Hbase�а�˳��洢
		return f.format(number);
	}
}
