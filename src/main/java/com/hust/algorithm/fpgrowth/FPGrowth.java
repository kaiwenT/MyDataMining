package com.hust.algorithm.fpgrowth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FPGrowth {
	//头指针表name,数量
	private List<HeadNode> headTable;
	//最小支持度
	private int minSup = 1;
	//事务数据集
	private HashMap<List<String>,Integer> dataSet;
	//
	private TreeNode retTree;
	
	
	public FPGrowth(HashMap<List<String>,Integer> dataSet, int minSup) {
		super();
		this.minSup = minSup;
		this.dataSet = dataSet;
		headTable = new ArrayList<>();
	}

	private int indexOf(String term){
		if(term == null || term.equals("")){
			return -1;
		}
		for(HeadNode h : headTable){
			if(h.getName() == term){
				return headTable.indexOf(h);
			}
		}
		return -1;
	}
	
	public void createTree(){
		//遍历数据集获得每个元素项的出现频率
		for(List<String> trans : dataSet.keySet()){
			for(String term : trans){
				int index = indexOf(term);
				if(index >= 0){
					HeadNode h = headTable.get(index);
					h.setCount(h.getCount() + dataSet.get(trans));
				}else{
					HeadNode e = new HeadNode(term, 1, null);
					headTable.add(e);
				}
			}
		}
		//移除不满足最小支持度的元素项
		for(int i = 0 ; i < headTable.size() ; i++){
			if(headTable.get(i).getCount() < minSup){
				headTable.remove(i);
				i--;
			}
		}
		//如果没有元素项满足要求则退出
		if(headTable == null || headTable.size() == 0){
			return;
		}
		//初始化根节点
		retTree = new TreeNode("Null Set", 1, null);
		//
		for(List<String> tranSet : dataSet.keySet()){
			//
			Map<String,Integer> localD = new HashMap<>();
			for(String item : tranSet){
				int index = indexOf(item);
				if(index >= 0){
					localD.put(item, headTable.get(index).getCount());
				}
			}
			
			if(localD.size() > 0){
				//排序
				List<Map.Entry<String, Integer>> list = new ArrayList<>(localD.entrySet());
				Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

					@Override
					public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
						// TODO Auto-generated method stub
						if(o2.getValue() != o1.getValue()){
							return o2.getValue() - o1.getValue();
						}
						return o1.getKey().compareTo(o2.getKey());
					}
				});
				//
				updateTree(list, retTree, headTable, dataSet.get(tranSet));
			}
		}
	}
	
	public void updateTree(List<Entry<String, Integer>> items, TreeNode inTree, List<HeadNode> headerTable, Integer count){
		int index = inTree.hasChild(items.get(0).getKey());
		if( index >= 0){
			TreeNode t = inTree.getChildren().get(index);
			t.inc(count);
		}else{
			inTree.addChild(new TreeNode(items.get(0).getKey(),count, inTree));
			index = inTree.hasChild(items.get(0).getKey());
			if(headerTable.get(0).getHead() == null){
				headerTable.get(0).setHead(inTree.getChildren().get(index));
			}else{
				updateHeader(headerTable.get(0).getHead(), inTree.getChildren().get(index));
			}
		}
		if(items.size() > 1){
			items.remove(0);
			updateTree(items, inTree.getChildren().get(index), headerTable,count);
		}
	}

	private void updateHeader(TreeNode head, TreeNode targetNode) {
		// TODO Auto-generated method stub
		while(head.getNodeLink() != null){
			head = head.getNodeLink();
		}
		head.setNodeLink(targetNode);
	}

	public List<HeadNode> getHeadTable() {
		return headTable;
	}

	public void setHeadTable(List<HeadNode> headTable) {
		this.headTable = headTable;
	}

	public int getMinSup() {
		return minSup;
	}

	public void setMinSup(int minSup) {
		this.minSup = minSup;
	}

	public HashMap<List<String>, Integer> getDataSet() {
		return dataSet;
	}

	public void setDataSet(HashMap<List<String>, Integer> dataSet) {
		this.dataSet = dataSet;
	}

	public TreeNode getRetTree() {
		return retTree;
	}

	public void setRetTree(TreeNode retTree) {
		this.retTree = retTree;
	}
	
	
}
