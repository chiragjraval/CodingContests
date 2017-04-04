package com.chirag.test.techgig.patterns;

import java.util.Scanner;

public class PatternProblem3 {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		
		int N = inScanner.nextInt();
		for(int i=1; i<=N; i++) {
			StringBuilder strBuilder = new StringBuilder();
			for(int j=N-i; j>=0; j--) {
				strBuilder.append(i);
				if(j>0) strBuilder.append(" ");
			}
			System.out.println(strBuilder.toString());
		}
		
		inScanner.close();
	}
	
}
