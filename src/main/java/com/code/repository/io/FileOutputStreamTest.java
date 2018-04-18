package com.code.repository.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("fileOutputStream.txt");
			String str = "hello world! 写文件";
			String str2 = "写文件 hello world! ";
			fos.write(str.getBytes());
			fos.write('\n');
			fos.write(str2.getBytes());
			System.out.println("ok");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fos.close();
		}
		
		
	}
}
