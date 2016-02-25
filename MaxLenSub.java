import java.util.HashMap;
import java.util.Map;

public class MaxLenSub {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, maxLen = 0;
        if (len == 0) {
            return 0;
        }
        int[] sum = new int[len+1];
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        sum[0] = 0;
        map.put(0,-1);
        for (int i = 0; i < nums.length; i++) {
            sum[i+1] = sum[i] + nums[i];
            if (map.containsKey(sum[i+1]-k)) {
                maxLen = (i - map.get(sum[i+1]-k)) > maxLen ? i - map.get(sum[i+1]-k) : maxLen;
            } 
                if (!map.containsKey(sum[i+1]))
                    map.put(sum[i+1], i);
            System.out.println(sum[i+1]);
        }
        return maxLen;
    }
}