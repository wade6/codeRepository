package com.code.repository.study.thread.runnable;

/**
 * 通过继承Thread实现线程类，和Runnable的区别只是在于实现方式
 * Thread也是实现Runnable接口，Thread和Runnable的实质是实现的关系
 */
public class ThreadExtendsTest extends Thread {

    private static int num=0; // 设置静态变量，多线程共享

    public ThreadExtendsTest(int num,String name){
        this.num = num;
        this.setName(name);
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        //多线程同时消耗一个变量
        while(num > 0){
            synchronized (this){ //防止并发
                if(num >0){
                    try {
                        Thread.sleep(100L);
                        System.out.println("thread-"+this.getName()+": get "+num--);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadExtendsTest t1 = new ThreadExtendsTest(10,"t1");
        ThreadExtendsTest t2 = new ThreadExtendsTest(10,"t2");
        // 第一种启动方式
        t1.start();
        t2.start();
        // 第二种启动方式
//        new Thread(t1).start();
//        new Thread(t2).start();

        // 是否等待线程执行完毕
//        t1.join();
//        t2.join();
        System.out.println("main over!");

    }
}
