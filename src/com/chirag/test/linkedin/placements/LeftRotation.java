package com.chirag.test.linkedin.placements;

import java.util.Scanner;

public class LeftRotation {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);

		int N = inScanner.nextInt();
		int D = inScanner.nextInt();
		int[] inArray = new int[N];
		int[] outArray = new int[N];
		for(int i=0; i<N; i++) {
			inArray[i] = inScanner.nextInt();
		}
		
		for(int i=0; i<N; i++) {
			outArray[getTargetIdx(i, D, N)] = inArray[i];
		}
		
		for(int i=0; i<N; i++) {
			System.out.print(outArray[i] + " ");
		}
		
		inScanner.close();
	}

	private static int getTargetIdx(int origIdx, int D, int N) {
		return Math.floorMod((origIdx-D), N);
	}
	
}
