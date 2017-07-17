package com.sysu.ck.graph;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

// ��������㷨������ÿ���ڵ�ʱ���ȷ�����ֱ��������ڵ����������нڵ㣬Ȼ���ٷ��ʸ���һ��Ľڵ�
// ʹ�ö��н��
public class BFS {
	// ������̵�·��
	public List<Node> bfs(int[][] g, int n, int m, Node s, Node e) {
		int sx = s.getX();
		int sy = s.getY();
		int ex = e.getX();
		int ey = e.getY();
		boolean[][] det = new boolean[n][m];
		// ÿ���ڵ�ĸ��ڵ��������һ����������������·��
		List<Node> path = new LinkedList<Node>();
		if (!det[sx][sy] && valid(g[sx][sy]) && 
			!det[ex][ey] && valid(g[ex][ey])) {
			Queue<Node> nodes = new LinkedList<>();
			Node first = new Node(sx, sy);
			first.setParent(null);
			nodes.add(first);
			while (!nodes.isEmpty()) {
				Node top = nodes.poll();
				int x = top.getX();
				int y = top.getY();
				det[x][y] = true;
				// ���п��ܵ�λ�÷���
				int[][] all = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
				for (int i = 0; i < 4; ++i) {
					int nx = x + all[i][0];
					int ny = y + all[i][1];
					if (nx == ex && ny == ey) {
						path.add(0, new Node(ex, ey));
						while (top != null) {
							path.add(0, top);
							top = top.getParent();
						}
						return path;
					}
					if (nx < n && ny < y && !det[nx][ny] && valid(g[nx][ny])) {
						Node one = new Node(nx, ny);
						one.setParent(top);
						nodes.add(one);
					}
				}
			}
		}
		return null;
	}
	// �ж�ĳλ���ϵĵ��Ƿ��ǿ�ͨ�е�
	private boolean valid(int p) {
		if (p == 0)
			return false;
		return true;
	}
}
