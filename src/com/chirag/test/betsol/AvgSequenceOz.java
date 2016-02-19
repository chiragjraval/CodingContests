package com.chirag.test.betsol;

import java.util.Scanner;

public class AvgSequenceOz {

	public static void main(String args[]) throws Exception
	{    
		Scanner inScanner = new Scanner(System.in);
		
		// Read All Input
		Integer N = inScanner.nextInt();
		Double[] arrBVals = new Double[N];
		for(int i=0; i<N; i++)
			arrBVals[i] = inScanner.nextDouble();
		
		// Get values for List A
		Integer[] arrAVals = getOrigElemList(N, arrBVals);
		
		// Print Output
		for(int i=0; i<N; i++)
			System.out.print(arrAVals[i] + " ");
		
		inScanner.close();
    }
	
	private static Integer[] getOrigElemList(Integer N, Double[] arrBVals)
	{
		Integer[] arrAVals = new Integer[N];
		
		// Initial Data Setup
		arrAVals[0] = arrBVals[0].intValue();
		Double sumOfPreceedingElems = arrBVals[0];
		
		/*
		 * Iterate over entire List B and calculate values of List A
		 * Use formula : A[i] = (B[i]*(i+1)) - SUM(A0,Ai-1)
		 */
		for(int i=1; i<N; i++)
		{
			arrAVals[i] = new Double(arrBVals[i]*(i+1) - sumOfPreceedingElems).intValue();
			sumOfPreceedingElems += arrAVals[i];
		}
		
		return arrAVals;
	}
	
}
