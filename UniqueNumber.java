import java.util.HashMap;
import java.util.Map;

public class UniqueNumber {
	public int uniqueNumber (int[] array) {
		Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			freqMap.put(array[i], freqMap.containsKey(array[i])?freqMap.get(array[i])+1:1);
		}
		for (int key : freqMap.keySet()) {
			if (freqMap.get(key) == 1) {
				return key;
			}
		}
		return -1;
	}
	
	// second method : add bitwise and mod 3
	public int getUniqueNumber(int[] nums) {
		int unique = 0;
		for (int i = 0; i < 32; i++) {
			int sum = 0;
			boolean allZeros = true;
			for (int j = 0; j < nums.length; j++) {
				if (nums[j] == 0) 
					continue;
				allZeros = false;
				sum += nums[j] % 2;
				nums[j] /= 2;
			}
			if (allZeros == true) {
				break;
			}
			System.out.println(unique + "," + sum);
			unique += (int)Math.pow(2, i) * (sum%3);
		}
		return unique;
	}
}