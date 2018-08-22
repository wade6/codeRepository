package com.code.repository.study.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出  java.lang.OutOfMemoryError: Java heap space
 * 
 * -XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  -XX:MaxNewSize=10m -XX:MaxPermSize=256m
 * 
 * -XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  -XX:MaxNewSize=256m
 * 
 * @author zhaoyuan.lizy
 *
 */
public class HeapSpace {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i=0;
		while(true){
			list.add(new String(""+i++));
		}
	}

}
