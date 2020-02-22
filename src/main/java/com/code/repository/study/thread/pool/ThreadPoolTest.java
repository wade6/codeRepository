package com.code.repository.study.thread.pool;

import java.util.concurrent.*;

public class ThreadPoolTest {

	public static void main(String[] args) {
		customThreadPool(); // 使用ThreadPoolExecutor 自定义线程池
		// cachePool(); // 使用 Executors 创建默认线程池
		// fixedThreadPool(); // 使用 Executors 创建默认线程池
	}

	/**
	 * 自定义线程池，ThreadPoolExecutor
	 */
	private static void customThreadPool() {

		int corePoolSize = 2;  // 主流线程个数
		int maximumPoolSize = 4; // 线程最大个数
		long keepAliveTime = 0L; // 大于主流线程个数的线程空闲的过期时间  wait for new tasks before terminating
		TimeUnit unit = TimeUnit.MILLISECONDS; // 时间单元
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(1); // 工作队列，有三种类SynchronousQueue、LinkedBlockingQueue(在所有 corePoolSize 线程都忙时新任务在队列中等待)、ArrayBlockingQueue，分别对应同步队列、无界队列(Integer.MAX_VALUE)、有界队列。
		ThreadFactory threadFactory = new ThreadFactory(){ // 自定义生成线程的名称
			@Override
			public Thread newThread(Runnable r) {
				Thread myThread=new Thread(r);
				myThread.setName("my-"+Thread.currentThread().getName());
				return myThread;
			}
		};
		RejectedExecutionHandler handler =new ThreadPoolExecutor.CallerRunsPolicy(); // 队列满，且达到maximumPoolSize时，再添加线程到线程池的拒绝策略，有4种 ThreadPoolExecutor.AbortPolicy抛异常、ThreadPoolExecutor.DiscardPolicy 默默抛弃不处理、ThreadPoolExecutor.DiscardOldestPolicy 抛弃等待最久的、ThreadPoolExecutor.CallerRunsPolicy 由向线程池提交任务的线程来执行该任务

		// 构造线程池
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,threadFactory,handler);
		for(int i=1;i<=20;i++){ // 并发最大任务数 = maximumPoolSize+队列深度
			try {
				int j=i;
				executorPool.execute(new Runnable(){
					@Override
					public void run() {
						try {
							System.out.println(j + " start at "+Thread.currentThread().getName() );
							Thread.sleep(2000);
							System.out.println(j +" end at "+Thread.currentThread().getName());
						} catch (InterruptedException e) {
						}
					}
				});
				System.out.println(i+" ok");
			}catch(Exception e){
				System.out.println(i+" fail:"+e.getMessage());
			}
		}
		executorPool.shutdown();
		System.out.println("over");
	}

	/**
	 * 使用固定数目的线程池,Executors
	 * 使用LinkedBlockingQueue固定大小
	 */
	private static void fixedThreadPool() {
		ExecutorService executorPool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			executorPool.execute( () -> {
					System.out.println(Thread.currentThread().getName()
							+ "正在执行。。。");
			});
		}
		executorPool.shutdown();
	}

	/**
	 * 动态增减线程数目的线程池,Executors
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
