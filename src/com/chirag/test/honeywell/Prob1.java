package com.chirag.test.honeywell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Prob1 {

	private static int NUM_THREADS = 4;
	
	static class SwapQuery {
		private int L;
		private int R;
		
		public SwapQuery(int L, int R) {
			this.L = L;
			this.R = R;
		}
	}
	
	static class ProcessSwapQueries implements Callable<boolean[]> {
		private List<SwapQuery> swapQueries;
		private boolean[] swapped;
		
		public ProcessSwapQueries(List<SwapQuery> swapQueries, int N) {
			this.swapQueries = swapQueries;
			swapped = new boolean[N];
		}
		
		@Override
		public boolean[] call() throws Exception {
			for (SwapQuery swapQuery : swapQueries) {
				for(int i=swapQuery.L; i<=swapQuery.R; i++) {
	        		swapped[i-1] = !swapped[i-1];
	        	}
			}
			
			return swapped;
		}
	}
	
	private static boolean[] aggregateResults(List<Future<boolean[]>> results, boolean[] swapped, int N) throws InterruptedException, ExecutionException {
		for(int i=0; i<N; i++) {
			for(int j=0; j<NUM_THREADS; j++) {
				swapped[i] = swapped[i]^results.get(j).get()[i];
			}
		}
		
		return swapped;
	}
			
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
		
        int[] data = new int[N];
        boolean[] swapped = new boolean[N];
        line = br.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line);
        for(int i=0; i<N && tokenizer.hasMoreTokens(); i++) {
        	data[i] = Integer.parseInt(tokenizer.nextToken());
        }
        
        line = br.readLine();
        int Q = Integer.parseInt(line);
        
        NUM_THREADS = Q < 4 ? Q : 4;
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<boolean[]>> results = new ArrayList<Future<boolean[]>>(NUM_THREADS);
        int processSwapQuerySize = Q/NUM_THREADS;
        List<SwapQuery> swapQueries = new ArrayList<>(processSwapQuerySize);
        for(int j=0; j<Q; j++) {
        	line = br.readLine();
            tokenizer = new StringTokenizer(line);
        	int L = Integer.parseInt(tokenizer.nextToken());
        	int R = Integer.parseInt(tokenizer.nextToken());
            swapQueries.add(new SwapQuery(L, R));
            
            if(swapQueries.size()==processSwapQuerySize || j==Q-1) {
            	results.add(executor.submit(new ProcessSwapQueries(swapQueries, N)));
            	swapQueries = new ArrayList<>(processSwapQuerySize);
            }
        }
        
        aggregateResults(results, swapped, N);
        
        for(int i=0; i<N/2; i++) {
        	if(swapped[i]^swapped[N-i-1]) {
        		int temp = data[i];
        		data[i] = data[N-i-1];
        		data[N-i-1] = temp;
        	}
        }
        
        for(int i=0; i<N; i++) {
        	System.out.print(data[i]);
        	if(i <= N-1) {
        		System.out.print(" ");
        	}
        }
        
        executor.shutdown();
        br.close();
	}
}
