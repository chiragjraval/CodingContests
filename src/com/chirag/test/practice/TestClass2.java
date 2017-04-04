package com.chirag.test.practice;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestClass2
{
	public static class Parent
	{
		protected Number method() throws FileNotFoundException{return new Integer(0);}
	}
	
	public static class Child extends Parent
	{
		@Override
		public Integer method() throws FileNotFoundException{return 0;}
	}
	
	public static void main(String[] args)
	{
		Parent c = new Child();
		Child c1 = new Child();
		try {
			c.method();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			c1.method();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
