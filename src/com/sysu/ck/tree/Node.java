package com.sysu.ck.tree;

public class Node {
	private int weigth;
	private Node left;
	private Node right;
	
	public Node(int w) {
		this.weigth = w;
		this.left = null;
		this.right = null;
	}
	
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public int getWeigth() {
		return weigth;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}
}
