package com.code.repository.study.io;

import java.io.File;
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
			System.out.println(new File(".").getAbsolutePath());//相对工程目录
			fis = new FileInputStream("src/main/resources/fileInputStream.txt");
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
