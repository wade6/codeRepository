package com.code.repository.study.jvm.outofmemory;

/**
 * 栈溢出  Exception in thread "main" java.lang.StackOverflowError
 * 
 * -XX:+HeapDumpOnOutOfMemoryError -Xss(默认1M)
 * 
 * @author zhaoyuan.lizy
 *
 */
public class StackOverflow {
	
	public int i=0;
	
	public void stackLeak(){
		i++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		StackOverflow sof = new StackOverflow();
		 
			sof.stackLeak();
		 
		
	}

}
