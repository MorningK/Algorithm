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
	// ���ȣ���ʼ��һ���ڵ�ļ���V����ʼʱֻ��һ���ڵ㡣
	// ������V�е����нڵ���ѡ����֮�йص���̱߲����Ǹ�����V�еĽڵ���ӵ�����V�С�ֱ��V�������еĽڵ㡣
	// �ص�����ѡ�е�����V����̱ߣ���������������
	// [���������ҽ���һ���˵���V��]
	// ��������1����̵ı߱��ǵ�����V����̱�
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
	
	// ÿ��ѡ����̵ıߣ�����Ҫע�ⲻҪ���ɻ�·
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
	// ����Ƿ�ȫ��������
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
