package com.hust.dm.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Tankai
 *
 */
public class TFIDFConverter {
	private List<String> vectorBase;
	
	public void initVectorBase(List<String[]> seglist){
		vectorBase = new ArrayList<String>();
		System.out.println("vectorBase:");
		for(String[] strs : seglist){
			for(String s : strs){
				if(!vectorBase.contains(s)){
					vectorBase.add(s);
					System.out.print(s+" ");
				}
			}
		}
		System.out.println();
	}
	
	
	public List<double[]> convert(List<String[]> seglist){
		initVectorBase(seglist);
		
		List<double[]> vectors = new ArrayList<double[]>(seglist.size());
		
		for(String[] strs : seglist){
			double[] vector = new double[vectorBase.size()];//= vectors.get(seglist.indexOf(strs));
			for(String s : strs){
				//计算每个词的TFIDF值
				double TF = occurTimes(s, strs) / strs.length;
				double IDF = Math.log( seglist.size() / existFileNums(s, seglist) );
				vector[vectorBase.indexOf(s)] = TF * IDF;
			}
			vectors.add(vector);
		}
		
		return vectors;
	}
	
	public double occurTimes(String w, String[] s){
		if(w == null || s == null )
			return 0;
		double t = 0;
		for(String str : s){
			if(str.equals(w))
				t++;
		}
		return t;
	}
	
	public boolean isContain(String w, String[] s){
		if(w == null || s == null )
			return false;
		for(String str : s){
			if(str.equals(w))
				return true;
		}
		return false;
	}
	
	
	public double existFileNums(String w , List<String[]> list){
		if(w == null || list == null || list.size() == 0 )
			return 0;
		double t = 0 ;
		for(String[] str : list){
			
			if(isContain(w,str)){
				t++;
			}
			
		}
		return t;
	}
}
