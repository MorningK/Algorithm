package com.sysu.ck.tree;

import java.util.Random;

public class BinarySearchTree extends BinaryTree {
	// ���ڲ��ҵ������ң��ڵ�ʱ���丸�ڵ�Ĵ���
	private boolean det;
	
	public BinarySearchTree() {
		super();
		det = false;
	}
	// ע����ڵ�����⴦��
	@Override
	public void insert(Node node) {
		Node one = insert(root, node);
		if (root == null)
			root = one;
	}
	// ���ϵıȽϴ�С��ֱ���ҵ�һ�����ʵĲ���λ��
	// ע��java���﷨
	private Node insert(Node root, Node node) {
		if (root == null) {
			return node;
		} else {
			if (root.getWeigth() > node.getWeigth()) {
				Node one = insert(root.getLeft(), node);
				if (one != null)
					root.setLeft(one);
				return null;
			} else if (root.getWeigth() < node.getWeigth()) {
				Node one = insert(root.getRight(), node);
				if (one != null)
					root.setRight(one);
				return null;
			} else {
				System.out.println("this node is already in the tree.");
				return null;
			}
		}
	}

	@Override
	public void delete(Node node) {
		delete(root, node);
	}
	// �ҵ��ڵ�����λ�ú󣬽����û�Ϊ�����������ҽڵ����������������ڵ�
	// ע����ڵ���������
	private Node delete(Node root, Node node) {
		if (root == null) {
			System.out.println("can not find this node.");
			return null;
		}
		else if (root.getWeigth() == node.getWeigth())
		{
			if (root.getLeft() == null && root.getRight() == null) {
				return root;
			} else {
				Node sub = null;
				if (root.getLeft() != null)
					sub = findMaxRight(root.getLeft());
				else
					sub = findMaxLeft(root.getRight());
				if (root.getLeft() == null || sub.getWeigth() != root.getLeft().getWeigth() )
					sub.setLeft(root.getLeft());
				if (root.getRight() == null || sub.getWeigth() != root.getRight().getWeigth() )
					sub.setRight(root.getRight());
				if (node.getWeigth() == this.root.getWeigth())
					this.root = sub;
				return sub;
			}
		}
		else if (root.getWeigth() > node.getWeigth())
		{
			Node sub = delete(root.getLeft(), node);
			if (sub != null) {
				root.setLeft(sub);
				if (sub.getWeigth() == node.getWeigth())
					root.setLeft(null);
			}
			return null;
		}
		else
		{
			Node sub = delete(root.getRight(), node);
			if (sub != null)
			{
				root.setRight(sub);
				if (sub.getWeigth() == node.getWeigth())
					root.setRight(null);
			}
			return null;
		}
	}
	// ���ϵ�����������Ѱ��ֱ������ĳ���ڵ��������Ϊ�գ�������Ҫ������ڵ�ĸ��ڵ����������Ϊ����ڵ���ҽڵ�
	protected Node findMaxLeft(Node right) {
		if (right.getLeft() == null) {
			det = true;
			return right;
		}
		Node one = findMaxLeft(right.getLeft());
		if (det) {
			det = false;
			right.setLeft(one.getRight());
		}
		return one;
	}
	// ���ϵ�����������Ѱ��ֱ������ĳ���ڵ��������Ϊ�գ�������Ҫ������ڵ�ĸ��ڵ����������Ϊ����ڵ����ڵ�
	protected Node findMaxRight(Node left) {
		if (left.getRight() == null) {
			det = true;
			return left;
		}
		Node one = findMaxRight(left.getRight());
		if (det) {
			left.setRight(one.getLeft());
			det = false;
		}
		return one;
	}

	@Override
	public boolean contain(Node node) {
		return find(root, node);
	}
	// �Ƚϴ�С������ȸ��ڵ�󣬾������������ң�����ȸ��ڵ�С��������ڵ����
	private boolean find(Node root, Node node) {
		if (root == null)
			return false;
		if (root.getWeigth() == node.getWeigth())
			return true;
		if (root.getWeigth() > node.getWeigth())
			return find(root.getLeft(), node);
		if (root.getWeigth() < node.getWeigth())
			return find(root.getRight(), node);
		return false;
	}

	public static void main(String[] args) {
		int n = 10, max = 10;
		BinaryTree tree = new BinarySearchTree();
		int[] num = new int[n];
		System.out.println("num");
		for (int i = 0; i < n; ++i) {
			Random random = new Random();
			int weigth = random.nextInt(max);
			num[i] = weigth;
			System.out.print(num[i] + " ");
			tree.insert(new Node(weigth));
		}
		System.out.println("pre order");
		tree.PostOrder();
		for (int i = 0; i < n/2; ++i) {
			Random random = new Random();
			int weigth = random.nextInt(max);
			num[i] = weigth;
			System.out.println(weigth);
			tree.delete(new Node(weigth));
			tree.PostOrder();
		}
		
	}
}
