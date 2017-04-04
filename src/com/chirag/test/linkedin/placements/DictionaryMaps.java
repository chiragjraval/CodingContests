package com.chirag.test.linkedin.placements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DictionaryMaps {

	public static final int THREADPOOL_SIZE = 10;
	public static final int TASK_SIZE = 20;
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		Scanner scanner = new Scanner(System.in);
		Integer N = scanner.nextInt();
		
		Map<String, Long> phoneBook = new HashMap<>(N);
		for(int i=0; i<N; i++) {
			String name = scanner.next();
			Long phoneNum = scanner.nextLong();
			phoneBook.put(name, phoneNum);
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(THREADPOOL_SIZE);
		List<Future<String>> taskResults = new ArrayList<>();
		
		TaskContext taskContext = null;
		do {
			taskContext = getNextTaskContext(scanner);
			taskResults.add(executor.submit(new Task(taskContext, phoneBook)));
		}
		while (!taskContext.isLastTask());
		
		for (Future<String> future : taskResults) {
			System.out.print(future.get());
		}
		
		executor.shutdown();
		scanner.close();
	}
	
	private static TaskContext getNextTaskContext(Scanner scanner) {
		TaskContext taskContext = new TaskContext();
		String line = null;
		
		while(scanner.hasNext() && !taskContext.isTaskFull()) {
			line = scanner.next();
			taskContext.addQueryName(line);
		}
		
		if(!taskContext.isTaskFull()) {
			taskContext.makeLastTask(true);
		}
		
		return taskContext;
	}
	
	static class TaskContext {
		private boolean lastTask = false;
		private boolean taskFull = false;
		private List<String> queryNames = new ArrayList<>(TASK_SIZE);
		
		public boolean isLastTask() {
			return this.lastTask;
		}
		
		public void makeLastTask(boolean lastTask) {
			this.lastTask = lastTask;
		}
		
		public boolean isTaskFull() {
			return this.taskFull;
		}
		
		public void addQueryName(String queryName) {
			this.queryNames.add(queryName);
			if(this.queryNames.size()==TASK_SIZE) {
				this.taskFull = true;
			}
		}
	}
	
	static class Task implements Callable<String> {

		private final String NOT_FOUND = "Not found";
		private TaskContext taskContext;
		private Map<String, Long> phoneBook;
		
		public Task(TaskContext taskContext, Map<String, Long> phoneBook) {
			this.taskContext = taskContext;
			this.phoneBook = phoneBook;
		}
		
		@Override
		public String call() throws Exception {
			StringBuilder builder = new StringBuilder();
			for (String queryName : taskContext.queryNames) {
				if(phoneBook.containsKey(queryName)) {
					builder.append(queryName).append("=").append(phoneBook.get(queryName)).append(System.lineSeparator());
				}
				else {
					builder.append(NOT_FOUND).append(System.lineSeparator());
				}
			}
			return builder.toString();
		}
		
	}
	
}
