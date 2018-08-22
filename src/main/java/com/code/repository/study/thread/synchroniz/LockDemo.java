/**
 * Project: study
 * File Created at 2013-5-23下午11:43:04
 */
package com.code.repository.study.thread.synchroniz;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用synchronized关键字实现的同步，会把一个对象的所有同步方法和同步块看做一个整体，只要有一个被某个线程调用了，
 * 其他的就无法被别的线程执行，即使这些方法或同步块与被调用的代码之间没有任何逻辑关系，
 * 这显然降低了程序的运行效率。而使用Lock就能够很好地解决这个问题。<p>
 * 
 * 我们可以把一个对象中按照逻辑关系把需要同步的方法或代码进行分组，为每个组创建一个Lock类型的对象，实现同步。
 * @author zhaoyuanli
 * 2013-5-23下午11:43:04
 */
public class LockDemo {
    
    private Lock lock1 = new ReentrantLock();
    
    private Lock lock2 = new ReentrantLock();
    
    public  void methodA(String name) throws InterruptedException{
        lock1.lock();
        for(int i=0;i<5;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("thread "+name+" run A:"+i);
        }
        lock1.unlock();
    }
    
    public  void methodB(String name) throws InterruptedException{
        lock2.lock();
        for(int i=0;i<5;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("thread "+name+" run B:"+i);
        }
        lock2.unlock();
    }
    
public static void main(String[] args) {
        
        final LockDemo test = new LockDemo();
        
        Thread th1 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    test.methodA("th1");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            
        });
        
        Thread th2 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    test.methodA("th2");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            
        });
        
        Thread th3 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    test.methodB("th3");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            
        });
        
        th1.start();
        th2.start();
        th3.start();
        
        System.out.println("main over");
    }

}
