package com.chirag.test.practice;

import java.util.Scanner;

public class LargestPermutation {

	public static void main(String[] args)
	{
		Scanner inScanner = new Scanner(System.in);
		
		int N = inScanner.nextInt();
		int K = inScanner.nextInt();
		int data[] = new int[N];
		
		for(int i=0; i<N; i++)
			data[i] = inScanner.nextInt();
		
		inScanner.close();
		
		performSelectionSortKIterations(data, ((K<N) ? K : N) );
		
		for (int i : data) {
			System.out.print(i + " ");
		}
	}
	
	private static void performSelectionSortKIterations(int[] data, int K)
	{
		int keyIndex;
		
		for(int i=0; i<K; i++)
		{
			keyIndex=i;
			int j;
			
			for(j=i+1; j<data.length; j++)
			{
				if(data[keyIndex] < data[j])
					keyIndex = j;
			}
			
			if(i!=keyIndex)
			{
				int temp = data[i];
				data[i] = data[keyIndex];
				data[keyIndex] = temp;
			}
		}
	}
	
}
