/**
 * Project: study
 * File Created at 2013-5-23下午11:28:16
 */
package com.code.repository.study.thread.synchroniz;

import java.util.concurrent.TimeUnit;

/**
 * 同步的实现方式有两种，同步方法和同步块，这两种方式都要用到synchronized关键字。<p>
 * 给一个方法增加synchronized修饰符之后就可以使它成为同步方法，
 * 这个方法可以是静态方法和非静态方法，但是不能是抽象类的抽象方法，也不能是接口中的接口方法。<p>
 * 
 * 在一个对象被某个线程锁定之后，其他线程是可以访问这个对象的所有非同步方法的<p>
 * 
 * @author zhaoyuanli
 * 2013-5-23下午11:28:16
 */
public class TestDemo {
    
    public synchronized void methodA(String name) throws InterruptedException{
        for(int i=0;i<5;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("thread "+name+" run A:"+i);
        }
    }
    
    public synchronized void methodB(String name) throws InterruptedException{
        for(int i=0;i<5;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("thread "+name+" run B:"+i);
        }
    }
    
    public  void methodC(String name) throws InterruptedException{
        for(int i=0;i<5;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("thread "+name+" run C:"+i);
        }
    }
    
    public static void main(String[] args) {
        
        final TestDemo test = new TestDemo();
        
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
                    test.methodB("th2");
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
                    test.methodC("th3");
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
