package com.chirag.test.codesprint;

import java.util.Arrays;
import java.util.Scanner;

public class SpaceStationDistance {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] cityWithSS = new int[m];
        
        for (int i=0; i<m; i++)
        	cityWithSS[i] = in.nextInt();
        
        Arrays.sort(cityWithSS);
        
        int maxDist= cityWithSS[0] - 0;
        int tempDistVar;
        for (int i=1; i<m; i++)
        {
        	tempDistVar = (cityWithSS[i] - cityWithSS[i-1])>>1;
        	if(maxDist<tempDistVar) maxDist = tempDistVar;
        }
        tempDistVar = (n-1) - cityWithSS[m-1];
    	if(maxDist<tempDistVar) maxDist = tempDistVar;
        
        System.out.println(maxDist);
        in.close();
    }
}

