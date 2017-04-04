package com.chirag.test.edgeverve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem2 {

	public static enum Direction
	{
		LEFT,
		RIGHT
	}
	
	public static class Cockroach implements Comparable<Cockroach>
	{
		public double pos;
		public Direction dir;
		
		public Cockroach(double pos)
		{
			this.pos = pos;
		}
		
		@Override
		public int compareTo(Cockroach o) {
			return Double.compare(this.pos, o.pos);
		}
		
		@Override
		public String toString()
		{
			return "[" + this.pos + "," + this.dir + "]";
		}
	}
	
	private static double getMinTimeForCollision(ArrayList<Cockroach> cockroaches)
	{
		double minTime = Double.MAX_VALUE;
		
		for(int j=0; j<cockroaches.size()-1; j++)
		{
			Cockroach roach = cockroaches.get(j);
			Cockroach nextRoach = cockroaches.get(j+1);
			
			if(roach.dir.equals(Direction.RIGHT)
					&& nextRoach.dir.equals(Direction.LEFT))
			{
				double collisionTime = (nextRoach.pos-roach.pos)/2;
				minTime = Math.min(minTime, collisionTime);
			}
		}
		
		return minTime;
	}
	
	private static ArrayList<Cockroach> updatePositionAfterTime(ArrayList<Cockroach> cockroaches, double polLength, double time)
	{
		ArrayList<Cockroach> resultList = new ArrayList<Cockroach>(cockroaches.size());
		
		for(int i=0; i<cockroaches.size(); i++)
		{
			Cockroach roach = cockroaches.get(i);
			
			if(roach.dir.equals(Direction.LEFT) && roach.pos-time<1)
				continue;
			else if(roach.dir.equals(Direction.RIGHT) && roach.pos+time>=polLength)
				continue;
			else
			{
				if(roach.dir.equals(Direction.LEFT))
					roach.pos -= time;
				else
					roach.pos += time;
				
				if(i>0) {
					Cockroach prevRoach = cockroaches.get(i-1);
					if(roach.pos==prevRoach.pos)
					{
						prevRoach.dir = Direction.LEFT;
						roach.dir = Direction.RIGHT;
					}
				}
				resultList.add(roach);
			}
		}
		
		return resultList;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		double polLength = Double.valueOf(tokenizer.nextToken());
		int N = Integer.valueOf(tokenizer.nextToken());
		ArrayList<Cockroach> cockroaches = new ArrayList<Cockroach>();
		
		for(int i=0; i<N; i++)
			cockroaches.add(new Cockroach(Double.valueOf(tokenizer.nextToken())));
		
		inputReader.close();
		Collections.sort(cockroaches);
		
		// Logic for calculating minimum time
		double minTime = Double.MIN_VALUE;
		for(int i=0; i<N; i++)
		{
			Cockroach roach = cockroaches.get(i);
			minTime = Math.max(minTime, Math.min(roach.pos, polLength-roach.pos));
		}
		
		// Logic for calculating maximum time
		cockroaches.get(0).dir = Direction.RIGHT;
		cockroaches.get(N-1).dir = Direction.LEFT;
		for(int i=1; i<=N-2; i++)
		{
			if(i%2==1)
				cockroaches.get(i).dir = Direction.LEFT;
			else
				cockroaches.get(i).dir = Direction.RIGHT;
		}
		
		double maxTime = 0;
		while(!cockroaches.isEmpty() && cockroaches.size()!=1)
		{
			double minTimeForCollision = getMinTimeForCollision(cockroaches);
			if(minTimeForCollision==Double.MAX_VALUE) break;
			
			maxTime += minTimeForCollision;
			cockroaches = updatePositionAfterTime(cockroaches, polLength, minTimeForCollision);
		}
		
		if(cockroaches.size()==1)
		{
			Cockroach roach = cockroaches.get(0); 
			if(roach.dir.equals(Direction.LEFT)) maxTime += roach.pos;
			else maxTime += polLength-roach.pos;
		}
		else
		{
			double maxTimeToEnd = Double.MIN_VALUE;
			for (Cockroach roach : cockroaches) {
				if(roach.dir.equals(Direction.LEFT))
					maxTimeToEnd = Math.max(maxTimeToEnd, roach.pos);
				else
					maxTimeToEnd = Math.max(maxTimeToEnd, polLength-roach.pos);
			}
			maxTime += maxTimeToEnd;
		}
		
		NumberFormat nf = new DecimalFormat("##.###");
		System.out.print(nf.format(minTime) + " " + nf.format(maxTime));
	}

}
