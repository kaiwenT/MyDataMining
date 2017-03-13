package com.hust.dm.cluster;

import java.util.ArrayList;
import java.util.List;

import com.hust.dm.util.CosSimilarity;


public class Canopy {

	//原始文本
	private List<String> datalist;
	//分词后的文本
	private List<String[]> seglist;
	
	//原始文本对应向量
	private List<double[]> vectors;
	
	//聚类结果对应的下标
	private List<List<Integer>> resultIndex;

	//	Canopy初始阈值
	private double threshold = 0f;
	
	//	聚类结果类别数量
	private int canopy = 0;
	
	public List<String> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<String> datalist) {
		this.datalist = datalist;
	}

	public List<String[]> getSeglist() {
		return seglist;
	}

	public void setSeglist(List<String[]> seglist) {
		this.seglist = seglist;
	}

	public List<double[]> getVectors() {
		return vectors;
	}

	public void setVectors(List<double[]> vectors) {
		this.vectors = vectors;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public int getCanopy() {
		return canopy;
	}

	public void cluster(){		
		//canopy算法
		resultIndex = new ArrayList<List<Integer>>();
		List<Integer> tmpIndex = null;
		for(int i = 0 ; i < vectors.size() ; i++){
			double[] vector = vectors.get(i);
			// = new ArrayList<Integer>();
			//i = 0 一个类都没有时，直接添加进resultIndex。
			if(i == 0){
//				List<Integer> tmpIndex = resultIndex.get(i);
				tmpIndex = new ArrayList<Integer>();
				tmpIndex.add(i);
				resultIndex.add(tmpIndex);
				continue;
			}
			
			//找到符合相似度要求的类的标志
			boolean isFind = false;
			
			for(int j = 0 ; j < resultIndex.size() ; j++){
				//得到第j个类的向量组
				List<double[]> tmpVectors = getTmpVector(resultIndex.get(j));
				CosSimilarity sim = new CosSimilarity();
				//计算向量与已分的类的向量平均值是否大于阈值，大于则添加到该类
				if(sim.calculate(vector,tmpVectors) > threshold){
					
					tmpIndex = resultIndex.get(j);
					tmpIndex.add(i);
					//把i加到tmpIndex，然后从resultIndex里删除第j个，最后添加tmpIndex到resultIndex
					resultIndex.remove(j);
					resultIndex.add(tmpIndex);
					isFind = true;
					break;
				}
			}
			
			//与前面的类相似度都不符合要求则新建一个类
			if(!isFind){
				tmpIndex = new ArrayList<Integer>();
				tmpIndex.add(i);
				resultIndex.add(tmpIndex);
			}
		}

		canopy = resultIndex.size();
		//显示聚类结果
		showResult();
	}	

	public List<double[]> getTmpVector(List<Integer> list){
		List<double[]> tmp = new ArrayList<double[]>();
		for(int i = 0 ; i < list.size() ; i++){
			tmp.add(vectors.get(list.get(i)));
		}
		return tmp;
	}
	
	/**
	 * 显示分词后的文本结果
	 */
	public void showSeglist(){
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
	public void showVectors(){
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
	public void showResult(){
		System.out.println("Canopy个数:"+canopy);
		for(int i = 0 ; i < resultIndex.size() ; i++){
			List<Integer> tmpIndex = resultIndex.get(i);
			for(int j = 0 ; j < tmpIndex.size() ; j++){
				System.out.println(datalist.get(tmpIndex.get(j)));
			}
			System.out.println();			
		}
	}
}
