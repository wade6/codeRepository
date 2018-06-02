package com.code.repository.util;
/**
 * 字节与二进制转换工具
 * @author hm
 *
 */
public class ByteToBinaryUtil {

	// 字节转换二进制
	public static String byteToBinary(byte src) {
		// 二进制结果
		StringBuilder result = new StringBuilder();
		/**
		 *  字节是8位二进制，表现出来是一个整数，范围为-128~127;
		 *  java中用补码表示二进制数，补码的最高位是符号位，最高位为“0”表示正数，最高位为“1”表示负数
		 *  byte为一字节8位，最高位是符号位，即最大值是01111111，因正数的补码是其本身，即此正数为01111111,十进制表示形式为127 
		 */
        for (int i = 0; i < 8; i++) {
        	// 从左到右获取每一位的值
            result.append(src%2 == 0 ? '0' : '1'); 
            // >>>不带符号右移，高位补0
            src = (byte)(src >>> 1);  
        }  
        // 翻转顺序
        return result.reverse().toString();  
	}
	
	public static void main(String[] args) {
		System.out.println(2%2);
	}
}
