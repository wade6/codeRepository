/**
 * Project: study
 * File Created at 2013-5-19下午3:30:56
 */
package com.code.repository.study.thread;

/**
 * 斐波那契数列  f(n) = f(n-1) + f(n-2)
 * 
 * @author zhaoyuanli
 * 2013-5-19下午3:30:56
 */
public class Fibonacci implements Runnable {
    
    private int n = 0;
    
    private String name;
    
    private int a = 0;
    
    private int b=1;
    
    
    
    public Fibonacci(int n){
        this.n = n;
        
    }

    /**
     * @author zhaoyuanli
     * 2013-5-19下午3:31:37
     */
    @Override
    public void run() {
        this.name = Thread.currentThread().getName();
        System.out.println(name+":"+a);
        System.out.println(name+":"+b);
        for(int i=0;i<n;){
            a = a+b;
            System.out.println(name+":"+a);
            b = a+b;
            System.out.println(name+":"+b);   
            i = i+2;
        }
        
    }
    
    public static void main(String[] args) {
        Fibonacci f = new Fibonacci(5);
        
        Thread t1 = new Thread(f,"t1:");
        t1.start();
        System.out.println("ok!");
    }

}
