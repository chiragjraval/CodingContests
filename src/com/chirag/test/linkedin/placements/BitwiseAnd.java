package com.chirag.test.linkedin.placements;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitwiseAnd {

	private static final Logger LOGGER = Logger.getLogger(BitwiseAnd.class.getName());
	
	public static void main(String[] args) {
		Scanner inScanner = new Scanner(System.in);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		int T = inScanner.nextInt();
		List<Future<Integer>> resultFutures = new ArrayList<>(T);  
		for(int i=0; i<T; i++) {
			int N = inScanner.nextInt();
			int K = inScanner.nextInt();
			resultFutures.add(executor.submit(new BitwiseAndTask(N, K)));
		}
		
		try {
			for (Future<Integer> result : resultFutures) {
				System.out.println(result.get());
			}
		}
		catch (ExecutionException ex) {
			LOGGER.log(Level.SEVERE, "ExecutionException encountered while getting results", ex);
		}
		catch (InterruptedException ex) {
			LOGGER.log(Level.SEVERE, "InterruptedException encountered while getting results", ex);
		}
		
		executor.shutdown();
		inScanner.close();
	}
	
	static class BitwiseAndTask implements Callable<Integer> {

		private int n;
		private int k;
		
		public BitwiseAndTask(int n, int k) {
			this.n = n;
			this.k = k;
		}
		
		@Override
		public Integer call() throws Exception {
			int result = 0;
			
			for(int i=1; i<n; i++) {
				for(int j=i+1; j<=n; j++) {
					int curResult = i & j;
					if(curResult<k && curResult>result) {
						result = curResult;
					}
				}
			}
			
			return result;
		}
		
	}
}
