package com.hust.dm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Excel2List {
private List<String> dataList;
	
	private List<String[]> segList;
	
	private String path = "E:\\test.xlsx";//"E:\\test.xlsx"
	
	public void read(String path){
		if(path != null && !"".equals(path.trim())){
			this.path = path;
		}
		dataList = new ArrayList<String>();
		segList = new ArrayList<String[]>();
		
		dataList = new ArrayList<>();
		ExcelManage em = new ExcelManage();
		File file = new File(this.path);
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			List<String[]> list = em.read(inputStream);
			WordSegment ws = new WordSegment();
			for (int i = 0; i < list.size(); i++) {
				String[] strs = list.get(i);
				String words = strs[3];
				dataList.add(words);
				segList.add(ws.parse(words));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	}

	public List<String> getDataList() {
		return dataList;
	}

	public List<String[]> getSegList() {
		return segList;
	}
	
}
