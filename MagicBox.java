import java.util.HashMap;
import java.util.Map;

public class MagicBox {
	public int numOfWishes(char[][] board) {	// 'P' or 'T'
		int m = board.length, n = board[0].length;
		int maxCount = 0;
		String maxString = "";
		Map<String, Integer> patternCount = new HashMap<String,Integer>();
		for (int i = 0; i < m; i++) {
			String s = "a";
			for (int j = 1; j < n; j++) {
				s += board[i][j] == board[i][0] ? "a" : "b";
			}
			patternCount.put(s, patternCount.containsKey(s) ? patternCount.get(s)+1 : 1);
			if (patternCount.get(s) > maxCount) {
				maxCount = patternCount.get(s);
				maxString = s;
			}
		}
		// count the number os 'a' and 'b' in maxString
//		int numA = 0, numB = 0;
//		for (int i = 0; i < maxString.length(); i++) {
//			if (maxString.charAt(i) == 'a') {
//				numA++;
//			} else {
//				numB++;
//			}
//		}
		return maxCount;
	}
}