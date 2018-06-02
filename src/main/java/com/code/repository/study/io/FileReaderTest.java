package com.code.repository.study.io;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

	public static void main(String[] args) throws IOException {
		FileReader fr = null;
		try{
			// 创建字符文件输入流
			fr = new FileReader("fileInputStream.txt");
			// 每次读取128个字符
			char[] b = new char[128];
			int len;
			// 循环读取
			while((len = fr.read(b)) > 0) {
				System.out.print(new String(b,0,len));
			}
		}catch (Exception e) {
		}finally {
			fr.close();
		}
	}
}
