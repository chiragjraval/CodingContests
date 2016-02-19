package com.chirag.test.codesprint;

import java.util.Scanner;

public class MarsSignal {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        char[] sChars = S.toCharArray();
        int charsChanged = 0;
        
        for (int i=0; i<sChars.length; i=i+3)
        {
			if('S'!=sChars[i]) charsChanged++;
			if('O'!=sChars[i+1]) charsChanged++;
			if('S'!=sChars[i+2]) charsChanged++;
		}
        
        System.out.println(charsChanged);
        in.close();
    }
}

