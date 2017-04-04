package com.chirag.test.techgig.patterns;

import java.util.Scanner;

public class PatternProblem10 {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		
		int N = inScanner.nextInt();
		for(int i=1; i<=N; i++) {
			int noOfSpaces = N - i;
			int noOfStars = (2*i) - 1;
			StringBuilder strBuilder = new StringBuilder();
			
			for(int j=0; j<noOfSpaces; j++){
				strBuilder.append("  ");
			}
			for(int j=1; j<=noOfStars; j++){
				strBuilder.append("*");
				if(j<noOfStars) strBuilder.append(" ");
			}
			
			System.out.println(strBuilder.toString());
		}
		
		inScanner.close();
	}
	
}
