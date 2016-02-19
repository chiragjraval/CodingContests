/* IMPORTANT: Multiple classes and nested static classes are supported */
package com.chirag.test;


import java.util.HashMap;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class TestClass {
    public static void main(String args[]) throws Exception {
        /*
         * Read input from stdin and provide input before running
		*/	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
       	
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>(); 
       	
       	for(int i=0; i<line.length(); i++)
       	{
       		Character c = line.charAt(i); 
       		
       		if(charCountMap.containsKey(c))
       			charCountMap.put(c, charCountMap.get(c)+1);
       		else
       			charCountMap.put(c, 1);
       	}
       	//charCountMap.remove(' ');
       	
       	Set<Character> uniqueCharSet = charCountMap.keySet();
       	int maxCount = 0;
       	Character maxCountChar = null;
       	for (Character charKey : uniqueCharSet)
       	{
			if(maxCountChar == null || maxCount < charCountMap.get(charKey) ||
					(maxCount == charCountMap.get(charKey) && charKey < maxCountChar)) 
			{
				maxCount = charCountMap.get(charKey);
				maxCountChar = charKey;
			}
		}
       	
       	System.out.println(maxCountChar.toString() + " " + maxCount);
    }
}
