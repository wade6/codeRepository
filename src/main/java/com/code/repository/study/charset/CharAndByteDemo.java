package com.code.repository.study.charset;

/**
 * char 和 byte的关系
 * @author hm
 *
 */
public class CharAndByteDemo {
	
	public static void main(String[] args) {
		char a = '中';
		byte[] b = CharAndByteDemo.charToByte(a);
		System.out.println("b0: " + b[0]);
		System.out.println("b1: " + b[1]);
		System.out.println("byte[] to char: " + CharAndByteDemo.byteToChar(b));
	}
	
	/**
	 * 字符char转换成byte[]
	 * 
	 * 首先要明白的是：
	 * 	byte是字节数据类型，由8位二进制组成；
	 * 	char是字符数据类型，占2个字节byte；
	 *  
	 * @param c
	 * @return
	 */
	public static byte[] charToByte(char c) {
		byte[] b = new byte[2];// 一个字符是2个字节，所以b的长度为2
		b[0] = (byte)((c & 0xFF00) >> 8);// 取8位高字节，0xff00 为 1111 1111 0000 0000
		b[1] = (byte)(c & 0xff); // 取8位低字节，0xff为 1111 1111
		return b;
	}
	
	/**
	 * 字节转字符
	 * 
	 * @param b
	 * @return
	 */
	public static char byteToChar(byte[] b) {
		char c = (char)((b[0] << 8) | b[1]);// b[0]左移8位，变成16位二进制 ，b[1]就是低位， 或运算，
		return c;
	}
}
