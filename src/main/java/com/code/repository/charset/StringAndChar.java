package com.code.repository.charset;

import java.io.UnsupportedEncodingException;

public class StringAndChar {
	
	static String GBK = "GBK";
	static String UTF8 = "UTF-8";
	static String UTF16 = "UTF-16";
	static String UTF16BE = "UTF-16BE";
	static String UTF16LE = "UTF-16LE";
	static String ISO = "ISO-8859-1";
	

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = new String("中"); // 汉字，GBK、unicode是2个字节；utf-8是3个字节，文件默认是UTF-8编码
		
		byte[] bit_utf_8 = str.getBytes(UTF8);
		System.out.println("utf-8:"+bit_utf_8.length);// 输出字节长度，utf-8编码多字节编码，汉字是3个，所以输出3
		
		for(byte b : bit_utf_8) {
			System.out.println( Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
		}
		
		// 把这个utf-8的字节转换成string
		String strFromUtf8 = new String(bit_utf_8);
		System.out.println("strFromUtf8:"+strFromUtf8 +" length:"+strFromUtf8.length());
		
		byte[] bit_GBK = str.getBytes(GBK);
		System.out.println("gbk:"+bit_GBK.length);// 输出字节长度，gbk编码2个字节编码，所以输出2
		
		byte[] bit_unicode = str.getBytes(ISO);
		System.out.println("iso:"+bit_unicode.length);// 输出字节长度，ISO-8859-1编码1个字节编码，所以输出2
		
		byte[] bit_utf_16 = str.getBytes(UTF16);
		System.out.println("utf-16："+bit_utf_16.length);// 输出字节长度，UTF-16编码2个字节编码，但是没有指定大小端，系统自动识别bom
		
		byte[] bit_utf_16BE = str.getBytes(UTF16BE);
		System.out.println("utf-16be:"+bit_utf_16BE.length);// 输出字节长度，UTF-16BE编码2个字节编码，所以输出2
		
		byte[] bit_utf_16LE = str.getBytes(UTF16LE);
		System.out.println("utf-16le:"+bit_utf_16LE.length);// 输出字节长度，UTF-16LE编码2个字节编码，所以输出2
		
		
		
	}
}
