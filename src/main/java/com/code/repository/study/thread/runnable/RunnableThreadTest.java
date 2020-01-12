package com.code.repository.study.thread.runnable;

/**
 * 通过实现Runnable，创建线程类
 * 实现Runnable接口比继承Thread类所具有的优势： 可以避免java中的单继承的限制
 */
public class RunnableThreadTest implements  Runnable{

    private static int num =0; // 静态类，多线程共享

    public RunnableThreadTest(int num){
        this.num = num;
    }

    @Override
    public void run() {
        //多线程同时消耗一个变量
        while(num > 0){
            synchronized (this){ //防止并发
                if(num >0){
                    try {
                        Thread.sleep(100L);
                        System.out.println("thread-"+Thread.currentThread().getName()+": get "+num--);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        RunnableThreadTest rt = new RunnableThreadTest(10);
        new Thread(rt,"rt1").start();
        new Thread(rt,"rt2").start();
    }
}
