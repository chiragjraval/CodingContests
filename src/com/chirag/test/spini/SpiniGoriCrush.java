package com.chirag.test.spini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class DataObj {
	public Integer a,b,c,d,k;
	public Integer[] fortuneNoArr;
	
	public DataObj(Integer a, Integer b, Integer c, Integer d, Integer[] fortuneNoArr, Integer k)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.k = k;
		this.fortuneNoArr = Arrays.copyOf(fortuneNoArr, fortuneNoArr.length);
		
		/*this.fortuneNoArr = new Integer[fortuneNoArr.length];
		for(int i=0; i<fortuneNoArr.length; i++)
			this.fortuneNoArr[i] = fortuneNoArr[i];*/
	}
}

class GoriCrushCalculator implements Callable<String> {

	private DataObj inDataObj;
	
	public GoriCrushCalculator(DataObj inDataObj) {
		this.inDataObj = inDataObj;
	}
	
	@Override
	public String call() {
		ArrayList<Integer> goriList = getNoInRangeFromArr(inDataObj.fortuneNoArr, inDataObj.a, inDataObj.b);
		ArrayList<Integer> goriCrushList = getNoInRangeFromArr(inDataObj.fortuneNoArr, inDataObj.c, inDataObj.d);
		
		goriList.retainAll(goriCrushList);
		
		Integer m = goriList.size();
		
		if(m>=inDataObj.k)
			return "Propose";
		else
			return "Do not propose";
	}
	
	private ArrayList<Integer> getNoInRangeFromArr(Integer[] arr, Integer min, Integer max)
	{
		ArrayList<Integer> noList = new ArrayList<Integer>();
		
		for(int i=0;i<arr.length; i++)
		{
			if(min<=arr[i] && arr[i]<=max)
				noList.add(arr[i]);
		}
		
		return noList;
	}
}


public class SpiniGoriCrush {

	
	
	public static void main(String args[]) throws Exception {
    	
		Scanner inScanner = new Scanner(System.in);
		Integer N = inScanner.nextInt();
		Integer Q = inScanner.nextInt();
		Integer k = inScanner.nextInt();
		
		Integer[] fortuneNoArr = new Integer[N];
		for(int i=0; i<N; i++) fortuneNoArr[i] = inScanner.nextInt();
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
		
		for(int i=0; i<Q; i++)
		{
			Integer queryType = inScanner.nextInt();
			
			if(queryType==0)
			{
				Integer x = inScanner.nextInt();
				Integer y = inScanner.nextInt();
				fortuneNoArr[x-1] = y;
			}
			else if(queryType==1)
			{
				Integer a = inScanner.nextInt();
				Integer b = inScanner.nextInt();
				Integer c = inScanner.nextInt();
				Integer d = inScanner.nextInt();
				
				DataObj inDataObj = new DataObj(a, b, c, d, fortuneNoArr, k);
				resultList.add(executorService.submit(new GoriCrushCalculator(inDataObj)));
			}
			else
			{
				// Do nothing
			}
				
		}
		
		executorService.shutdown();
		
		int resultListSize = resultList.size();
		for(int i=0;i<resultListSize;i++)
			System.out.println(resultList.get(i).get());
		
		inScanner.close();
	}
	
	
	/*public static void main(String args[]) throws Exception {
    	
		Scanner inScanner = new Scanner(System.in);
		Integer N = inScanner.nextInt();
		Integer Q = inScanner.nextInt();
		Integer k = inScanner.nextInt();
		
		Integer[] fortuneNoArr = new Integer[N];
		for(int i=0; i<N; i++) fortuneNoArr[i] = inScanner.nextInt();
		
		for(int i=0; i<Q; i++)
		{
			Integer queryType = inScanner.nextInt();
			
			if(queryType==0)
			{
				Integer x = inScanner.nextInt();
				Integer y = inScanner.nextInt();
				fortuneNoArr[x-1] = y;
			}
			else if(queryType==1)
			{
				Integer a = inScanner.nextInt();
				Integer b = inScanner.nextInt();
				Integer c = inScanner.nextInt();
				Integer d = inScanner.nextInt();
				
				ArrayList<Integer> goriList = getNoInRangeFromArr(fortuneNoArr, a, b);
				ArrayList<Integer> goriCrushList = getNoInRangeFromArr(fortuneNoArr, c, d);
				
				goriList.retainAll(goriCrushList);
				
				Integer m = goriList.size();
				
				if(m>=k)
					System.out.println("Propose");
				else
					System.out.println("Do not propose");
			}
			else
			{
				// Do nothing
			}
				
		}
		
		inScanner.close();
	}*/
	
	
	
}
