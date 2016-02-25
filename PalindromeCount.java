public class PalindromeCount {
	public int numOfPalindromes(String s) {
		int output = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			// i as center
			for (int j = 0; i-j >= 0 && i+j < len; j++) {
				if (s.charAt(i-j) == s.charAt(i+j)) {
					output++;
				} else {
					// this is important
					break;
				}
			}
			// i and i+1 as center
			for (int j = 0; i-j >= 0 && i+1+j < len; j++) {
				if (s.charAt(i-j) == s.charAt(i+1+j)) {
					output++;
				} else {
					break;
				}
			}
		}
		return output;
	}
}