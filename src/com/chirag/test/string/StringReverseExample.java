package com.chirag.test.string;

public class StringReverseExample {

	public static void main(String... args)
	{
		String origStr = "chirag";
		
		System.out.println(reverseRecursive(origStr));
		System.out.println(reverseIterative(origStr));
	}
	
	
	private static String reverseRecursive(String orig)
	{
		if(orig == null)
			return null;
		
		if(orig.isEmpty())
			return orig;
		
		if(orig.length()==1)
			return orig;
		else
			return reverseRecursive(orig.substring(1)) + orig.charAt(0);
	}
	
	private static String reverseIterative(String org)
	{
		if(org == null)
			return null;
		
		if(org.isEmpty())
			return org;
		
		char[] orgCharArr = org.toCharArray();
		int length = orgCharArr.length;
		
		for(int i=0; i<length/2; i++)
		{
			char temp = orgCharArr[i];
			orgCharArr[i] = orgCharArr[length-1-i];
			orgCharArr[length-1-i] = temp; 
		}
		
		return new String(orgCharArr);
	}
	
}
