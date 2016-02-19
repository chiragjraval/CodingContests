package com.chirag.test.betsol;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

class DataObj {
	private Long N,M;
	private String leftShiftStr;
	
	public DataObj(Long N, Long M)
	{
		this.N = N;
		this.M = M;
		this.leftShiftStr = null;
	}
	
	public Long getN() {
		return this.N;
	}
	
	public Long getM() {
		return this.M;
	}
	
	public String getLeftShiftStr(){
		return this.leftShiftStr;
	}
	
	public void setLeftShiftStr(String leftShiftStr){
		this.leftShiftStr = leftShiftStr;
	}
}

class MatteyMultiplier implements Runnable {

	private int threadIndex;
	private int totalThreads;
	private DataObj[] inDataArr;
	private CountDownLatch latch;
	
	public MatteyMultiplier(int threadIndex,int totalThreads, DataObj[] inDataArr, CountDownLatch latch) {
		this.threadIndex = threadIndex;
		this.totalThreads = totalThreads;
		this.inDataArr = inDataArr;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		for(int i=threadIndex; i<inDataArr.length; i+=totalThreads)
		{
			DataObj inDataObj = inDataArr[i];  
			inDataObj.setLeftShiftStr(getLeftShiftStrVal(inDataObj.getN(), inDataObj.getM()));
		}
		latch.countDown();
	}
	
	private static String getLeftShiftStrVal(Long N, Long M)
	{
		// Initialize variable
		ArrayList<Integer> leftShiftVals = new ArrayList<Integer>();
		Character char1 = '1';
		String strM = Long.toBinaryString(M);
		int strMLength = strM.length();
		
		// We need to note index at which 1 is there in M and need to left shift by it
		for(int i=0; i<strMLength; i++)
		{
			if(char1.equals(strM.charAt(i)))
				leftShiftVals.add(strMLength-1-i);
		}
		
		int leftShiftValsSize = leftShiftVals.size();
		StringBuilder leftShiftStrBldr = new StringBuilder();
		for (int j=0; j<leftShiftValsSize; j++) {
			leftShiftStrBldr.append("(").append(N).append("<<").append(leftShiftVals.get(j)).append(")");
			if(j+1<leftShiftValsSize)
				leftShiftStrBldr.append(" + ");
		}
		
		return leftShiftStrBldr.toString();
	}
	
}

public class MatteyMultiplication {

	public static void main(String args[]) throws Exception
	{    
		Scanner inScanner = new Scanner(System.in);
		
		// Read All Input for TestCases
		Integer T = inScanner.nextInt();
		DataObj[] inDataArr = new DataObj[T];
		for(int i=0; i<T; i++)
		{
			Long N = inScanner.nextLong();
			Long M = inScanner.nextLong();
			DataObj inDataObj = new DataObj(N, M);
			inDataArr[i] = inDataObj;
		}
		
		// Start 4 thread and calculate Left Shift Operations 
		final int totalThreads = 4;
		CountDownLatch latch = new CountDownLatch(totalThreads);
		Thread[] mattryMultipliers = new Thread[totalThreads];
		for(int i=0; i<totalThreads; i++)
		{
			mattryMultipliers[i] = new Thread(new MatteyMultiplier(i, totalThreads, inDataArr, latch));
			mattryMultipliers[i].start();
		}
		
		// Wait for worker threads to complete and then print output
		latch.await();
		for(int i=0; i<T; i++)
			System.out.println(inDataArr[i].getLeftShiftStr());
		
		inScanner.close();
    }

}
