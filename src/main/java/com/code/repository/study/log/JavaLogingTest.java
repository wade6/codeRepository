package com.code.repository.study.log;

import java.util.logging.Logger;

public class JavaLogingTest {
	
	private static Logger log = Logger.getLogger("test");
	
	public static void main(String[] args) {
		System.out.println("ok");
		log.info("this is a test");
	}

}
