import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/

class TestClass {
    public static void main(String args[] ) throws Exception {
//    	MagicBox mb = new MagicBox();
//    	char[][] board = {{'T','T','P'},
//    			{'P','T','P'},
//    			{'T','P','P'},
//    			{'P','T','P'},
//    			{'T','P','T'}};
//    	System.out.println(mb.numOfWishes(board));
//    	String[] times = {
//    			"23:59","24:00","00:00"
//    	};
//    	ClosestTimePoints ct = new ClosestTimePoints();
//    	ct.getMinTimeDifference(times);
    	
    	String[] transactions = {
    			"Shilpa|500|California|63",
    			"Tom|25|New York|615",
    			"Krasi|9000|California|1230",
    			"Tom|25|New York|1235",
    			"Tom|25|New York|1238",
    			"Matt|33|Virginia|1239",
    			"Shilpa|50|Michigan|1300",
    			
    			"Matt|90000|Georgia|1305",
    			"Jay|100000|Virginia|1310",
    			"Krasi|49|Florida|1320",
    			"Krasi|83|California|1325",
    			"Shilpa|50|California|1350"
    			
    	};
    	FindFraudTransaction fs = new FindFraudTransaction();
    	fs.getSuspiciousList(transactions);
    	
    	
    	
//    	MaxLenSub ml = new MaxLenSub();
//    	int[] nums = {1,-1,5,-2,3};
//    	int k = 3;
//    	ml.maxSubArrayLen(nums, k);
//    	
//    	UniqueNumber un = new UniqueNumber();
//    	int[] nums = {2,1,3,2,3,4,1,3,1,2};
//    	System.out.println(un.getUniqueNumber(nums));
    	
//    	existBomb bb = new existBomb();
//    	int[] nums = {1,1,2,2,2,4,4,4};
//    	System.out.println(bb.isBombExist(nums));
    	
        /*
         * Read input from stdin and provide input before running
		*/
//    	String path = "/Users/liuyidan/Documents/interview/AZCoding/SkylineTests/input";
//        BufferedReader br = new BufferedReader(new FileReader(path + "007.txt"));
//        String line = br.readLine();
//        String[] split;
//        int m = Integer.valueOf(line);
//        double[][] matrix = new double[m][3];
//        for (int i = 0; i < m; i++) {
//            line = br.readLine();
//            split = line.split(" ");
//            for (int j = 0; j < 3; j++) {
//            	matrix[i][j] = Double.valueOf(split[j]);
//            }
//        }
//////    	PalindromeCount pc = new PalindromeCount();
//////        System.out.println(pc.numOfPalindromes("dskjkd"));
////    	Matrix ma = new Matrix();
////    	System.out.println(ma.numOfPaths(matrix));
//    	
//    	Skyline sl = new Skyline();
//    	// int[][] points = {{5,1,1},{4,3,3},{3,5,5},{2,7,7},{1,9,9}};
//    	sl.getSkyline(matrix);
    	
//    	StringChain sc = new StringChain();
//    	String[] str = {"abcde", "acde", "abde", "abe", "ae", "adb"};
//    	List<String> output = sc.longestChain(str);
//    	for (String s : output) {
//    		System.out.println(s);
//    	}
    	/*
    	public List<Point> getSkyline(int[][] points) {
    		List<Point> output = new ArrayList<Point>();
    		List<Point> input = new ArrayList<Point>();

    		for (int i = 0; i < points.length; i++) {
    			input.add(new Point(points[i][0], points[i][2], true));
    			input.add(new Point(points[i][0] + points[i][1], points[i][2],
    					false));
    		}
    		// sort in increasing order based on x-axis
    		Collections.sort(input, new Comparator<Point>() {
    			public int compare(Point p1, Point p2) {
    				if (p1.x < p2.x) {
    					return -1;
    				} else if (p1.x > p2.x) {
    					return 1;
    				} else {
    					if (p1.y < p2.y) {
    						return -1;
    					} else if (p1.y > p2.y) {
    						return 1;
    					} else {
    						if (p1.isLeft && !p2.isLeft) {
    							return -1;
    						} else {
    							return 1;
    						}
    					}
    				}
    			}
    		});

    		Stack<Point> stack = new Stack<Point>();
    		Map<Integer, Integer> heightFreq = new HashMap<Integer, Integer>();

    		for (Point p : input) {
    			if (stack.isEmpty()) {
    				stack.push(p);
    				heightFreq.put(p.y, 1);
    			}
    			// skip duplicate
    			if (p.x == stack.peek().x && p.y == stack.peek().y
    					&& (p.isLeft || !stack.peek().isLeft)) {
    				continue;
    			}
    			if (stack.peek().isLeft && stack.peek().y > p.y) {
    				continue;
    			}
    			// left point
    			if (p.isLeft) {
    				while (stack.peek().x == p.x && stack.peek().y < p.y) {
    					heightFreq.put(stack.peek().y,
    							heightFreq.get(stack.peek().y) - 1);
    					stack.pop();
    				}
    				if (stack.peek().isLeft) {
    					if (stack.peek().x == p.x && stack.peek().y < p.y
    							&& stack.peek().y > 0) {
    						stack.push(p);
    						heightFreq.put(
    								p.y,
    								heightFreq.containsKey(p.y) ? heightFreq
    										.get(p.y) + 1 : 1);
    					} else if (stack.peek().y < p.y) {
    						// corner
    						int tmpy = stack.peek().y;
    						stack.push(new Point(p.x, tmpy, false));
    						heightFreq.put(
    								tmpy,
    								heightFreq.containsKey(tmpy) ? heightFreq
    										.get(tmpy) + 1 : 1);
    						stack.push(p);
    						heightFreq.put(
    								p.y,
    								heightFreq.containsKey(p.y) ? heightFreq
    										.get(p.y) + 1 : 1);
    					}
    				} else {
    					if (stack.peek().x < p.x) {
    						// separate intervals
    						stack.push(new Point(stack.peek().x, 0, false));
    						stack.push(new Point(p.x, 0, false));
    						stack.push(p);
    						heightFreq
    								.put(0,
    										heightFreq.containsKey(0) ? heightFreq
    												.get(0) + 1 : 2);
    						heightFreq.put(
    								p.y,
    								heightFreq.containsKey(p.y) ? heightFreq
    										.get(p.y) + 1 : 1);
    					} else if (stack.peek().y < p.y) {
    						stack.push(p);
    						heightFreq.put(
    								p.y,
    								heightFreq.containsKey(p.y) ? heightFreq
    										.get(p.y) + 1 : 1);
    					}
    				}

    			} else { // p is right point

    				if (!stack.isEmpty() && stack.peek().y == p.y
    						&& !stack.peek().isLeft) {
    					heightFreq.put(stack.peek().y,
    							heightFreq.get(stack.peek().y) - 1);
    					stack.pop();
    				} else if (!stack.isEmpty() && stack.peek().y > p.y) {
    					// corner
    					stack.push(new Point(stack.peek().x, p.y, p.isLeft));
    					heightFreq
    							.put(p.y,
    									heightFreq.containsKey(p.y) ? heightFreq
    											.get(p.y) + 1 : 1);
    				} else {
    					while (!stack.isEmpty() && stack.peek().y <= p.y) {
    						if (stack.peek().y == 0
    								|| (stack.peek().y == p.y && heightFreq
    										.get(p.y) == 1)) {
    							break;
    						}
    						heightFreq.put(stack.peek().y,
    								heightFreq.get(stack.peek().y) - 1);
    						stack.pop();
    					}
    				}

    				stack.push(p);
    				heightFreq.put(p.y,
    						heightFreq.containsKey(p.y) ? heightFreq.get(p.y) + 1
    								: 1);

    			}
    		}
    		stack.push(new Point(stack.peek().x, 0, false));
    		while (!stack.isEmpty()) {
    			stack.pop();
    		}

    		return output;
    	}
    */
    }
}