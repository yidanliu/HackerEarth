import java.math.BigInteger;
import java.util.Random;

public class Matrix {
	public String numOfPaths(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return "0";
		}
		int m = matrix.length, n = matrix[0].length;
		BigInteger[] first = new BigInteger[n]; // first column
		BigInteger[] second = new BigInteger[n]; // second column
		for (int i = 0; i < m; i++) {
			first[i] = new BigInteger(1024, new Random());
			second[i] = new BigInteger(1024, new Random());
			if (i == 0) {
				first[0] = BigInteger.valueOf(matrix[0][0]);
			} else {
				first[i] = matrix[i][0] == 0 ? BigInteger.ZERO : first[i - 1];
			}
		}
		for (int j = 1; j < n; j++) { // j-th column
			for (int i = 0; i < m; i++) { // i-th row
				if (i == 0) {
					second[0] = matrix[0][j] == 0 ? BigInteger.ZERO : first[0];
				} else {
					second[i] = matrix[i][j] == 0 ? BigInteger.ZERO : second[i-1].add(first[i]);
				}
			}
			for (int i = 0; i < m; i++) {
				first[i] = second[i];
			}
		}
		return first[m - 1].toString();
	}
}