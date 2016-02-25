import java.util.Arrays;
import java.util.Comparator;

class StringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		int h1 = Integer.valueOf(o1.substring(0,2));
		int h2 = Integer.valueOf(o2.substring(0,2));
		int min1 = Integer.valueOf(o1.substring(3));
		int min2 = Integer.valueOf(o2.substring(3));
		if (h1 < h2) {
			return -1;
		}
		if (h1 > h2) {
			return 1;
		}
		if (min1 < min2) {
			return -1;
		}
		return min1 > min2 ? 1 : 0;
	}
	
}


public class ClosestTimePoints {
	private int getTimeDiff(String o1, String o2) {		// o2-o1
		int diff = 0;
		int h1 = Integer.valueOf(o1.substring(0,2));
		int h2 = Integer.valueOf(o2.substring(0,2));
		int min1 = Integer.valueOf(o1.substring(3));
		int min2 = Integer.valueOf(o2.substring(3));
		
		int carry = 0;
		if (min2 < min1) {
			min2 += 60;
			carry = -1;
		}
		diff += min2-min1;
		h2 += carry;
		if (h2 < h1) {
			h2 += 24;
		}
		diff += (h2-h1) * 60;
		return diff;
	}
	
	public int smallestInterval(String[] times) {
		int numOfMinutes = 0, len = times.length;
		Arrays.sort(times, new StringComparator());
		for (int i = 0; i < len; i++) {
			int diff = getTimeDiff(times[i], times[(i+1)%len]);
			numOfMinutes = numOfMinutes < diff ? numOfMinutes : diff;
			System.out.println(times[i]+","+diff);

		}
		return numOfMinutes;
	}
	
    private int getEntry(String curTime) {
        int hour = Integer.valueOf(curTime.substring(0,2));
        int min = Integer.valueOf(curTime.substring(3));
        return hour*60+min;
    }
    public int getMinTimeDifference(String[] times) {
        boolean[] timeExist = new boolean[24*60+1];
        for (int i = 0; i < times.length; i++) {
            String curTime = times[i];
            int entry = getEntry(curTime);
            System.out.println(entry);

            timeExist[entry] = true;
        }
        int minDiff = timeExist.length, prevEntry = -1, firstEntry = -1;
        for (int i = 0; i < timeExist.length; i++) {
            if (timeExist[i]) {
                if (prevEntry == -1) {
                    firstEntry = i;
                } else {
                    minDiff = Math.min(i-prevEntry-1, minDiff);
                }
                prevEntry = i;
            }
        }
        minDiff = Math.min(firstEntry+timeExist.length-prevEntry, minDiff);
        System.out.println(minDiff);
        return minDiff;
    }
	
	
}