package com.hust.dm.cluster;

import java.util.List;

public class KMeans {
		//原始文本
		private List<String> datalist;
		//分词后的文本
		private List<String[]> seglist;
		
		//原始文本对应向量
		private List<double[]> vectors;
		
		//聚类结果对应的下标
		private List<List<Integer>> resultIndex;

		//	KMeans初始K值
		private double k = 0f;
		
		//	聚类结果类别数量
		private int cluster = 0;
		
		/**
		 * KMeans聚类
		 */
		public void cluster(){
			
		}
}
