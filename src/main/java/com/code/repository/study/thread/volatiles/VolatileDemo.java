/**
 * Project: study
 * File Created at 2013-5-19下午6:15:43
 */
package com.code.repository.study.thread.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。<br>
 * 而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。<br>
 * 这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * <p>
 * Java语言规范中指出：为了获得最佳速度，允许线程保存共享成员变量的私有拷贝，<br>
 * 而且只当线程进入或者离开同步代码块时才与共享成员变量的原始值对比。<br>
 * 这样当多个线程同时与某个对象交互时，就必须要注意到要让线程及时的得到共享成员变量的变化。<br>
 * 
 * @author zhaoyuanli 2013-5-19下午6:15:43
 */
public class VolatileDemo {

    private static boolean a = false;

    public static void main(String[] args) throws InterruptedException {

        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (!a) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    i++;
                    System.out.println(i);
                }
                System.out.println(a);
            }

        });
        //        th.setDaemon(true);

        th.start();

        TimeUnit.SECONDS.sleep(1);
        a = true;
        System.out.println("main over");
    }

}
