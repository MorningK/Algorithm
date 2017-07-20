package com.sysu.ck.graph;

public class Node implements Comparable<Node> {
	public Node() {
		x = 0;
		y = 0;
	}
	public Node(int xx, int yy) {
		x = xx;
		y = yy;
		weigth = 0;
		parent = null;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Node getParent() {
		return parent;
	}
	public int getWeigth() {
		return weigth;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	private int x;
	private int y;
	private int weigth;
	private Node parent;
	
	@Override
	public int compareTo(Node o) {
		return this.getWeigth() - o.getWeigth();
	}
	
}
