package com.hust.dm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

public class WordSegment {

	/**
	 * 中文分词
	 * 包括去停用词
	 * @param str
	 * @return
	 */
	public String[] parse(String str) {
		Result res = ToAnalysis.parse(str);

		List<String> list = new ArrayList<String>();
		List<Term> parse = res.getTerms();
		List<String> list_c = null;
		try {
			list_c = readStopWord("library/StopWordTable.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (Term term : parse) {
             
            boolean flag = true;
 
            String str1 = term.getName().trim();
             
            for (String str_c : list_c) {
//            	System.out.println(str_c);
//            	System.out.println(str_c+";"+str1);
                if (str_c.equals(str1))
                    flag = false;
            }	             	            
             
            if (str1 == "")
                flag = false;
             
            if (flag)
                list.add(str1);
 
        }
		
		String[] words = new String[list.size()];
//		words = (String[]) list.toArray();
		for (int i = 0; i < list.size(); i++) {
			words[i] = list.get(i);
		}
		return words;
	}

	public void setFilter(FilterRecognition filter) {
		filter.insertStopNatures("uj"); // 过滤词性
		filter.insertStopNatures("ul");
		filter.insertStopNatures("null");

		// filter.insertStopWord("我"); //过滤单词
		// filter.insertStopRegex("小.*?"); //支持正则表达式
	}

	 public List<String> readStopWord(String path) throws Exception {
		 
	        List<String> list = new ArrayList<String>();
	 
	        File file = new File(path);
	        InputStreamReader isr = new InputStreamReader(
	                new FileInputStream(file),"UTF-8");
	        BufferedReader bf = new BufferedReader(isr);
	 
	        String stopword = null;
	        while ((stopword = bf.readLine()) != null) {
	            stopword = stopword.trim();
	            list.add(stopword);
	        }
	        bf.close();
	        return list;
	    }
}
