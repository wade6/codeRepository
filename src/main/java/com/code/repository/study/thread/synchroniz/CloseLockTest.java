package com.code.repository.study.thread.synchroniz;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch有一个计数器字段，您可以根据需要减少它。然后我们可以用它来阻塞一个调用线程，直到它被计数到零。
 * ContDownLatch是一个同步辅助类，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行，这就叫闭锁。
 */
public class CloseLockTest implements Runnable{

    private CountDownLatch countDownLatch;

    public CloseLockTest(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CloseLockTest ct = new CloseLockTest(countDownLatch);
        new Thread(ct).start();
        new Thread(ct).start();
        new Thread(ct).start();
        countDownLatch.await(); // 等待子线程全部执行完
        System.out.println("main thread done!");
    }

    @Override
    public void run() {
        System.out.println("sub thread run! "+Thread.currentThread().getName());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
    }
}
