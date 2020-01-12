package com.code.repository.study.thread.impl;

/**
 * 生产者消费者同步算法<p>
 * 使用synchronized同步产品池中的生产和消费操作<p>
 * 使用wait()和notifyAll()实现线程睡眠和唤醒<p>
 * 注意：不能使用notify()实现唤醒，该方法唤醒的是某个线程，存在唤醒同类线程的风险
 * 
 * @author huamo
 * Apr 25, 20132:53:17 PM
 */
public class ProducterAndCustomerDemo {

    // 产品
    public static class Product {

        private String name;

        public Product(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    // 产品池
    public static class Pool {

        // 产品池最大容量，有界缓冲区
        Product[] pros  = new Product[5];

        int       index = 0;

        // 投入产品池
        public synchronized void push(Product p) {

            // 循环遍历条件
            while (index == 5) {

                System.out.println(" 满了，待生产：" + p.getName());

                // 让当前线程让出产品池对象锁，并进入休眠，等待notify()的唤醒，而且此方法必须在while循环中调用
                try {
                    System.out.println("生产者 "+Thread.currentThread().getName()+" 进入休眠！" );
                    this.wait();
                    System.out.println("被唤醒：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }

            }
            
            
            pros[index++] = p;
            
            // 生产了一个产品，唤醒所有的其他线程（生产或消费）进行活动
            this.notifyAll();
            
            System.out.println(Thread.currentThread().getName() + " 生产了：" + p.getName());

        }

        // 从产品池中获取产品
        public synchronized Product pop() {

            while (index == 0) {
                System.out.println("没货");

                // 让当前线程让出对象锁，并进入休眠，等待notify()的唤醒，而且此方法必须在while循环中调用
                try {
                    System.out.println("消费者 "+Thread.currentThread().getName()+" 进入休眠!");
                    this.wait();
                    System.out.println("被唤醒：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
            
            // 即将消费了一个产品，唤醒所有的其他线程（生产或消费）等待获取对象锁进行活动
            this.notifyAll();
            return pros[--index];

        }

    }

    // 生产者
    public static class Producter implements Runnable {

        private Pool pool;

        public Producter(Pool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            // 生产产品
            for (int i = 0; i < 4; i++) {
                String name = Thread.currentThread().getName() + i;
                Product p = new Product(name);
                pool.push(p);
            }

        }

    }

    // 消费者
    public static class customer implements Runnable {

        private Pool pool;

        public customer(Pool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            // 消费产品
            for (int i = 0; i < 4; i++) {
                Product p = pool.pop();
                System.out.println("第" + i + "次消费了：" + p.getName());
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        Pool pool = new Pool();
        Producter p = new Producter(pool);
        customer c = new customer(pool);

        // 每个producter需要完成3个产品的指标，一共9个产品，如果c1消费后剩余的产品大于缓冲区大小，t线程将永久等待
        Thread t1 = new Thread(p, "p1->");
        Thread t2 = new Thread(p, "p2->");
        Thread t3 = new Thread(p, "p3->");
        
        // 每个customer需要消费2个，如果t不能生产出足够的产品，c1将永久等待
        Thread c1 = new Thread(c, "c1");
        Thread c2 = new Thread(c, "c2");

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(10);
        c1.start();
        c2.start();
        
        System.out.println("main end!");
    }

}
