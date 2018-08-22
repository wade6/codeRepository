package com.code.repository.study.thread.pool;

import java.util.concurrent.*;

public class ThreadPoolTest {

	public static void main(String[] args) {

		customThreadPool();
		// cachePool();
		// fixedThreadPool();
		
	}

	/**
	 * 自定义线程池
	 */
	private static void customThreadPool() {

		int corePoolSize = 2;  // 主流线程个数
		int maximumPoolSize = 4; // 线程最大个数
		long keepAliveTime = 0L; // 大于主流线程个数的线程空闲的过期时间  wait for new tasks before terminating
		TimeUnit unit = TimeUnit.MILLISECONDS; // 时间单元
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(); // 工作队列，有三种类SynchronousQueue、LinkedBlockingQueue(在所有 corePoolSize 线程都忙时新任务在队列中等待,maximumPoolSiz失效)、ArrayBlockingQueue，分别对应同步队列、无界队列、有界队列。

		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, unit, workQueue);
		
		for(int i=0;i<20;i++){
			System.out.println("start i:"+i);
			executorPool.execute(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println(Thread.currentThread().getName()
							+ "正在执行。。。");
				}
			});
			System.out.println("end i:"+i);
		}
		
		executorPool.shutdown();
	}

	/**
	 * 使用固定数目的线程池<br>
	 * 使用LinkedBlockingQueue固定大小
	 */
	private static void fixedThreadPool() {
		ExecutorService executorPool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			executorPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()
							+ "正在执行。。。");
				}

			});
		}
		executorPool.shutdown();
	}

	/**
	 * 动态增减线程数目的线程池<br>
	 * 使用SynchronousQueue 实现线程实时增加
	 */
	private static void cacheThreadPool() {
		ExecutorService executorPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executorPool.execute(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()
							+ "正在执行。。。");
				}

			});
		}
	}

}
