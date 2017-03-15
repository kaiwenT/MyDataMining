package com.hust.dm.util;

import java.util.List;

public class ShowUtil {

	/**
	 * 显示分词后的文本结果
	 */
	public static void showSeglist(List<String[]> seglist){
		for(String[] s:seglist){
			for(String str:s){
				System.out.print(str+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 显示文本向量空间
	 */
	public static void showVectors(List<double[]> vectors){
		for(double[] v : vectors ){
			for(double d :v){
				System.out.print(String.format("%4.2f", d)+" ");
			}
			System.out.println();
		}
	}
	/**
	 * 显示聚类结果
	 */
	public static void showResult(List<List<Integer>> resultIndex, List<String> datalist){
		for(int i = 0 ; i < resultIndex.size() ; i++){
			List<Integer> tmpIndex = resultIndex.get(i);
			for(int j = 0 ; j < tmpIndex.size() ; j++){
				System.out.println(datalist.get(tmpIndex.get(j)));
			}
			System.out.println();			
		}
	}
}
