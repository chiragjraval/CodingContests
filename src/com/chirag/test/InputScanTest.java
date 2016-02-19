package com.chirag.test;

import java.util.Scanner;

public class InputScanTest {

	public static void main(String[] args)
	{
		Scanner inScanner = new Scanner(System.in);
		
		System.out.print("Enter 1st number to add : ");
		int a = inScanner.nextInt();
		
		System.out.print("Enter 2nd number to add : ");
		int b = inScanner.nextInt();
		
		System.out.println("Addition of two numbers is :: " + (a+b));
		
		Boolean ab = inScanner.nextBoolean();
		System.out.println(ab);
				
		inScanner.close();
	}
	
}
