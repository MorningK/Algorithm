package com.sysu.ck.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MST {
	
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
				System.out.println(i + " " + k + " " + g[i][k]);
			}
		}
		MST mst = new MST();
		List<Node> nodes = mst.Prim(g, n);
		System.out.println("path");
		for (Node node : nodes) {
			System.out.println(node.getX() + " " + node.getY() + " " + node.getWeigth());
		}
	}
}
