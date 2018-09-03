package com.code.repository.study.classes;

public class DemoA {
	
	static{ // Class.forName(xxx.xx.xx);调用该类时，JVM会执行该类的静态代码段。
		System.out.println("DemoA static method!");
	}

	public void aa(){
		System.out.println("DemoA");
	}
}
