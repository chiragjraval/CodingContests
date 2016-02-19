package com.chirag.test.practice;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;


class Producer implements Runnable {

	private Vector<Integer> dataQueue;
	private final int SIZE;
	private CountDownLatch latch;
	
	public Producer(Vector<Integer> dataQueue, int size, CountDownLatch latch) {
		this.dataQueue = dataQueue;
		this.SIZE = size;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		
		for(int i=1; i<=10 ; i++)
		{
			try
			{
				while(dataQueue.size()>=SIZE)
				{
					synchronized (dataQueue) {
						System.out.println("Producer " + Thread.currentThread().getName() + " waiting. Queue is full.");
						dataQueue.wait();
					}
				}
				
				synchronized (dataQueue) {
					dataQueue.add(i);
					System.out.println(Thread.currentThread().getName() + " Produced : " + i);
					System.out.println(dataQueue);
					dataQueue.notifyAll();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		latch.countDown();
	}
	
}



class Consumer implements Runnable {

	private Vector<Integer> dataQueue;
	private CountDownLatch latch;
	
	public Consumer(Vector<Integer> dataQueue, CountDownLatch latch) {
		this.dataQueue = dataQueue;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		
		while(latch.getCount()>0)
		{
			try
			{
				while(dataQueue.isEmpty())
				{
					synchronized (dataQueue) {
						System.out.println("Consumer " + Thread.currentThread().getName() + " waiting. Queue is empty.");
						dataQueue.wait(1000);
					}
					
					if(latch.getCount()==0 && dataQueue.isEmpty())
						return;
				}
				
				synchronized (dataQueue) {
					System.out.println(Thread.currentThread().getName() + " Consumed : " + dataQueue.remove(0));
					System.out.println(dataQueue);
					dataQueue.notifyAll();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}


public class ProdConsProblemWaitNotify {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int size = 3;
		Vector<Integer> dataQueue = new Vector<Integer>(size);
		CountDownLatch latch = new CountDownLatch(2);
		
		Thread producer1 = new Thread(new Producer(dataQueue, size, latch), "Producer-1");
		Thread producer2 = new Thread(new Producer(dataQueue, size, latch), "Producer-2");
		
		Thread consumer1 = new Thread(new Consumer(dataQueue, latch), "Consumer-1");
		Thread consumer2 = new Thread(new Consumer(dataQueue, latch), "Consumer-2");
		Thread consumer3 = new Thread(new Consumer(dataQueue, latch), "Consumer-3");
		
		consumer1.start();
		consumer2.start();
		consumer3.start();
		producer1.start();
		producer2.start();
	}

}
