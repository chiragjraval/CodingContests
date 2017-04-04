package com.chirag.test.zensar;

import java.util.HashSet;
import java.util.Set;

public class CandidateCode {

	public static void main(String[] args) {
		int result = maxTreeDestroyed(6, 7, 14, "((2,1),(6,6),(4,2),(2,5),(2,6),(2,7),(3,4),(6,1),(6,2),(2,3),(6,3),(6,4),(6,5),(6,7))");
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
	
	static private Integer getMaxLengthPath(Point point, Set<Point> pointGrid){
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
		
	static private Point getThirdPoint(Point p1, Point p2) {
		return new Point(2*p2.x - p1.x, 2*p2.y - p1.y);
	}

	
	public static int maxTreeDestroyed(int input1,int input2,int input3,String input4) {
		int destroyedPlants = input3;
		Set<Point> pointGrid = createPointGrid(destroyedPlants, input4);
		
		int maxResult = 0;
		for (Point point : pointGrid) {
			int result = getMaxLengthPath(point, pointGrid);
			
			if(maxResult < result) {
				maxResult = result;
			}
		}
		
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
