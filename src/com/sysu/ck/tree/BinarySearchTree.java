package com.sysu.ck.tree;

import java.util.Random;

public class BinarySearchTree extends BinaryTree {
	// 用于查找到最左（右）节点时对其父节点的处理
	private boolean det;
	
	public BinarySearchTree() {
		super();
		det = false;
	}
	// 注意根节点的特殊处理
	@Override
	public void insert(Node node) {
		Node one = insert(root, node);
		if (root == null)
			root = one;
	}
	// 不断的比较大小，直到找到一个合适的插入位置
	// 注意java的语法
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
	// 找到节点所在位置后，将其置换为左子树的最右节点或者右子树的最左节点
	// 注意根节点的特殊情况
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
	// 不断的往左子树搜寻，直到遇到某个节点的左子树为空，并且需要把这个节点的父节点的左子树置为这个节点的右节点
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
	// 不断的往右子树搜寻，直到遇到某个节点的右子树为空，并且需要把这个节点的父节点的右子树置为这个节点的左节点
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
	// 比较大小，如果比根节点大，就往右子树查找，如果比根节点小，就往左节点查找
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
