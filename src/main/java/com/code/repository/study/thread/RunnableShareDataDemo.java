package com.code.repository.study.thread;

/**
 * 实现Runable接口，实现资源共享 数据共享最易出现同步问题，数据的同步问题使用synchronized解决
 * 
 * @author huamo Apr 25, 201310:21:07 AM
 */
public class RunnableShareDataDemo {

    /**
     * 卖票类，没有同步
     * 
     * @author huamo Apr 25, 201310:16:35 AM
     */
    public static class SellTicket implements Runnable {

        private int ticketNum;

        public SellTicket(int num) {
            this.ticketNum = num;
        }

        @Override
        public void run() {
            
            for(int i=0;i<5;i++){
                
                if (ticketNum > 0) {
                    // 在判断条件之后，具体操作之前，如果多个线程同时进入，就会出现同步问题
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    
                    System.out.println(Thread.currentThread().getName() + " sold:" + ticketNum--);
                }
            }

            

        }

    }

    public static void main(String[] args) throws InterruptedException {
        // 初始化一个实例 
        SellTicket s = new SellTicket(10);

        // 创建三个线程，窗口一起卖票
        Thread t1 = new Thread(s, "t1");
        Thread t2 = new Thread(s, "t2");
        Thread t3 = new Thread(s, "t3");

        t1.start();
        t2.start();
        t3.start();
        
        // 线程跑完在继续下面的操作
        t1.join();
        t2.join();
        t3.join();
        
        System.out.println("实现同步的线程");
        
     // 初始化一个实例 
        SellTicket2 s2 = new SellTicket2(10);

        // 创建三个线程，窗口一起卖票
        Thread t21 = new Thread(s2, "t21");
        Thread t22 = new Thread(s2, "t22");
        Thread t23 = new Thread(s2, "t23");

        t21.start();
        t22.start();
        t23.start();
        
     // 线程跑完在继续下面的操作
        t21.join();
        t22.join();
        t23.join();
        
     // 初始化一个实例 
        SellTicket2 s3 = new SellTicket2(10);

        // 创建三个线程，窗口一起卖票
        Thread t31 = new Thread(s3, "t31");
        Thread t32 = new Thread(s3, "t32");
        Thread t33 = new Thread(s3, "t33");

        t31.start();
        t32.start();
        t33.start();
        
        System.out.println("main end!");
    }

    /**
     * 卖票类，处理同步问题,同步快
     * 
     * @author huamo Apr 25, 201310:16:35 AM
     */
    public static class SellTicket2 implements Runnable {

        private int ticketNum;

        public SellTicket2(int num) {
            this.ticketNum = num;
        }

        @Override
        public void run() {

            // 同步块 实现同步该线程实例
            for(int i=0;i<5;i++){
                
                synchronized (this) {
                    
                    if (ticketNum > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }

                        System.out.println(Thread.currentThread().getName() + " sold:" + ticketNum--);
                    }
                }
            }
            

        }

    }
    
    /**
     * 卖票类，处理同步问题,同步方法
     * 
     * @author huamo Apr 25, 201310:16:35 AM
     */
    public static class SellTicket3 implements Runnable {

        private int ticketNum;

        public SellTicket3(int num) {
            this.ticketNum = num;
        }
        
        // 同步方法
        private synchronized void sell(){
            if (ticketNum > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println(Thread.currentThread().getName() + " sold:" + ticketNum--);
            }
        }

        @Override
        public void run() {

            for(int i=0;i<5;i++){
                sell();
            }

        }

    }

}
