package com.sysu.ck.graph;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

// 宽度优先算法，访问每个节点时，先访问完直接与这个节点相连的所有节点，然后再访问更深一层的节点
// 使用队列解决
public class BFS {
	// 返回最短的路径
	public List<Node> bfs(int[][] g, int n, int m, Node s, Node e) {
		int sx = s.getX();
		int sy = s.getY();
		int ex = e.getX();
		int ey = e.getY();
		boolean[][] det = new boolean[n][m];
		// 每个节点的父节点至多存在一个，可以用来保存路径
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
				// 所有可能的位置方向
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
	// 判断某位置上的点是否是可通行的
	private boolean valid(int p) {
		if (p == 0)
			return false;
		return true;
	}
}
