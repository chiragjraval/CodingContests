package com.chirag.test.edgeverve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem1 {

	public static void main(String[] args) throws IOException
	{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = null;
		
		tokenizer = new StringTokenizer(inputReader.readLine());
		Integer N = Integer.valueOf(tokenizer.nextToken());
		String inString = tokenizer.nextToken();
		char[] inCharArr = inString.toCharArray();
		LinkedList<Character> buffer = new LinkedList<Character>();
		
		inputReader.close();
		
		boolean firstOutput = true;
		for(int i=0; i<inCharArr.length; i++)
		{
			if(inCharArr[i]=='!')
			{
				if(!firstOutput) System.out.print(" ");
				else firstOutput = false;
				
				Iterator<Character> bufferItr = buffer.iterator();
				while(bufferItr.hasNext())
					System.out.print(bufferItr.next());
			}
			else
			{
				if(buffer.contains(inCharArr[i]))
				{
					buffer.remove((Character)inCharArr[i]);
					buffer.add(inCharArr[i]);
				}
				else
				{
					if(buffer.size()==N)
						buffer.removeFirst();
					
					buffer.add(inCharArr[i]);
				}
			}
		}
	}

}
