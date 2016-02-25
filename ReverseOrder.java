public class ReverseOrder {
	public String reverseOrder (String s) {
		StringBuffer sb = new StringBuffer(s);
		int left = 0, right = s.length()-1;
		while (left < right) {
			if (sb.charAt(left) == 'x' && sb.charAt(right) == 'y') {
				sb.setCharAt(left, 'y');
				sb.setCharAt(right, 'x');
				left++;
				right--;
			} else if (sb.charAt(left) == 'x') {
				right--;
			} else if (sb.charAt(right) == 'y') {
				left++;
			} else {
				left++;
				right--;
			}
		}
		return sb.toString();
	}
}