import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Skyline {
	private int MaxPoints = 10000;
	
	public List<List<Double>> getSkyline(double[][] points) {
		List<Point> result = new ArrayList<Point>();
		List<Point> input = new ArrayList<Point>();
		// init input
		for (int i = 0; i < points.length; i++) {
			input.add(new Point(points[i][0], points[i][2], true));
			input.add(new Point(points[i][0] + points[i][1], points[i][2],
					false));
		}
		// sort according to x-axis increasingly
		Collections.sort(input, new Comparator<Point>() {

			@Override
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
		// priority queue, highest point on top
		PriorityQueue<Double> queue = new PriorityQueue<Double>(MaxPoints,
				Collections.reverseOrder());

		queue.offer(0.0);
		double prevHeight = 0;
		for (Point p : input) {
			if (p.isLeft) {
				queue.offer(p.y);
			} else {
				queue.remove(p.y);
			}

			double curHeight = queue.peek();
			if (curHeight != prevHeight) {
				// corner points
				result.add(new Point(p.x, curHeight, true));
				prevHeight = curHeight;
			}
		}
		// output
		List<List<Double>> output = new ArrayList<List<Double>>();
		prevHeight = 0;
		for (int i = 0; i < result.size(); i++) {
			Point p = result.get(i);
			if (i < result.size() - 1 && result.get(i + 1).x == p.x
					&& result.get(i + 1).y >= p.y) {
				continue;
			}
			List<Double> l1 = new ArrayList<Double>();
			l1.add(p.x);
			l1.add(prevHeight);
			List<Double> l2 = new ArrayList<Double>();
			l2.add(p.x);
			l2.add(p.y);
			output.add(l1);
			output.add(l2);
			prevHeight = p.y;
		}
		// start point
		if (output.get(0).get(0) != 0) {
			List<Double> list = new ArrayList<Double>();
			list.add(0.0);
			list.add(0.0);
			output.add(0, list);
		}

		return output;
	}
}

class Point {
	double x;
	double y;
	boolean isLeft;

	Point(double xx, double yy, boolean left) {
		x = xx;
		y = yy;
		isLeft = left;
	}
}