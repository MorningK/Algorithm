package com.sysu.ck.graph;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class MST {
	// 首先，初始化一个节点的集合V，开始时只有一个节点。
	// 从属于V中的所有节点中选出与之有关的最短边并把那个不在V中的节点添加到集合V中。直到V包含所有的节点。
	// 重点在于选中到集合V的最短边，满足以下条件：
	// [这条边有且仅有一个端点在V中]
	// 满足条件1的最短的边便是到集合V的最短边
	public List<Node> Prim(int[][] g, int n) {
		List<Node> path = new LinkedList<>();
		Set<Integer> v = new HashSet<>();
		v.add(0);
		while (v.size() < n) {
			Iterator<Integer> iterator = v.iterator();
			int min = 0;
			Node one = new Node(0, 0);
			while (iterator.hasNext()) {
				int s = iterator.next();
				for (int i = 0; i < n; ++i) {
					if ((min == 0 || min > g[s][i]) && g[s][i] != 0 && !v.contains(i)) {
						min = g[s][i];
						one.setWeigth(min);
						one.setX(s);
						one.setY(i);
					}
				}
			}
			path.add(one);
			v.add(one.getY());
		}
		return path;
	}
	
	// 每次选择最短的边，但需要注意不要构成回路
	public List<Node> Kruskal(int[][] g, int n) {
		List<Node> path = new LinkedList<>();
		Queue<Node> edges = new PriorityQueue<>();
		boolean[] det = new boolean[n];
		for (int i = 0; i < n; ++i) {
			for (int j = i+1; j < n; ++j) {
				if (g[i][j] != 0) {
					Node node = new Node(i, j);
					node.setWeigth(g[i][j]);
					edges.add(node);
				}
			}
		}
		while (!isAllIn(det, n)) {
			Node node = edges.poll();
			int x = node.getX();
			int y = node.getY();
			if (det[x] && det[y])
				continue;
			det[x] = true;
			det[y] = true;
			path.add(node);
		}
		return path;
	}
	// 检查是否全部包含了
	private boolean isAllIn(boolean[] det, int n) {
		for (int i  = 0; i < n; ++i) {
			if (!det[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int max = 3, n = 5;
		int[][] g = new int[n][n];
		System.out.println("graph");
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < max; ++j) {
				Random random = new Random();
				int k = random.nextInt(n);
				while (k == i)
					k = random.nextInt(n);
				g[i][k] = g[k][i] = random.nextInt(10) + 1;
			}
		}
		for (int i = 0; i < n; ++i) {
			for (int j = i+1; j < n; ++j) {
				if (g[i][j] != 0) {
					System.out.println(i + " " + j + " " + g[i][j]);
				}
			}
		}
		MST mst = new MST();
		Date start = new Date();
		List<Node> nodes = mst.Prim(g, n);
		Date end = new Date();
		System.out.println("Prim");
		System.out.println(end.getTime()-start.getTime());
		int sum = 0;
		for (Node node : nodes) {
			sum += node.getWeigth();
			System.out.println(node.getX() + " " + node.getY() + " " + node.getWeigth());
		}
		System.out.println(sum);
		sum = 0;
		start = new Date();
		nodes = mst.Kruskal(g, n);
		end = new Date();
		System.out.println("Kruskal");
		System.out.println(end.getTime()-start.getTime());
		for (Node node : nodes) {
			sum += node.getWeigth();
			System.out.println(node.getX() + " " + node.getY() + " " + node.getWeigth());
		}
		System.out.println(sum);
	}
}
