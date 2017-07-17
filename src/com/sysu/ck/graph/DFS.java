package com.sysu.ck.graph;

import java.util.Stack;

// 深度优先算法，访问一个节点时，先把这个节点的某个直接相邻的节点全部访问完，再访问其他的相邻节点
// 遇到分叉，先选择一个方向，沿着这个方向把这条路径走完，然后回到分叉点，选择其余的路径
// 使用递归或者栈来实现
public class DFS {
	// 判断某位置上的点是否是可通行的
	private boolean valid(int p) {
		if (p == 0)
			return false;
		return true;
	}
	// 遍历路径上的所有点
	private void visit(int[][] g, int n, int m, int x, int y, boolean[][] det) {
		if (det[x][y])
			return;
		det[x][y] = true;
		// 所有可能的位置方向
		int[][] all = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int i = 0; i < 4; ++i) {
			int nx = x + all[i][0];
			int ny = y + all[i][1];
			if (nx < n && ny < y && !det[nx][ny] && valid(g[nx][ny])) {
				visit(g, n, m, nx, ny, det);
			}
		}
	}
	// 找出经过（sx，sy）点处所有路径
	public void dfs(int[][] g, int n, int m, int sx, int sy, boolean[][] det) {
		if (valid(g[sx][sy])) {
			visit(g, n, m, sx, sy, det);
		}
	}
	// 栈版本
	public void dfs_stack(int[][] g, int n, int m, int sx, int sy, boolean[][] det) {
		Stack<Node> nodes = new Stack<>();
		if (valid(g[sx][sy]) && !det[sx][sy]) {
			nodes.push(new Node(sx, sy));
			while (!nodes.empty()) {
				Node top = nodes.pop();
				int x = top.getX();
				int y = top.getY();
				det[x][y] = true;
				// 所有可能的位置方向
				int[][] all = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
				for (int i = 0; i < 4; ++i) {
					int nx = x + all[i][0];
					int ny = y + all[i][1];
					if (nx < n && ny < y && !det[nx][ny] && valid(g[nx][ny])) {
						nodes.push(new Node(nx, ny));
					}
				}
			}
		}
	}
	// 遍历全图,返回连通区域的个数
	public int dfs(int[][] g, int n, int m) {
		int k = 0;
		boolean[][] det = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (valid(g[i][j]) && !det[i][j]) {
					++k;
					dfs(g, n, m, i, j, det);
				}
			}
		}
		return k;
	}
}
