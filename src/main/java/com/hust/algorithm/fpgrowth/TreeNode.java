package com.hust.algorithm.fpgrowth;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	//
	private String name;
	//
	private int count;
	//
	private TreeNode nodeLink;
	//
	private TreeNode parent;
	//
	private List<TreeNode> children;
	
	private static int ind = 1;
	
	public TreeNode(String name, int count, TreeNode parent) {
		super();
		this.name = name;
		this.count = count;
		this.nodeLink = null;
		this.parent = parent;
		this.children = new ArrayList<TreeNode>();
	}

	public void inc(int num){
		this.count += num;
	}
	
	public void disp(){
		printSpace(ind);
		System.out.println(this.name + " " + this.count);
		for(){
			
		}
	}
	public void printSpace(int n){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i++){
			sb.append(" ");
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public TreeNode getNodeLink() {
		return nodeLink;
	}

	public void setNodeLink(TreeNode nodeLink) {
		this.nodeLink = nodeLink;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
}
