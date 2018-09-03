package com.code.repository.study.thread.algorithm;

/**
 * 建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
 * 
 * @author huamo Apr 25, 20132:59:16 PM
 */
public class AlternatePrintABCDemo {

    // 打印线程
    public static class Printf implements Runnable {

        private Object pre;

        private Object self;

        public Printf(Object pre, Object self) {
            this.pre = pre;
            this.self = self;
        }

        @Override
        public void run() {
            
            for(int i=0;i<10;i++){
                
                synchronized (pre) {
                    synchronized (self){
                        
                        System.out.println(Thread.currentThread().getName());
                        if(Thread.currentThread().getName().equals("C")){
                            System.out.println("----");
                        }
                        // 唤醒self上等待的线程
                        self.notifyAll();
                    }
                    try {
                        // 将自己添加到pre的等待队列中
                        pre.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
                
            }
            
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Printf p1 = new Printf(c, a);
        Printf p2 = new Printf(a, b);
        Printf p3 = new Printf(b, c);

        Thread t1 = new Thread(p1, "A");
        Thread t2 = new Thread(p2, "B");
        Thread t3 = new Thread(p3, "C");

        t1.start();
        Thread.sleep(10);
        t2.start();
        Thread.sleep(10);
        t3.start();
        Thread.sleep(10);

    }

}
