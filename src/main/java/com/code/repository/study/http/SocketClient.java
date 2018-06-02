package com.code.repository.study.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("127.0.0.1",8080);
		// 定义输出
		OutputStream out = socket.getOutputStream();
		boolean autoflush = true;
		PrintWriter write = new PrintWriter(out,autoflush);
		
		// 定义输入
		InputStream in = socket.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		// send an http request 
		write.println("GET / HTTP/1.1");
		write.println("Host:localhost:8080");
		write.println("Connection:Close");
		write.println();
		
		// 读回执
		StringBuffer sb = new StringBuffer(8096);
		boolean loop = true;
		while(loop) {
			if(read.ready()) {
				int i=0;
				while(i!=-1) {
					i = in.read();
					sb.append((char)i);
				}
				loop = false;
			}
			Thread.currentThread().sleep(50);
		}
				
		// 打印内容
		System.out.println(sb.toString());
		socket.close();		
	}
}
