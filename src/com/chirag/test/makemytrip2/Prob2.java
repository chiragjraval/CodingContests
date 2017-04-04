package com.chirag.test.makemytrip2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob2
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		int N = Integer.valueOf(tokenizer.nextToken());
		int Q = Integer.valueOf(tokenizer.nextToken());
		
		int[] input = new int[N];
		tokenizer = new StringTokenizer(inputReader.readLine());
		for(int i=0;i<N; i++) input[i] = Integer.valueOf(tokenizer.nextToken());
			
		
		for(int i=0; i<Q; i++)
		{
			tokenizer = new StringTokenizer(inputReader.readLine());
			int opId = Integer.valueOf(tokenizer.nextToken());
			
			if(opId==1)
			{
				int v = Integer.valueOf(tokenizer.nextToken());
				boolean found = false;
				for(int j=0; !found && j<N; j++)
				{
					if(input[j]>=v)
					{
						int idx = j+1;
						System.out.println(idx);
						found = true;
					}
				}
				if(!found) System.out.println(-1);
			}
			else if(opId==0)
			{
				int x = Integer.valueOf(tokenizer.nextToken());
				int y = Integer.valueOf(tokenizer.nextToken());
				if(x<=N) input[x-1] = y;
			}
		}
	}
}
