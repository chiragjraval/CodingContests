package com.chirag.test.accolite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class PROB1
{
	private static int VAL_RANGE = 0;
	private static int N;
	
	private static int[] getInputArray() throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		N = Integer.valueOf(tokenizer.nextToken());
		int[] inputArr = new int[N];
		
		for(int i=0; i<N; i++)
		{
			tokenizer = new StringTokenizer(inputReader.readLine());
			inputArr[i] = Integer.valueOf(tokenizer.nextToken());
			if(inputArr[i] > VAL_RANGE)
				VAL_RANGE = inputArr[i];
		}
		VAL_RANGE++;
		
		return inputArr;
	}
	
	private static int[] getCountArray(int[] inputArr)
	{
		int[] countArr = new int[VAL_RANGE];
		
		for(int i=0; i<N; i++)
			countArr[inputArr[i]]++;
		
		return countArr;
	}
	
	private static Set<Integer> getPefectSquareHashSet()
	{
		Set<Integer> perfectSquareSet = new HashSet<Integer>(VAL_RANGE);
		
		for(int i=1; i<VAL_RANGE; i++)
			perfectSquareSet.add((i)*(i));
		
		return perfectSquareSet;
	}
	
	private static int nCm(int n, int m)
	{
		Double nCmDouble = 1.0;
		
		for(int j=0; j<m; j++)
		{
			nCmDouble *= (((double)(n-j))/((double)(m-j))); 
		}
		
		return nCmDouble.intValue();
	}
	
	public static void main(String[] args) throws IOException
	{
		int[] inputArr = getInputArray();
		int[] countArr = getCountArray(inputArr);
		Set<Integer> perfectSquareSet = getPefectSquareHashSet();
		
		double probability = 0;
		int totalPerfectSquarePairs = 0, totalPairs = 0;
		
		for(int i=1;i<VAL_RANGE; i++)
		{
			if(countArr[i]>1)
			{
				totalPerfectSquarePairs +=  nCm(countArr[i], 2);
				
			}
			
			for(int j=i+1; j<VAL_RANGE; j++)
			{
				if(countArr[i]>0 && countArr[j]>0)
				{
					int multiplication = i * j; 
					if(perfectSquareSet.contains(multiplication))
					{
						totalPerfectSquarePairs += (countArr[i] * countArr[j]);
					}
				}
			}
		}
		
		totalPairs = nCm(N, 2);
		probability = ((double)totalPerfectSquarePairs)/((double)totalPairs);
		System.out.printf("%.6g", probability);
	}
	
	

}
