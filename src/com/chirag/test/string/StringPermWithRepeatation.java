package com.chirag.test.string;

import java.util.Arrays;

public class StringPermWithRepeatation {

	public static void main(String[] args) {
		printPermWithRepeatation("ABC");
	}
	
	private static void printPermWithRepeatation(String str)
	{
		char[] strData = str.toCharArray();
		Arrays.sort(strData);
		char[] permData = new char[str.length()];
		printPermWithRepeatation(strData, permData, 0);
	}
	
	private static void printPermWithRepeatation(char[] strData, char[] permData, int permIdx)
	{
		for(int i=0; i<strData.length; i++)
		{
			permData[permIdx] = strData[i];
			
			if(permIdx==strData.length-1)
				System.out.println(new String(permData));
			else
				printPermWithRepeatation(strData, permData, permIdx+1);
		}
	}
}
