package com.chirag.test.edgeverve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Problem3 {

	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		int N = Integer.valueOf(tokenizer.nextToken());
		int[] inputIds = new int[N];
		
		for(int i=0; i<N; i++)
			inputIds[i] = Integer.valueOf(tokenizer.nextToken());
		
		inputReader.close();
		
		for(int i=N; i<=Long.MAX_VALUE; i++)
		{
			HashSet<Integer> uniqueIds = new HashSet<Integer>(N);
			boolean allUnique = true;
			
			for(int j=0; j<N; j++)
			{
				Integer mod = inputIds[j]%i;
				if(uniqueIds.contains(mod))
				{
					allUnique = false;
					break;
				}
				else
					uniqueIds.add(mod);
			}
			
			if(allUnique)
			{
				System.out.print(i);
				break;
			}
		}
	}

}
