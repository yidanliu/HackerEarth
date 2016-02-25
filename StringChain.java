import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringChain {
	private Map<String, List<String>> cache;
	private Set<String> setOfInput;
	
	private List<String> getLongestChain(String s, String[] input) {
		if (cache.containsKey(s)) {
			return cache.get(s);
		}
		List<String> output = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			StringBuffer sb = new StringBuffer(s);
			String delOne = sb.deleteCharAt(i).toString();
			if (setOfInput.contains(delOne)) {
				List<String> chain = getLongestChain(delOne, input);
				if (output.size() < chain.size()) {
					output = chain;
				}
			}
		}
		output.add(0, s);
		cache.put(s, output);
		return output;
	}
	
	public List<String> longestChain(String[] input) {
		List<String> output = new ArrayList<String>();
		cache = new HashMap<String, List<String>>();
		setOfInput = new HashSet<String>();
		for (int i = 0; i < input.length; i++) {
			setOfInput.add(input[i]);
		}
		for (int i = 0; i < input.length; i++) {
			List<String> longest = getLongestChain(input[i], input);
			if (longest.size() > output.size()) {
				output = longest;
			}		
		}
		return output;
	}
}