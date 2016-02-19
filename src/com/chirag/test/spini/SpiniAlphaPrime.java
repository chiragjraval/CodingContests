package com.chirag.test.spini;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class AlphaPrimeDataObj {
	public Integer L,R;
	
	public AlphaPrimeDataObj(Integer L, Integer R)
	{
		this.L = L;
		this.R = R;
	}
}

class AlphaPrimeCountCalculator implements Callable<Integer> {

	private AlphaPrimeDataObj inDataObj;
	private ConcurrentHashMap<Integer, Boolean> primeHashmap;
	private ConcurrentHashMap<Integer, Boolean> alphaPrimeHashmap;
	
	public AlphaPrimeCountCalculator(AlphaPrimeDataObj inDataObj, ConcurrentHashMap<Integer, Boolean> primeHashmap, ConcurrentHashMap<Integer, Boolean> alphaPrimeHashmap) {
		this.inDataObj = inDataObj;
		this.primeHashmap = primeHashmap;
		this.alphaPrimeHashmap = alphaPrimeHashmap;
	}
	
	@Override
	public Integer call() {
		Integer alphaPrimeCount = 0;
		
		for(Integer i=inDataObj.L; i<=inDataObj.R; i++)
			if(isAlphaPrime(i)) alphaPrimeCount++;
		
		return alphaPrimeCount;
	}
	
	private Boolean isAlphaPrime(Integer num) 
	{
		if(this.alphaPrimeHashmap.containsKey(num))
			return true;
		else
		{
			if(isPrime(num))
			{
				this.primeHashmap.put(num, true);
				this.alphaPrimeHashmap.put(num, true);
				return true;
			}
			
			for(Integer i=1; i<num; i=i*10)
			{
				Integer sfx = num%i;
				
				if(this.primeHashmap.containsKey(sfx))
				{
					this.alphaPrimeHashmap.put(num, true);
					return true;
				}
				else if(isPrime(sfx))
				{
					this.primeHashmap.put(sfx, true);
					this.alphaPrimeHashmap.put(num, true);
					return true;
				}
			}
			
			return false;
		}
	}
	
	private Boolean isPrime(Integer n) {
	    //check if n is a multiple of 2
		if (n<2) return false;
		if (n==2) return true;
		if (n%2==0) return false;
	    //if not, then just check the odds
	    for(Integer i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

}


public class SpiniAlphaPrime {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Scanner inScanner = new Scanner(System.in);
		Integer Q = inScanner.nextInt();
		
		ConcurrentHashMap<Integer, Boolean> primeHashmap = new ConcurrentHashMap<Integer, Boolean>();
		ConcurrentHashMap<Integer, Boolean> alphaPrimeHashmap = new ConcurrentHashMap<Integer, Boolean>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ArrayList<Future<Integer>> resultList = new ArrayList<Future<Integer>>();
		
		for(Integer i=0; i<Q; i++)
		{
			Integer L = inScanner.nextInt();
			Integer R = inScanner.nextInt();
			
			AlphaPrimeDataObj inDataObj = new AlphaPrimeDataObj(L, R);
			resultList.add(executorService.submit(new AlphaPrimeCountCalculator(inDataObj, primeHashmap, alphaPrimeHashmap)));
		}
		
		executorService.shutdown();
		
		int resultListSize = resultList.size();
		for(int i=0;i<resultListSize;i++)
			System.out.println(resultList.get(i).get());
		
		inScanner.close();
	}

}
