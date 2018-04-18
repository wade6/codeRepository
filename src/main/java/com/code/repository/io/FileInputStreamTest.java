package com.code.repository.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * @author hm
 *
 */
public class FileInputStreamTest {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;
		try {
			// 创建字节文件输入流
			fis = new FileInputStream("fileInputStream.txt");
			// 最多读64字节
			byte[] b = new byte[64];
			// 实际读取字节数
			int len = 0;
			// 循环读取并输出
			while((len=fis.read(b)) > 0) {
				System.out.print(new String(b,0,len)+"|");//注意： 读取后，对中文转换有乱码问题
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
		
	}
}
