package com.sysu.ck.graph;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// 给定起点s，找出s到图中任意一点的最短路径
public class SPA {
	// 每次循环，都确定一条从源s到节点的最短路径
	// 算法的重点：distance[i] = min(distance[i], distance[mid] + g[mid][i])
	// Dijkstra算法不适用于边的权重为负数的情况下
	public List<Node> Dijkstra(int[][] g, int n, int s, int[] distance) {
		List<Node> path = new LinkedList<>();
		boolean[] det = new boolean[n];
		int min = s;
		for (int j = 0; j < n; ++j) {
			final int mid = min;
			for (int i = 0; i < n; ++i) {
				if ((distance[i] == 0 || distance[i] > distance[mid] + g[mid][i]) && 
						g[mid][i] != 0 && i != s) {
					if (distance[i] != 0) {
						for (Node one : path) {
							if (one.getY() == i) {
								one.setX(mid);
								one.setWeigth(g[mid][i]);
							}
						}
					} else {
						Node node = new Node(mid, i);
						node.setWeigth(g[mid][i]);
						path.add(node);
					}
					distance[i] = distance[mid] + g[mid][i];
				}
				if (g[mid][i] > 0 &&  i != s && !det[i] && 
						(min == mid || g[mid][min] > g[mid][i])) {
					min = i;
				}
			}
			det[min] = true;
		}
		return path;
	}
	// 找到从s到e的最短路径
	public List<Node> findPath(List<Node> paths, int s, int e) {
		List<Node> path = new LinkedList<>();
		int mid = e;
		while (true) {
			for (Node node : paths) {
				if (node.getY() == mid) {
					path.add(node);
					mid = node.getX();
				}
			}
			if (mid == s)
				break;
		}
		return path;
	}
	
	public static void main(String[] args) {
		int n = 1000, s = 0;
		int max = n/2;
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
		SPA spa = new SPA();
		{
			Date start = new Date();
			int[] distance = new int[n];
			List<Node> nodes = spa.Dijkstra(g, n, s, distance);
			Date end = new Date();
			System.out.println("Dijkstra");
			System.out.println(end.getTime()-start.getTime());
			System.out.println("distance ");
			for (int i = 0; i < n; ++i) {
				System.out.println(s + " -> " + i);
				System.out.println(distance[i]);
				List<Node> path = spa.findPath(nodes, s, i);
				for (Node node : path) {
					System.out.println(node.getX() + " " + node.getY() + " " + node.getWeigth());
				}
			}
		}
	}
}
