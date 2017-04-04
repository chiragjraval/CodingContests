package com.chirag.test.linkedin.placements;

import java.util.Scanner;

public class DivisiblePairsSum
{
	public static void main(String[] args)
	{
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        in.close();
        
        int result = 0;
        for(int a_i=0; a_i < n; a_i++){
            for(int a_j=a_i+1; a_j < n; a_j++){
                if((a[a_i]+a[a_j])%k==0)
                    result++;
            }    
        }
        
        System.out.println(result);
    }
}
