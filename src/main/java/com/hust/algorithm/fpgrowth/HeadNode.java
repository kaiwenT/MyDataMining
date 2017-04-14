package com.hust.algorithm.fpgrowth;

public class HeadNode {

	private String name;
	
	private int count;
	
	private TreeNode head;

		
	public HeadNode(String name, int count, TreeNode head) {
		super();
		this.name = name;
		this.count = count;
		this.head = head;
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

	public TreeNode getHead() {
		return head;
	}

	public void setHead(TreeNode head) {
		this.head = head;
	}
	
	
}
