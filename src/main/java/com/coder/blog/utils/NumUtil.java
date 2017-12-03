package com.coder.blog.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumUtil {
	public static String Format_8(int number){
		NumberFormat f=new DecimalFormat("00000000");//格式化数字为8位数，以便于Hbase中按顺序存储
		return f.format(number);
	}
}
