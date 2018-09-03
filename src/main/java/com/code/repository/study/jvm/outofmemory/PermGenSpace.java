package com.code.repository.study.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * 永久区溢出   Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 * 
 * -XX:+HeapDumpOnOutOfMemoryError -XX:MaxPermSize=2m
 * @author zhaoyuan.lizy
 *
 */
public class PermGenSpace {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i=0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}

}
