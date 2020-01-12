package com.code.repository.study.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 实现Callable接口，线程运行后可以有返回值，线程运行时主线程可以继续运营
 * 待有结果时，返回结果再继续运行
 */
public class CallableTreadTest implements Callable<String> {

    private String name;
    public CallableTreadTest(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("subTread running...");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("subTread running done !");
        return this.name;
    }

    public static void main(String[] args) throws  Exception {
        CallableTreadTest t1 = new CallableTreadTest("t1");
        FutureTask<String> task = new FutureTask<String>(t1); // 生成futureTask
        new Thread(task).start(); // 启动线程
        System.out.println("main process other done!"); // 主线程继续执行其他逻辑
        if(!task.isDone()){
            System.out.println("main waiting subTread result ...");
        }
        String result = task.get(); // 主线程阻塞，等待子线程结果
        System.out.println("main get sub result:"+result);
    }
}
