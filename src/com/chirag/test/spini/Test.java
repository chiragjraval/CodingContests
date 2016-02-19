package com.chirag.test.spini;

class Test 
{
	 public static void main(String args[]) {
	        String s1 = "Hello";
	        StringBuffer sb = new StringBuffer(s1);
	        sb.reverse();
	        s1.concat(sb.toString());
	        System.out.println(s1 + sb + s1.length() + sb.length());
	    }

    void start() 
    {
        String s1 = "slip";
        String s2 = fix(s1);
        System.out.println(s1 + " " + s2);
    }

    String fix(String s1) 
    {
        s1 = s1 + "stream";
        System.out.print(s1 + " ");
        return "stream";
    }
}