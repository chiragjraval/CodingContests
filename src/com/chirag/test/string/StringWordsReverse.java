package com.chirag.test.string;

public class StringWordsReverse {

	public static void main(String[] args) {
		printReverseWords("Testing String words and lots of other things add");
	}
	
	private static void swap(String[] arr, int idx1, int idx2)
	{
		String temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	private static void reverseArray(String[] strArr)
	{
		for(int i=0; i<(strArr.length>>1); i++)
		{
			swap(strArr, i, strArr.length-i-1);
		}
	}
	
	private static void printReverseWords(String ipStr)
	{
		if(ipStr == null)
			return;
		
		String[] strArr = ipStr.split(" ");
		
		if(strArr.length > 0)
			reverseArray(strArr);
		
		for (String str : strArr) {
			System.out.print(str + " ");
		}
	}
}
