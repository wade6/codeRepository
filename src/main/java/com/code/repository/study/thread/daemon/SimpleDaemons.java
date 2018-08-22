/**
 * Project: study
 * File Created at 2013-5-19下午4:42:01
 */
package com.code.repository.study.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author zhaoyuanli
 * 2013-5-19下午4:42:01
 */
public class SimpleDaemons implements Runnable {

    /**
     * @author zhaoyuanli
     * 2013-5-19下午4:42:01
     */
    @Override
    public void run() {
        
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted!");
            }
            System.out.println(Thread.currentThread()+" "+this);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<5;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(Boolean.TRUE);
            daemon.start();
        }
        
        System.out.println("all daemon start!");
        TimeUnit.MILLISECONDS.sleep(175);
    }

}
