package com.chirag.test.mobileiron;

import java.io.IOException;
import java.util.Objects;

public class MaxElementIndices {

	public static void main(String[] args) throws IOException {
		int[] a = {1, 2, 4, 3};
		int[] rotate = {0, 4};
		
		int[] result = getMaxElementIndices(a, rotate);
		
		for (int i : result) {
			System.out.println(i);
		}
	}

	private static int getMaxElementIndex(int a[]) {
		int maxElementIdx = -1;
		int maxElement = Integer.MIN_VALUE;
		
		if(Objects.nonNull(a) && a.length>0) {
			for (int i=0; i<a.length; i++) {
				if(a[i]>maxElement) {
					maxElement= a[i];
					maxElementIdx = i;
				}
			}
		}
		
		return maxElementIdx;
	}
	
	static int[] getMaxElementIndices(int[] a, int[] rotate) {
		int[] result = new int[rotate.length];
		int maxElementIdx = getMaxElementIndex(a);
		
		for(int i=0; i<rotate.length; i++) {
			result[i] = Math.floorMod(maxElementIdx - rotate[i], a.length);
		}
		
		return result;
    }
}
