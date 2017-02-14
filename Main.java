package assignment2;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem1 {
	
	static double height = 2;
	static double width = 3;
	
	static DecimalFormat df = new DecimalFormat("#.##");
	
	public static void main(String[] args) {
		IntStream.range(1, 21).forEach(i -> {
			int N = i * 100;
			double area = width * 2 * height * 2;
			List<Point> inCircle = generatePoints(N, width*2, height*2).parallelStream()
					.filter(p -> isInEllipse(p.x, p.y, width, height))
					.collect(Collectors.toList());
			System.out.print(N);
			double probability = (double) inCircle.size() / (double) N;
			System.out.print("|" + df.format(probability * area));
			double[] ci = confidenceInterval95(probability, N); 
			
			System.out.println("|[" + df.format(ci[0]*area) + ", " + df.format(ci[1]*area) + "]");
		});
	}
	
	public static List<Point> generatePoints(int N, double width, double height) {
		return new Random().doubles(N)
				.mapToObj(d -> new Point(d * width - width/2, new Random().nextDouble() * height - height/2))
				.collect(Collectors.toList());
	}
	
	public static boolean isInEllipse(double x, double y, double w, double h) {
		return Math.pow(x/w, 2) + Math.pow(y/h, 2) <= 1.0;
	}

	public static double[] confidenceInterval95(double p, int n) {
		return new double[] {p - (1.96 * Math.sqrt((p * (1-p))/n)), p + (1.96 * Math.sqrt((p * (1-p))/n))};
	}
	
	public static class Point {
		double x;
		double y;
		
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		void setX(double x) {
			this.x = x;
		}
		
		void setY(double y) {
			this.y = y;
		}
		
		double getX() {
			return x;
		}
		
		double getY() {
			return y;
		}
	}
}
