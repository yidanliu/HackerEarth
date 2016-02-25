import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindFraudTransaction {
	// only store the latest transaction time
	// delete the entry if already put in output
	private Map<String, String> mapNameTime;
	public List<String> fraudTransactions (String[] trans) {
		mapNameTime = new HashMap<String, String>(); 
		List<String> output = new ArrayList<String>();
		Set<String> added = new HashSet<String>();

		for (int i = trans.length-1; i >= 0; i--) {
			String cur = trans[i];
			String[] split = cur.split("|");
			String name = split[0];
			if (added.contains(name)) {
				continue;
			}
			int amount = Integer.valueOf(split[1]);
			if (amount > 3000) {
				added.add(name);
				output.add(0,name);
				continue;
			}
			if (!mapNameTime.containsKey(name)) {
				mapNameTime.put(name, split[2]+"|"+split[3]);
			} else {
				String curCity = split[2], curTime = split[3];
				String[] later = mapNameTime.get(name).split("|");
				String laterCity = later[0], laterTime = later[1];
				boolean fraud = isFraud(curCity, curTime, laterCity, laterTime);
				if (fraud) {
					added.add(name);
					output.add(0,name);
				} else {
					mapNameTime.put(name, split[2] + "|" + split[3]);
				}
			}
			
		}
		
		return output;
	}
	
//	private boolean isFraud(String curCity, String curTime, String laterCity, String laterTime) {
//		if (curCity.equals(laterCity) || getTimeDiff(laterTime, curTime) >= 60) {
//			return false;
//		}
//		return true;
//	}
	
	private int getTimeDiff(String o1, String o2) {		// o2-o1
		int diff = 0;
		int h1 = Integer.valueOf(o1.substring(0,2));
		int h2 = Integer.valueOf(o2.substring(0,2));
		int min1 = Integer.valueOf(o1.substring(2));
		int min2 = Integer.valueOf(o2.substring(2));
		
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
	
    private boolean isFraud(String curCity, String curTime, String laterCity, String laterTime) {
    	System.out.println(laterCity + "===" + curCity);
        if (curCity.equals(laterCity) || (Integer.valueOf(laterTime)-Integer.valueOf(curTime)>=60)) {
            return false;
        }
        return true;
    }
    public String[] getSuspiciousList(String[] transactions) {
        Map<String, String> mapNameTime = new HashMap<String,String>();
        List<String> output = new ArrayList<String>();
        Set<String> found = new HashSet<String>();
        
        for (int i = transactions.length-1; i >= 0; i--) {
            String[] split = transactions[i].split("\\|");
            String name = split[0];
           
            int amount = Integer.valueOf(split[1]);
            if (amount > 3000 ){
                found.add(name);
                output.add(name);
            }
            if (found.contains(name)) {
                continue;
            }
            if (mapNameTime.containsKey(name)){
                String curCity = split[2], curTime = split[3];
                String[] later = mapNameTime.get(name).split("\t");
                String laterCity = later[0], laterTime = later[1];
                boolean fraud = isFraud(curCity, curTime, laterCity, laterTime);
                if (fraud) {
                    found.add(name);
                    output.add(name);
                }                 
            }
            mapNameTime.put(name, split[2]+"\t"+split[3]);

        }
        int size = found.size();
        String[] res = new String[size];
        int index = 0;
        for (int i = output.size()-1; i >= 0; i--) {
            if (found.contains(output.get(i))) {
                res[index++] = output.get(i);
                System.out.println(output.get(i));
                found.remove(output.get(i));
            } 
        }
        return res;
    }
}