package com.code.repository.study.log;

/**
 * 日志原理<p>
 * 通过调用Throwable.getStackTrace()方法实现
 * @author zhaoyuanli
 *
 */
public class Main {
	
	public static void main(String[] args) {
		TestThrowableLog t1=new TestThrowableLog();
		t1.test();
		System.out.println("=========");
		TestThreadLog t2 = new TestThreadLog();
		t2.test();
		
	}

}
