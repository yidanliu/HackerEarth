import java.util.LinkedList;
import java.util.Queue;

public class zerosConnected {
	public boolean isZerosConnected(int[][] grid) {
		boolean colored = false;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					if (!colored) {
						startColoring(i, j, grid);
						colored = true;
					} else {
						return false;
					}
					
				}
			}
		}
		return true;
	}

	private void startColoring(int x, int y, int[][] grid) {
		int m = grid.length, n = grid[0].length;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(x*n + y);
		grid[x][y] = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int row = cur/n, col = cur%n;
			if (row-1 >= 0 && grid[row-1][col] == 0) {
				queue.offer(cur-n);
				grid[row-1][col] = 1;
			}
			if (row+1 < m && grid[row+1][col] == 0) {
				queue.offer(cur+n);
				grid[row+1][col] = 1;
			}
			if (col-1 >= 0 && grid[row][col-1] == 0) {
				queue.offer(cur-1);
				grid[row][col-1] = 1;
			}
			if (col+1 < n && grid[row][col+1] == 0) {
				queue.offer(cur+1);
				grid[row][col+1] = 1;
			}
		}
	}
}