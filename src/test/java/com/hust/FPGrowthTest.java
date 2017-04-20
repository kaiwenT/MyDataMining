package com.hust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.hust.algorithm.fpgrowth.FPGrowth;
import com.hust.algorithm.fpgrowth.TreeNode;

public class FPGrowthTest{
	
	@Test
	public void testPrintTree(){
		TreeNode sj = new TreeNode("smartphone", 3 , null);
		TreeNode p = new TreeNode("apple", 3, null);
		TreeNode c1 = new TreeNode("iphone5", 5, null);
		TreeNode c2 = new TreeNode("iphone7", 2, null);
		TreeNode h = new TreeNode("huawei",4,null);
		sj.getChildren().add(p);
		sj.getChildren().add(h);
		p.getChildren().add(c1);
		p.getChildren().add(c2);
		sj.disp();
	}
	
	@Test
	public void testCreateTree(){
		String[][] simpData = {
				{"e","m","q","s","t","y","x","z"},
				{"x","s","r","o","n"},
				{"s","u","t","w","v","y","x","z"},
				{"q","p","r","t","y","x","z"},
				{"h","r","z","j","p"},				
				{"z"}				
				};
//		String[][] simpData = {
//				{"f","a","c","d","g","i","m","p"},
//				{"a","b","c","f","l","m","o"},
//				{"b","f","h","j","o"},
//				{"b","c","k","s","p"},
//				{"a","f","c","e","l","p","m","n"}};
		HashMap<List<String>,Integer> dataSet = new HashMap<>();
		for(String[] items : simpData){
			List<String> list = new ArrayList<>();
			for(String s : items){
				list.add(s);
			}
			dataSet.put(list, 1);
		}
		
		FPGrowth fpgrowth = new FPGrowth(dataSet,3);
		fpgrowth.createTree();
		fpgrowth.getRetTree().disp();
	}
	
}