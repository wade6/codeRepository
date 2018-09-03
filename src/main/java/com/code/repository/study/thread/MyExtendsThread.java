package com.code.repository.study.thread;

/**
 * 继承Thread
 * 
 * 虽然我们在这里调用的是start（）方法，但是实际上调用的还是run（）方法的主体
 * 
 * @author zhaoyuanli
 *
 */
public class MyExtendsThread extends Thread {
	
	private String name;
	
	public MyExtendsThread(String name){
		this.name= name;
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(name+":"+i);
			
			// 特定条件下，线程主动将执行权交给其他线程
			if(i==3 || i==6){
				 System.out.println("线程的礼让");
				 // 让出执行权
				Thread.yield();
			}
			try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}
	
	public static void main(String[] args) {
	    // 声明两个线程
		MyExtendsThread e1 = new MyExtendsThread("e1");
		MyExtendsThread e2 = new MyExtendsThread("e2");
		
		// 调用方法不对,还是按顺序运行
//		e1.run(); 
//		e2.run(); 
		
		// 因为需要用到CPU的资源，所以每次的运行结果基本是都不一样的
		e1.start();
		e2.start();
		
		System.out.println("main end");
	}

}
