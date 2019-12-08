package com.code.repository.test;

import java.util.*;

/**
 * 给定一个字符串，找出其中不含有重复字符的 最长子串 的长度
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class StringMaxSubStr {
    public static void main(String[] args){
        String str = " ";
        StringMaxSubStr.test2(str);
    }



    public static int test2(String s){
        // 使用hashSet实现滑动窗口
        int length = s.length();
        Set<Character> set = new HashSet<Character>();
        int i=0;
        int j=0;
        int count=0;
        while(j<length){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j)); // 加入队列
                j++; // 后移一位
                count = Math.max(count,j-i);
            }else{// 在set中，滑动一个格子
                set.remove(s.charAt(i));
                i++; // i后移一位
            }
        }
        return count;
    }

    public static int  test1(String s){
        // 长度暂存器 int length=0;
        // 计数器 int count=0;
        // 字符缓存器 List<>
        // 1、分开s为字符数据
        // 2、循环check每个字符
        // 2.1、判断字符缓存器list中是否有字符；
        // 2.1.1、没有，计数器+1；
        // 2.1.2、有字符，长度暂存器=当前计数；字符缓存器清空，添加当前字符到字符缓存器；
        // 2.2、返回长度暂存器；
        //  String str = "abbabcbb";
        //

        char[] charArray = s.toCharArray();
        int length=0;
        int count=0;
        Map<Character,Integer> dupIndex = new HashMap<>();
        List<Character> charStr = new ArrayList<Character>();
        int index=0;
        for(;index<charArray.length;index++){
            char ch = charArray[index];
            if(charStr.contains(ch)){
                if(length < count){
                    length = count;
                }
                count=0;
                index = dupIndex.get(ch);// 获取重新开始计数位置
                dupIndex.clear();
                charStr.clear();
            }else{
                count++;
                charStr.add(ch);
                dupIndex.put(ch,index);
            }
        }
        if(length<count){
            length = count;
        }
        System.out.println(length);
        System.out.println(charStr);
        return length;
    }
}
