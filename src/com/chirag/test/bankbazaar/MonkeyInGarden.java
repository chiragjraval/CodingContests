package com.chirag.test.bankbazaar;

public class MonkeyInGarden {

	public static void main(String[] args)
	{
		System.out.println(traveltime(new int[]{1,2,3,4,5,6,5,4,3,2,8}, 11));
	}

	
	public static int traveltime(int[] input1,int input2)
    {
		int noOfTrees = input1.length;
		int maxDist = 0;		// Assume initial maxDist to be 0
		
		// Calculate max distance between each tree 
		for(int i=0; i<noOfTrees; i++)
		{
			// This loop will ignore trees for which distance was already considered in previous run
			for(int j=i+1; j<noOfTrees; j++)
			{
				int distBetweenTree = Math.min((j-i), ((noOfTrees+i)-j));
				int totalDistBtwnTree = input1[i] + input1[j] + distBetweenTree;
				if(maxDist<totalDistBtwnTree)
					maxDist = totalDistBtwnTree;
			}
		}
		
        return maxDist;
    }
}
