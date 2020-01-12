/**
 * Project: study
 * File Created at 2013-5-19下午3:56:20
 */
package com.code.repository.study.thread.pool.executor;

import com.code.repository.study.math.Fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author zhaoyuanli
 * 2013-5-19下午3:56:20
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        
        for(int i=0;i<5;i++){
            executor.execute(new Fibonacci(5));
        }
        executor.shutdown();
        System.out.println("main over!");
    }
}
