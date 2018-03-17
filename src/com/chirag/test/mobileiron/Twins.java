package com.chirag.test.mobileiron;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Twins {

	private static final String YES = "Yes";
	private static final String NO = "No";
	
	enum CompareType { EVEN, ODD }
	
	public static void main(String[] args) throws IOException {
		String[] a = {"abbdd"};
		String[] b = {"ddbba"};
		
		String[] result = twins(a, b);
		
		for (String i : result) {
			System.out.println(i);
		}
	}

	private static char[] getSortedAlternativeCharsFromString(String s, int startIdx) {
		int N = s.length();
		char[] result = new char[(N>>1)+1]; 
		
		for(int i=startIdx; i<N; i+=2) {
			result[i>>1] = s.charAt(i);
		}
		
		Arrays.sort(result);
		return result;
	}
	
	private static boolean compareChars(String a, String b, CompareType compareType) {
		int startIdx = CompareType.EVEN.equals(compareType) ? 0 : 1;
		char[] charsA = getSortedAlternativeCharsFromString(a, startIdx);
		char[] charsB = getSortedAlternativeCharsFromString(b, startIdx);
		return Arrays.equals(charsA, charsB);
	}
	
	private static String twins(String a, String b) {
		if(Objects.isNull(a) && Objects.isNull(b)) {
			return YES;
		}
		else if(Objects.isNull(a) || Objects.isNull(b)) {
			return NO;
		}
		else if(a.length() != b.length()) {
			return NO;
		}
		else if(compareChars(a, b, CompareType.EVEN) && compareChars(a, b, CompareType.ODD)) {
			return YES;
		}
		else {
			return NO;
		}
	}
	
	static String[] twins(String[] a, String[] b) {
		int maxN = Math.max(a.length, b.length);
		int minN = Math.min(a.length, b.length);
		
		String[] result = new String[maxN];
		
		for(int i=0; i<minN; i++) {
			result[i] = twins(a[i], b[i]);
		}
		
		for(int i=minN; i<maxN; i++) {
			result[i] = NO;
		}
		
		return result;
    }
}
