package com.code.repository.study.log;

import java.util.Map;

public class MyThreadLogger {

	public static void log(String message){
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		for(Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()){
			System.out.println(entry.getKey().toString());
			for(StackTraceElement stack : entry.getValue()){
				System.out.println(stack.toString());
			}
		}
	}
}
