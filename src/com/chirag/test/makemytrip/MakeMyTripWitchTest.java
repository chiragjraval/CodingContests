package com.chirag.test.makemytrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class MakeMyTripWitchTest {
    
	public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        
        for (int i = 0; i < N; i++) {
            /* Read input and get height of persons */
        	line = br.readLine();
            String[] strValues = line.split(" ");
            long[] values = new long[strValues.length];
            for(int j=0; j<strValues.length; j++) values[j] = Long.parseLong(strValues[j]);
            sortHeights(values);
            
            if(values.length == 3) {
            	if(values[1] - values[0] > values[2] - values[1])
            		System.out.println((values[1]-values[0]-1));
            	else if(values[2] - values[1] != 0)
            		System.out.println((values[2]-values[1]-1));
            	else
            		System.out.println("0");
            }
            else {
            	System.out.println("Error");
            }
            
        }
    }
    
    
    private static void sortHeights(long[] values) {
    	if(values[0] > values[1]) {
    		long temp = values[1];
    		values[1] = values[0];
    		values[0] = temp;
    	}
    	if(values[1] > values[2]) {
    		long temp = values[2];
    		values[2] = values[1];
    		values[1] = temp;
    	}
    	if(values[0] > values[1]) {
    		long temp = values[1];
    		values[1] = values[0];
    		values[0] = temp;
    	}	
    }
}
