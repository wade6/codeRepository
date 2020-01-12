package com.code.repository.study.io;

import java.io.IOException;
import java.util.Scanner;

public class SystemIn {

    /**
     * next() 方法遇见第一个有效字符（非空格，非换行符）时，开始扫描，即获得第一个扫描到的不含空格、换行符的单个字符串。
     * 例如：
     *  输入 " qwe "，输出"qwe"
     *  输入 " q w e "，输出"q"
     *
     * 使用nextLine()时，则可以扫描到一行内容并作为一个字符串而被获取到。
     */
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("input words:");
        String str1 = s.nextLine();
        String str2 = s.nextLine();
        System.out.println("input words is str1:"+str1+",str2:"+str2);
    }
}
