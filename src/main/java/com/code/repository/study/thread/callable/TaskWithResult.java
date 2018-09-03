/**
 * Project: study
 * File Created at 2013-5-19下午4:08:41
 */
package com.code.repository.study.thread.callable;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 
 * @author zhaoyuanli
 * 2013-5-19下午4:08:41
 */
// 创建一个线程类，实现Callable<T>接口，其中泛型T决定返回类型
public class TaskWithResult implements Callable<String> {

    /**
     * 2013-5-19下午4:08:56
     */
	// 实现call方法，实现线程的逻辑
    @Override
    public String call() throws Exception {
//        Thread.sleep(1000);
        
        TimeUnit.MILLISECONDS.sleep(1000);
        
        return Thread.currentThread().getName()+" :TaskWithResult";
    }
    
    public static void main(String[] args) {
    	// 创建线程池
        ExecutorService exe = Executors.newCachedThreadPool();
        // 声明返回结果List，类型是Future<String>
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        // 将线程提交给线程池，将结果保存到List中
        for(int i=0;i<5;i++){
            results.add(exe.submit(new TaskWithResult()));
        }
        
        System.out.println("ok");
        
        // 调用fs.get()拿结果，导致主线程阻塞，等待子线程返回数据
        int i=1;
        for(Future<String> fs : results){
            try {
                if(!fs.isDone()){
                    System.out.println(i+" waiting...");
                    i++;
                }
                System.out.println(fs.get());
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }
        
        // 关闭线程池
        exe.shutdown();
        
        System.out.println("main over!");
    }

}
