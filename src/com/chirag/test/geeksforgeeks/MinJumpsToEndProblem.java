package com.chirag.test.geeksforgeeks;

public class MinJumpsToEndProblem {

	private static int getMinJumpsToEndWithDP(int[] arr)
	{
		assert(arr!= null && arr.length>0);
		
		int[] jumps = new int[arr.length];
		
		jumps[0] = 0;
		for(int i=1; i<arr.length; i++)
			jumps[i] = -1;
		
		
		for(int i=0; i<arr.length; i++)
		{
			if(arr[i]==-1)
				break;
			
			int startPoint = i + 1;
			int endPoint = i + arr[i];
			
			for(int j=startPoint; j<arr.length && j<=endPoint; j++)
			{
				if(jumps[j]==-1 || jumps[j] > jumps[i]+1)
					jumps[j] = jumps[i]+1;
			}
			
			if(jumps[arr.length-1]>-1)
				break;
		}
		
		return jumps[arr.length-1];
	}
	
	private static int getMinJumpsToEndWithoutDP(int[] arr)
	{
		assert(arr!= null && arr.length>0);
		
		int curIdx = 0;
		int totalJumps = 0;
		
		while(curIdx<arr.length-1)
		{
			if(arr[curIdx] == 0) return -1;
			
			int startPoint = curIdx + 1;
			int endPoint = curIdx + arr[curIdx];
			int nextPointIdx = curIdx, nextPointMaxReach = curIdx;
			for(int i=startPoint; i<arr.length && i<=endPoint; i++)
			{
				int curMaxReach = i + arr[i]; 
				if(curMaxReach > nextPointMaxReach)
				{
					nextPointIdx = i;
				}
			}
			
			curIdx = nextPointIdx;
			totalJumps++;
		}
		
		return totalJumps;
	}
	
	public static void main(String[] args)
	{
		int[] arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		System.out.println(getMinJumpsToEndWithDP(arr));
		System.out.println(getMinJumpsToEndWithoutDP(arr));
		
		arr = new int[]{0};
		System.out.println(getMinJumpsToEndWithDP(arr));
		System.out.println(getMinJumpsToEndWithoutDP(arr));
	}

}
