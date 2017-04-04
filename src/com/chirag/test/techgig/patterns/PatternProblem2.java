package com.chirag.test.techgig.patterns;

import java.util.Scanner;

public class PatternProblem2 {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		
		int N = inScanner.nextInt();
		for(int i=0; i<N; i++) {
			System.out.println("E D C B A");
		}
		
		inScanner.close();
	}
	
}
