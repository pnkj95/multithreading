package com.multi;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	public static void main(String[] args) {
		
		//initially threads will be 20 and new ones will be only added when the blocking queue size is exhausted
		ThreadPoolExecutor executor = new ThreadPoolExecutor(20, // core thread pool size
				100, // maximum thread pool size
				1, // time to wait before resizing pool
				TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5, true), new ThreadPoolExecutor.CallerRunsPolicy());

		executor.prestartAllCoreThreads();
		
		//Stats before tasks execution
	      System.out.println("Largest executions: "
	         + executor.getLargestPoolSize());
	      System.out.println("Maximum allowed threads: "
	         + executor.getMaximumPoolSize());
	      System.out.println("Current threads in pool(Pool Size): "
	         + executor.getPoolSize());
	      System.out.println("Currently executing threads: "
	         + executor.getActiveCount());
	      System.out.println("Total number of threads(ever scheduled): "
	         + executor.getTaskCount());
	      
	      System.out.println("-----------------------------------------------------------");
	      for (int i = 0; i < 100; i++) {
	    	  executor.submit(new Worker());
		}
	      
	      System.out.println("-----------------------------------------------------------");
	      //Stats after tasks execution
	      System.out.println("Core threads: " + executor.getCorePoolSize());
	      System.out.println("Largest executions: "
	         + executor.getLargestPoolSize());
	      System.out.println("Maximum allowed threads: "
	         + executor.getMaximumPoolSize());
	      System.out.println("Current threads in pool: "
	         + executor.getPoolSize());
	      System.out.println("Currently executing threads: "
	         + executor.getActiveCount());
	      System.out.println("Total number of threads(ever scheduled): "
	         + executor.getTaskCount());
	      
	      executor.shutdown();
	}
}

class Worker implements Runnable{

	@Override
	public void run() {
		 try {
			 Date date = new Date();
			 System.out.println(date.getTime());
	            Long duration = (long) (Math.random() * 5);
	            System.out.println("Running Task! Thread Name: " +
	               Thread.currentThread().getName());
	            System.out.println("######################+" + duration);
	            TimeUnit.SECONDS.sleep(duration);
	            System.out.println("Task Completed! Thread Name: " +
	               Thread.currentThread().getName());
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	}
	
}
