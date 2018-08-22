package com.code.repository.study.log;

public class MyThrowableLogger {
	
	public static void log(String message){
		StackTraceElement[] statck=new Throwable().getStackTrace();
		System.out.println("log message:"+message);
		for(int i=1;i<statck.length;i++){
			System.out.println("at "+statck[i].toString());
		}
	}

}
