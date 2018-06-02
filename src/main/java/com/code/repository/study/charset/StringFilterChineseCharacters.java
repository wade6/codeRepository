package com.code.repository.study.charset;

/**
 * 只保留汉字
 * @author hm
 *
 */
public class StringFilterChineseCharacters {

	public static void main(String[] args) {
		String str = "这是@个%例子123#@《》：‘’‘";
		String reg = "[^\u4e00-\u9fa5]"; // unicode的汉字字符集合
        str = str.replaceAll(reg, "");  
        System.out.println(str); 
	}
}
