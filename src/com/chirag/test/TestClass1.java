package com.chirag.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class TestClass1 {
	public static void main(String args[]) throws Exception {
        /*
         * Read input from stdin and provide input before running
		*/	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
       	int T = Integer.parseInt(line);			// Number of testcases
       	
       	for(int i=0; i<T; i++)		// Iterate over each test case
       	{
       		line = br.readLine();
       		String[] inputParams = line.split(" ");
       		int N = Integer.parseInt(inputParams[0]);		// Total number of Strings
       		int K = Integer.parseInt(inputParams[1]);		// Index of string to be found
       		int M = Integer.parseInt(inputParams[2]);		// characters to consider while sorting
       		
       		HashMap<String, List<String>> bucketMap = new HashMap<String, List<String>>();
       		for (int j=0; j<N; j++) 		// Iterate over each string and store in bucket 
       		{
       			line = br.readLine();
       			String lineKey = line.substring(0, M);
       			
       			if(bucketMap.containsKey(lineKey))
       			{
       				bucketMap.get(lineKey).add(line);
       			}
       			else
       			{
       				List<String> bucketList = new ArrayList<String>();
       				bucketList.add(line);
       				bucketMap.put(lineKey, bucketList);
       			}
			}
       		
       		TreeSet<String> bucketKeySet = new TreeSet<String>();
       		bucketKeySet.addAll(bucketMap.keySet());
       		int skippedStringCount = 0;
       		for (String bucketKey : bucketKeySet)
       		{
       			List<String> bucketList = bucketMap.get(bucketKey);
       			if(skippedStringCount + bucketList.size() >= K)
       			{
       				System.out.println(bucketList.get(K-skippedStringCount-1));
       				break;
       			}
       			else
       			{
       				skippedStringCount += bucketList.size();
       			}
			}
       	}
	}
}
