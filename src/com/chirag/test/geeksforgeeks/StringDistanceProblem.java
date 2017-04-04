package com.chirag.test.geeksforgeeks;

public class StringDistanceProblem
{
	private static int min(int a, int b, int c)
	{
		return Math.min(a, Math.min(b, c));
	}
	
	private static int getStringDistance(String str1, String str2)
	{
		assert(str1!=null && !str1.isEmpty());
		assert(str2!=null && !str2.isEmpty());
		
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		
		int[][] minDist = new int[charArr1.length+1][charArr2.length+1];
		
		for(int i=0; i<charArr1.length+1; i++)
			minDist[i][0] = i;
		
		for(int i=0; i<charArr2.length+1; i++)
			minDist[0][i] = i;
		
		for(int i=0; i<charArr1.length; i++)
		{
			for(int j=0; j<charArr2.length; j++)
			{
				if(charArr1[i]==charArr2[j])
					minDist[i+1][j+1] = minDist[i][j];
				else
					minDist[i+1][j+1] = 1 + min(minDist[i][j], minDist[i+1][j], minDist[i][j+1]); 
			}
		}
		
		return minDist[charArr1.length][charArr2.length];
	}
	
	public static void main(String[] args)
	{
		System.out.println(getStringDistance("geek", "gesek"));
		System.out.println(getStringDistance("cat", "cut"));
		System.out.println(getStringDistance("sunday", "saturday"));
	}
}
