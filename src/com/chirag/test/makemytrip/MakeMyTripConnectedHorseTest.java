package com.chirag.test.makemytrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class MakeMyTripConnectedHorseTest {
    
	public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
        
        for (int i = 0; i < T; i++) {
            /* Read input and get N, M, Q */
        	line = br.readLine();
            String[] strValues = line.split(" ");
            //long N = Long.parseLong(strValues[0]);
            //long M = Long.parseLong(strValues[0]);
            long Q = Long.parseLong(strValues[0]);
            
            
            
            for (int j = 0; j < Q; j++) {
            	
            }
            
        }
    }
    
    
}
