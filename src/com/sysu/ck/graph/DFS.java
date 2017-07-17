package com.sysu.ck.graph;

import java.util.Stack;

// ��������㷨������һ���ڵ�ʱ���Ȱ�����ڵ��ĳ��ֱ�����ڵĽڵ�ȫ�������꣬�ٷ������������ڽڵ�
// �����ֲ棬��ѡ��һ����������������������·�����꣬Ȼ��ص��ֲ�㣬ѡ�������·��
// ʹ�õݹ����ջ��ʵ��
public class DFS {
	// �ж�ĳλ���ϵĵ��Ƿ��ǿ�ͨ�е�
	private boolean valid(int p) {
		if (p == 0)
			return false;
		return true;
	}
	// ����·���ϵ����е�
	private void visit(int[][] g, int n, int m, int x, int y, boolean[][] det) {
		if (det[x][y])
			return;
		det[x][y] = true;
		// ���п��ܵ�λ�÷���
		int[][] all = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int i = 0; i < 4; ++i) {
			int nx = x + all[i][0];
			int ny = y + all[i][1];
			if (nx < n && ny < y && !det[nx][ny] && valid(g[nx][ny])) {
				visit(g, n, m, nx, ny, det);
			}
		}
	}
	// �ҳ�������sx��sy���㴦����·��
	public void dfs(int[][] g, int n, int m, int sx, int sy, boolean[][] det) {
		if (valid(g[sx][sy])) {
			visit(g, n, m, sx, sy, det);
		}
	}
	// ջ�汾
	public void dfs_stack(int[][] g, int n, int m, int sx, int sy, boolean[][] det) {
		Stack<Node> nodes = new Stack<>();
		if (valid(g[sx][sy]) && !det[sx][sy]) {
			nodes.push(new Node(sx, sy));
			while (!nodes.empty()) {
				Node top = nodes.pop();
				int x = top.getX();
				int y = top.getY();
				det[x][y] = true;
				// ���п��ܵ�λ�÷���
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
	// ����ȫͼ,������ͨ����ĸ���
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
