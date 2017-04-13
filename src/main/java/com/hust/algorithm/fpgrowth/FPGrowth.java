package com.hust.algorithm.fpgrowth;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FPGrowth {
	//头指针表name,数量
	private HashMap<String,Integer> headTable;
	//最小支持度
	private int minSup = 1;
	//事务数据集
	private List<List<String>> dataSet;
	//
	private TreeNode retTree;
	
	
	public FPGrowth(int minSup, List<List<String>> dataSet) {
		super();
		this.minSup = minSup;
		this.dataSet = dataSet;
		headTable = new HashMap<>();
	}

	public void createTree(){
		//遍历数据集获得每个元素项的出现频率
		for(List<String> trans : dataSet){
			for(String term : trans){
				if(headTable.containsKey(term)){
					headTable.replace(term, headTable.get(term), 1 + headTable.get(term));
				}else{
					headTable.put(term, 0);
				}
			}
		}
		//移除不满足最小支持度的元素项
		for(String k : headTable.keySet()){
			if(headTable.get(k) < minSup){
				headTable.remove(k);
			}
		}
		//如果没有元素项满足要求则退出
		Set<String> freqTermSet = headTable.keySet();
		if(freqTermSet == null || freqTermSet.size() == 0){
			return;
		}
		//初始化根节点
		retTree = new TreeNode("Null Set", 1, null);
		
	}
	
	public void updateTree(){
		
	}
}
