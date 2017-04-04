package com.chirag.test.linkedin.placements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		
		int N = inScanner.nextInt();
		int M = inScanner.nextInt();
		
		int[] in1 = new int[N];
		int[] in2 = new int[M];
		
		for(int i=0; i<N; i++) {
			in1[i] = inScanner.nextInt();
		}
		
		for(int j=0; j<M; j++) {
			in2[j] = inScanner.nextInt();
		}
		
		Integer[] lcsArr = getLongestCommonSubsequence(in1, in2);
		
		for (Integer lcsElem : lcsArr) {
			System.out.print(lcsElem + " ");
		}
		
		inScanner.close();
	}
	
	private static Integer[] getLongestCommonSubsequence(int[] in1, int[] in2) {
		int N = in1.length;
		int M = in2.length;
		int[][] lcsDp = new int[N+1][M+1];
		
		for(int i=0; i<=N; i++) {
			lcsDp[i][0] = 0;
		}
		
		for(int j=0; j<=M; j++) {
			lcsDp[0][j] = 0;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(in1[i]==in2[j]) {
					lcsDp[i+1][j+1] = lcsDp[i][j] + 1;
				}
				else {
					lcsDp[i+1][j+1] = Math.max(lcsDp[i+1][j], lcsDp[i][j+1]);
				}
			}
		}
		
		List<Integer> lcsArr = new ArrayList<>(lcsDp[N][M]);
		int i=N, j=M;
		while(i>0 && j>0) {
			if(lcsDp[i][j]==lcsDp[i][j-1]) {
				j--;
			}
			else if(lcsDp[i][j]==lcsDp[i-1][j]) {
				i--;
			}
			else if(lcsDp[i][j]==lcsDp[i-1][j-1]+1) {
				lcsArr.add(in1[i-1]);
				i--; j--;
			}
		}
		
		Collections.reverse(lcsArr);
		return (Integer[]) lcsArr.toArray(new Integer[lcsArr.size()]);
	}
}
