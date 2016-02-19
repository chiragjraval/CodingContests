package com.chirag.test.string;

public class StringPermutations {

	public static void main(String[] args) {
		printPermutations("Chirag");
	}
	
	private static void swap(char[] str, int idx1, int idx2)
	{
		char temp = str[idx1];
		str[idx1] = str[idx2];
		str[idx2] = temp;
	}
	
	private static void printPermutationsUtil(char[] str, int startIdx, int endIdx)
	{
		if(startIdx==endIdx) {
			System.out.println(new String(str));
		}
		else {
			for(int i=startIdx;i<=endIdx; i++)
			{
				swap(str, startIdx, i);
				printPermutationsUtil(str, startIdx+1, endIdx);
				swap(str, startIdx, i);
			}
		}
	}
	
	private static void printPermutations(String str) {
		if(str!= null && str.length()>0)
			printPermutationsUtil(str.toCharArray(), 0, str.length()-1);
	}
	
}
