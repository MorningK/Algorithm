package com.sysu.ck.tree;

public abstract class BinaryTree {
	protected Node root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public void visit(Node node) {
		System.out.print(node.getWeigth() + " ");
	}
	
	public int size() {
		return size(root);
	}
	// �������Ĵ�С = ���ڵ� + �������Ĵ�С + �������Ĵ�С
	private int size(Node root) {
		if (root == null)
			return 0;
		return 1 + size(root.getLeft()) + size(root.getRight());
	}
	
	public int height() {
		return height(root);
	}
	
	protected int height(Node root) {
		if (root == null)
			return 0;
		int left = 1 + height(root.getLeft());
		int right = 1 + height(root.getRight());
		return left > right ? left : right;
	}
	
	// ����������ȷ��ʸ��ڵ㣬Ȼ�����������������������
	public void PreOrder() {
		PreOrder(root);
		System.out.println();
	}
	private void PreOrder(Node node) {
		if (node == null)
			return;
		visit(node);
		PreOrder(node.getLeft());
		PreOrder(node.getRight());
	}
	
	// ����������ȷ�����������Ȼ���Ǹ��ڵ㣬�����������
	public void InOrder() {
		InOrder(root);
		System.out.println();
	}
	private void InOrder(Node node) {
		if (node == null)
			return;
		InOrder(node.getLeft());
		visit(node);
		InOrder(node.getRight());
	}
	
	// ����������ȷ�����������Ȼ����������������Ǹ��ڵ�
	public void PostOrder() {
		PostOrder(root);
		System.out.println();
	}
	private void PostOrder(Node node) {
		if (node == null)
			return;
		PostOrder(node.getLeft());
		PostOrder(node.getRight());
		visit(node);
	}
	
	public abstract void insert(Node node);
	
	public abstract void delete(Node node);
	
	public abstract boolean contain(Node node);
}
