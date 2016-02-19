package com.chirag.test.codesprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class GreedyFlorist {

	public static class ReverseIntegerComparator implements Comparator<Integer> {
	    @Override
	    public int compare(Integer o1, Integer o2) {
	        return o2.compareTo(o1);
	    }
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		Integer N = Integer.valueOf(tokenizer.nextToken());
		Integer K = Integer.valueOf(tokenizer.nextToken());
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		Integer[] cost = new Integer[N];
		for(Integer i=0; i<N; i++)
			cost[i] = Integer.valueOf(tokenizer.nextToken());
		
		Arrays.sort(cost, new ReverseIntegerComparator());
		Integer curMltplr = 0;
		Integer totalCost = 0;
		
		for(int i=0; i<N; i++)
		{
			if(i>0 && i%K==0) curMltplr++;
			totalCost += cost[i] * (curMltplr+1);
		}
		
		inputReader.close();
		System.out.println(totalCost);
	}

}
