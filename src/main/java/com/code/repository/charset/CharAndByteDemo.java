package com.code.repository.charset;

/**
 * char 和 byte的关系
 * @author hm
 *
 */
public class CharAndByteDemo {

	public static void main(String[] args) {
		char a = 'a';
		byte[] b = CharAndByteDemo.charToByte(a);
		System.out.println(b[0]);
		System.out.println(b[1]);
	}
	
	/**
	 * 字符char转换成byte
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
		b[0] = (byte)((c & 0xFF00) >> 8);// 0xff00 为 1111 1111 0000 0000
		b[1] = (byte)(c & 0xff); // 0xff为 1111 1111
		return b;
	}
	
	public static char byteToChar(byte[] b) {
		char c = (char)(((b[0] & 0xff) << 8)|(b[1] & 0xff));
		return c;
	}
}
