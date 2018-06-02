package com.code.repository.study.io;

public class AutoColseableTest {

	public static void main(String[] args) {
		try(MyResource my = new MyResource()) {
			my.invoke();
		} catch (Exception e) {
		}
		System.out.println("end");
	}
	
	// 资源自动关闭的接口AutoCloseable
	// 在使用的时候只需要把资源在try块中用小括号括起来就可以了
	static class MyResource implements AutoCloseable{
		@Override
		public void close() throws Exception {
			System.out.println("i am closed!");
			
		}
		
		public void invoke() {
			System.out.println("i am working!");
		}
	}

}

