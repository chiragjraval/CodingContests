package com.chirag.test.zensar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CandidateCode2 {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		int row = inScanner.nextInt();
		int column = inScanner.nextInt();
		int destroyedPlants = inScanner.nextInt();
		String plantString = inScanner.next();
		inScanner.close();
		
		int result = maxTreeDestroyed(row, column, destroyedPlants, plantString);
		System.out.println(result);
	}
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Point))
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
	
	static class GetMaxLengthPathForPoint implements Callable<Integer> {
		private Point point;
		private Set<Point> pointGrid;
		
		public GetMaxLengthPathForPoint(Point point, Set<Point> pointGrid) {
			this.point = point;
			this.pointGrid = pointGrid;
		}
		
		@Override
		public Integer call() throws Exception {
			int maxLengthPath = 0;
			
			for (Point gridPoint : pointGrid) {
				if(!gridPoint.equals(point)) {
					int curLengthPath = 2;
					Point p1 = point;
					Point p2 = gridPoint;
					Point p3 = getThirdPoint(p1, p2);
					
					while(pointGrid.contains(p3)) {
						curLengthPath++;
						p1 = p2;
						p2 = p3;
						p3 = getThirdPoint(p1, p2);
					}
					
					if(maxLengthPath < curLengthPath) {
						maxLengthPath = curLengthPath;
					}
				}
			}
			
			return maxLengthPath;
		}
		
		private Point getThirdPoint(Point p1, Point p2) {
			return new Point(2*p2.x - p1.x, 2*p2.y - p1.y);
		}
	}
	
	public static int maxTreeDestroyed(int input1,int input2,int input3,String input4) {
		int destroyedPlants = input3;
		Set<Point> pointGrid = createPointGrid(destroyedPlants, input4);
		
		ExecutorService executor = Executors.newFixedThreadPool(destroyedPlants<10 ? destroyedPlants : 10);
		List<Future<Integer>> getMaxLengthPathForPointResults = new ArrayList<>(destroyedPlants);
		
		for (Point point : pointGrid) {
			getMaxLengthPathForPointResults.add(executor.submit(new GetMaxLengthPathForPoint(point, pointGrid)));
		}
		
		int maxResult = 0;
		for (Future<Integer> futureResult : getMaxLengthPathForPointResults) {
			int result = 0;
			try {
				result = futureResult.get();
			} catch (InterruptedException | ExecutionException e) {
				result = 0;
			}
			
			if(maxResult < result) {
				maxResult = result;
			}
		}
		
		executor.shutdown();
		return maxResult;
    }
	
	private static Set<Point> createPointGrid(int destroyedPlants, String gridInput) {
		Set<Point> pointGrid = new HashSet<>();
		
		if(gridInput!=null && !gridInput.isEmpty()) {
			String[] coordinateStrings = gridInput.split(",");
			if(coordinateStrings.length%2 != 0) {
				return pointGrid;
			}
			else {
				for(int i=0; i<coordinateStrings.length; i=i+2) {
					String xStr = coordinateStrings[i].replace("(", "");
					xStr = xStr.replace(")", "");
					String yStr = coordinateStrings[i+1].replace("(", "");
					yStr = yStr.replace(")", "");
					
					pointGrid.add(new Point(Integer.parseInt(xStr), Integer.parseInt(yStr)));
				}
			}
		}
		
		return pointGrid;
	}
}
