package com.chirag.test.linkedin.placements;

import java.util.Scanner;

public class Consecutive1Bits
{
	public static void main(String[] args)
	{
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        
        int maxResult = 0;
        int	result = 0;
        while(n!=0)
        {
        	if(n%2==1)
        	{
        		result++;
        		maxResult = Math.max(maxResult, result);
        	}
        	else
        	{
        		result=0;
        	}
        	n = n>>1;
        }
        
        System.out.println(maxResult);
    }
}
