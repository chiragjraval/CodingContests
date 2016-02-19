package com.chirag.test.string;

import java.util.Arrays;

public class StringMaskExample
{
	public static void main(String[] args)
	{
		System.out.println(removeMaskString("Chirag", "hr"));
		System.out.println(removeMaskString("geeksforgeeks", "mask"));
	}
	
	private static String removeMaskString(String ipString, String maskString)
	{
		byte[] ipBytes = ipString.getBytes();
		boolean[] maskChars = new boolean[256];
		byte[] maskBytes = maskString.getBytes();
		
		for(int i=0; i<maskBytes.length; i++)
			maskChars[maskBytes[i]] = true;
		
		int ipIdx, resIdx;
		for(ipIdx=0, resIdx=0; ipIdx<ipBytes.length; ipIdx++)
		{
			if(!maskChars[ipBytes[ipIdx]]) {
				ipBytes[resIdx]= ipBytes[ipIdx];
				resIdx++;
			}
		}
		
		return new String(Arrays.copyOf(ipBytes, resIdx));
	}
}
