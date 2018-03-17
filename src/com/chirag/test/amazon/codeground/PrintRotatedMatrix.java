package com.chirag.test.amazon.codeground;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrintRotatedMatrix {

	static enum Direction {
		UPWARD,
		DOWNWARD
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line);
        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        
        int[][] matrix = new int[R][C];
        for(int i=0; i<R; i++) {
        	line = br.readLine();
        	tokenizer = new StringTokenizer(line);
        	for(int j=0; j<C; j++) {
        		matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
        	}
        }
        
        int rIdx = 0, cIdx = 0;
        Direction curDirction = Direction.UPWARD;
        
        while(rIdx<R && cIdx<C) {
        	System.out.print(matrix[rIdx][cIdx] + " ");
        	
        	if(Direction.UPWARD.equals(curDirction)) {
        		if(cIdx==C-1) {
        			rIdx++;
        			curDirction = Direction.DOWNWARD;
        		}
        		else if(rIdx==0) {
        			cIdx++;
        			curDirction = Direction.DOWNWARD;
        		}
        		else {
        			rIdx--;
        			cIdx++;
        		}
        	}
        	else if(Direction.DOWNWARD.equals(curDirction)) {
        		if(rIdx==R-1) {
        			cIdx++;
        			curDirction = Direction.UPWARD;
        		}
        		else if(cIdx==0) {
        			rIdx++;
        			curDirction = Direction.UPWARD;
        		}
        		else {
        			rIdx++;
        			cIdx--;
        		}
        	}
        }
        
        br.close();
	}
}
