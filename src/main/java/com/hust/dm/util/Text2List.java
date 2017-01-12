package com.hust.dm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Text2List {

	private List<String> dataList;
	
	private List<String[]> segList;
	
	public static String PATH = "data";
	
	public void read(){
		dataList = new ArrayList<String>();
		segList = new ArrayList<String[]>();
		
        File file = new File(PATH);
        InputStreamReader isr;
		try {
			isr = new InputStreamReader(
			        new FileInputStream(file),"GBK");
		
        BufferedReader bf = new BufferedReader(isr);
 
        String words = null;
        WordSegment ws = new WordSegment();
        while ((words = bf.readLine()) != null) {
        	words = words.trim();
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
