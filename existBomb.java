public class existBomb {
	public boolean isBombExist(int[] nums) {		
		int pair = 0, count = 1;
		int prev = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == prev) {
				count++;			
			} else {
				if (count >= 2 && nums[i]-prev == 1) {
					pair++;
				} else {
					pair = 0;
				}
				count = 1;
				prev = nums[i];
			}
			if (pair == 3) {
				return true;
			}
		}		
		return false;
	}
}