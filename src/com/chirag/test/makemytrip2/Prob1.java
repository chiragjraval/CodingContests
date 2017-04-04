package com.chirag.test.makemytrip2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Prob1
{	
	private static class LongRef
	{
		long value;
		public LongRef(long value) {this.value=value;}
		
		@Override
		public String toString() {return String.valueOf(this.value);}
	}
	
	private static HashMap<Character, LongRef> populateCharCountMap(String input)
	{
		HashMap<Character, LongRef> charCountMap = new HashMap<Character, LongRef>(input.length());
		
		for (Character strChar : input.toCharArray())
		{
			if(charCountMap.containsKey(strChar))
				charCountMap.get(strChar).value++;
			else
				charCountMap.put(strChar, new LongRef(1));
		}
		
		return charCountMap;
	}
	
	private static long countSubstingWithEndings(String input, char c1, char c2, HashMap<Character, LongRef> charCountMap)
	{
		if(c1==c2)
		{
			LongRef charCount = charCountMap.get(c1);
			
			if(charCount==null)
				return 0;
			else 
				return ((charCount.value)*(charCount.value-1))>>1;
		}
		else if(c1!=c2)
		{
			LongRef charCount1 = charCountMap.get(c1);
			LongRef charCount2 = charCountMap.get(c2);
			
			if(charCount1==null || charCount2==null)
				return 0;
			else
				return charCount1.value*charCount2.value;
		}
		else
		{
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		String input = tokenizer.nextToken();
		HashMap<Character, LongRef> charCountMap = populateCharCountMap(input);
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		int Q = Integer.valueOf(tokenizer.nextToken());
		
		for(int i=0; i<Q; i++)
		{
			tokenizer = new StringTokenizer(inputReader.readLine());
			char c1 = tokenizer.nextToken().charAt(0);
			char c2 = tokenizer.nextToken().charAt(0);
			System.out.println(countSubstingWithEndings(input, c1, c2, charCountMap));
		}
	}
	
	public static int getCountofChar(String input, char c)
	{
		int count = 0;
		
		for (char strChar : input.toCharArray())
			if(strChar==c) count++;
		
		return count;
	}
	
	public static void getCountofChar(String input, char c1, char c2, LongRef v1, LongRef v2)
	{
		v1.value=0;
		v2.value=0;
		
		for (char strChar : input.toCharArray())
		{
			if(strChar==c1) v1.value++;
			if(strChar==c2) v2.value++;
		}
	}
}
