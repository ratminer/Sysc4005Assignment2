package assignment2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	
	static double height = 2;
	static double width = 3;
	
	public static void main(String[] args) {
		Random r = new Random();
		
		IntStream.range(1, 21).forEach(i -> {
			
			int N = i * 100;
			List<Point> points = generatePoints(N, width*2, height*2);
			
			List<Point> inCircle = generatePoints(N, width*2, height*2).parallelStream()
					.filter(p -> isInEllipse(p.x, p.y, width, height))
					.collect(Collectors.toList());
			System.out.println(N + " " + inCircle.size());
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
