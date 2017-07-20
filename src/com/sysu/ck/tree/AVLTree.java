package com.sysu.ck.tree;

import java.util.Random;

public class AVLTree extends BinarySearchTree {
	
	private int bf, lbf, rbf;
	
	public AVLTree() {
		super();
		bf = 0;
		lbf = 0;
		rbf = 0;
	}
	
	// 平衡因子，左子树的深度减去右子树的深度
	private int BF(Node root) {
		if (root == null)
			return 0;
		return height(root.getLeft()) - height(root.getRight());
	}
	
	@Override
	public void insert(Node node) {
		Node one = insert(root, node);
		if (one != null)
		{
			root = one;
		}
	}
	
	private Node insert(Node root, Node node) {
		if (root == null) {
			return node;
		} else {
			if (root.getWeigth() > node.getWeigth()) {
				Node one = insert(root.getLeft(), node);
				if (one != null)
					root.setLeft(one);
			} else if (root.getWeigth() < node.getWeigth()) {
				Node one = insert(root.getRight(), node);
				if (one != null)
					root.setRight(one);
			} else {
				System.out.println("this node is already in the tree.");
			}
			return keepBalance(root);
		}
	}
	// 检查整棵子树的平衡性
	private Node treeBalance(Node root) {
		if (root == null)
			return null;
		Node left = keepBalance(root.getLeft());
		if (left != null)
			root.setLeft(left);
		Node right = keepBalance(root.getRight());
		if (right != null)
			root.setRight(right);
		return keepBalance(root);
	}
	// 必要时旋转以保持树的平衡，如果需要旋转，返回新的根节点，不需要则返回null
	private Node keepBalance(Node root) {
		if (root == null)
			return null;
		bf = BF(root);
		lbf = BF(root.getLeft());
		rbf = BF(root.getRight());
		if (bf > 1 && lbf > 0) {
			return RotateLL(root);
		}
		if (bf > 1 && lbf < 0) {
			return RotateLR(root);
		}
		if (bf < -1 && rbf < 0) {
			return RotateRR(root);
		}
		if (bf < -1 && rbf > 0) {
			return RotateRL(root);
		}
		return null;
	}
	
	// 根节点左子树比右子树深2层，并且左子树的左子树深于右子树
	private Node RotateLL(Node root) {
		Node left = root.getLeft();
		root.setLeft(left.getRight());
		left.setRight(root);
		return left;
	}
	// 根节点右子树比左子树深2层，并且右子树的右子树深于左子树
	private Node RotateRR(Node root) {
		Node right = root.getRight();
		root.setRight(right.getLeft());
		right.setLeft(root);
		return right;
	}
	// 根节点左子树比右子树深2层，并且左子树的右子树深于左子树
	private Node RotateLR(Node root) {
		Node left = root.getLeft();
		left = RotateRR(left);
		root.setLeft(left);
		return RotateLL(root);
	}
	// 根节点右子树比左子树深2层，并且右子树的左子树深于右子树
	private Node RotateRL(Node root) {
		Node right = root.getRight();
		right = RotateLL(right);
		root.setRight(right);
		return RotateRR(root);
	}

	@Override
	public void delete(Node node) {
		Node one = delete(root, node);
		if (one != null) 
		{
			root = one;
		}
	}

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
				if (height(root.getLeft()) > height(root.getRight()))
				{
					sub = findMaxRight(root.getLeft());
					Node left = treeBalance(root.getLeft());
					if (left != null)
						root.setLeft(left);
				}
				else
				{
					sub = findMaxLeft(root.getRight());
					Node right = treeBalance(root.getRight());
					if (right != null)
						root.setRight(right);
				}
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
		}
		return keepBalance(root);
	}

	
	public static void main(String[] args) {
		int n = 20, max = 50;
		BinaryTree tree = new AVLTree();
		int[] num = new int[n];
		System.out.println("num");
		for (int i = 0; i < n; ++i) {
			Random random = new Random();
			int weigth = random.nextInt(max);
			num[i] = weigth;
			System.out.print(num[i] + " ");
			tree.insert(new Node(weigth));
		}
		System.out.println("\npost order");
		tree.PostOrder();
		System.out.println("delete");
		for (int i = 0; i < n/2; ++i) {
			System.out.println(num[i]);
			tree.delete(new Node(num[i]));
			tree.PostOrder();
		}
		
	}
}
