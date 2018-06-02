package com.code.repository.study.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// 转换流
public class InputStreamReaderTest {

	public static void main(String[] args) throws IOException {
		// 字节流转换成reader字符流
		InputStreamReader reader = new InputStreamReader(new FileInputStream("fileInputStream.txt"));
		// 包装成reader字符缓冲流
		BufferedReader br = new BufferedReader(reader);
		String str = null;
		
		try {
			// 读取每一行打印
			while((str = br.readLine())!=null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
	}
}
