package com.hust.dm.test;

import java.util.List;

import com.hust.dm.cluster.Canopy;
import com.hust.dm.util.Excel2List;
import com.hust.dm.util.TFIDFConverter;
import com.hust.dm.util.Threshold;

public class CanopyTest{
	
	public static void main(String[] args) {
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
		e2l.read("E:\\四川项目\\测试数据\\本国数据2.xlsx");//test
		canopy.setDatalist(e2l.getDataList());
		canopy.setSeglist(e2l.getSegList());
		 

		
		//显示分词的原始文本
//		showSeglist();
		
		//向量转换
		TFIDFConverter converter = new TFIDFConverter();
		List<double[]> vectors = converter.convert(canopy.getSeglist());
		canopy.setVectors(vectors);
		
		//计算阈值
		Threshold t = new Threshold(vectors);
		double thre = t.getThreshold();
		
		
		//初始化canopy阈值
		canopy.setThreshold(thre);
		System.out.println("计算的平均阈值："+canopy.getThreshold());
		
		//进行Canopy聚类
		canopy.cluster();
		
//		showVectors();
		
//		System.out.println("Canopy个数："+canopy.getCanopy());
		canopy.showResult();
	}
}