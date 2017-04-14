package com.hust;

import org.junit.Test;

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
		List<>
	}
}