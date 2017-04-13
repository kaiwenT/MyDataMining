package com.hust.algorithm.fpgrowth;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<TreeNode>{
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
	
	/**
	 * 添加一个子节点
	 * @param c
	 */
	public void addChild(TreeNode c){
		this.children.add(c);
	}
	/**
	 * 以树形打印本节点及子节点信息
	 */
	public void disp(){
		printSpace(ind);
		System.out.println(this.name + " " + this.count);
		ind++;
		for(TreeNode child : this.children){			
			child.disp();
		}
		ind--;
	}
	public void printSpace(int n){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i++){
			sb.append(" ");
		}
		System.out.print(sb);
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

	@Override
	public int compareTo(TreeNode t) {
		// 重写比较方法
		return 0;
	}
	
	
}
