package com.hust.dm.test;

import java.util.ArrayList;
import java.util.List;

import com.hust.dm.cluster.Canopy;
import com.hust.dm.util.Excel2List;
import com.hust.dm.util.ExcelWriter;

public class ToExcelTest {

	public static void main(String[] args) {
		listToExcel();
	}
	
	public static void listToExcel(){
		Canopy canopy = new Canopy();
		
		/**
		从文本文件中读取文本，保存到List里
		**/
//		Text2List t2l = new Text2List();
//		t2l.read();
//		datalist = t2l.getDataList();
//		seglist = t2l.getSegList();
		
		/**
		从Excel文件中读取文本，保存到List里
		并进行分词、去停用词
		**/
		Excel2List e2l = new Excel2List();
		
		List<String> test = new ArrayList<>();
		test.add("111");
		test.add("222");
		test.add("333");
				
		ExcelWriter.rowListToExcel("E:\\四川项目\\测试数据\\list.xlsx", test);
	}
}
