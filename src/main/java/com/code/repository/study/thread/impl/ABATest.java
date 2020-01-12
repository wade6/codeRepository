package com.code.repository.study.thread.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题：
 * 线程1和线程2通过CAS机制并发修改某个共享变量,同时获取当前值A；假设线程2速度比线程1要快；
 * 线程2将变量从A改成B，又将其从B改成A；
 * 线程1这时再进行cas也会成功，但是此A非原始A，可能会出现问题；比如优惠充值场景
 */
public class ABATest {

    private static AtomicInteger total = new AtomicInteger(10); // 会出现ABA
    private static AtomicStampedReference<Integer> total1 = new AtomicStampedReference(10,0); // 防止ABA

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                total.compareAndSet(10,80);
                boolean result = total.compareAndSet(80,10);
                System.out.println("t1 ok! result:"+result);
            }
        });

        Thread t2 = new Thread(()->{
            boolean result = total.compareAndSet(10,30);
            System.out.println("t2 ok! result:"+result);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("==================");
        Thread t11 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                total1.compareAndSet(10,80,total1.getStamp(),total1.getStamp()+1);
                total1.compareAndSet(80,10,total1.getStamp(),total1.getStamp()+1);
                System.out.println("t11 ok!");
            }
        });

        Thread t21 = new Thread(()->{
                int stamp = total1.getStamp(); // 获取stamp，t11睡眠1秒，模拟同时并发获取stamp的场景
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = total1.compareAndSet(10,30,stamp,stamp+1);
                System.out.println("t21 ok! result:"+result);

        });

        t11.start();
        t21.start();

    }
}
