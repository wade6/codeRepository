package com.code.repository.study.thread;

/**
 * join方法的功能就是使异步执行的线程变成同步执行<p>
 * 使用join方法后，直到这个线程退出，程序才会往下执行
 * 
 * @author huamo
 * Apr 25, 201310:35:30 AM
 */
public class ThreadJoinDemo {
    
    public static class IncDemo implements Runnable{

        private static long total;
        
        @Override
        public void run() {
            
            // 增加3个数
            synchronized(this){
                
                for(int i=0;i<3;i++){
                    total ++;
                }
                System.out.println(Thread.currentThread().getName());
            }

        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        IncDemo r1 = new IncDemo();
        
        Thread[] ts = new Thread[10];
        
        // 创建10个线程
        for(int i=0;i<10;i++){
            ts[i] = new Thread(r1);
        }
        
        // 启动10个线程
        for(int i=0;i<10;i++){
            ts[i].start();
        }
        // 调用10个线程的join,10个线程全部执行完程序才能继续进行
        for(int i=0;i<3;i++){
            ts[i].join();
        }

        
        System.out.println("main end："+ IncDemo.total);
    }
    

}
