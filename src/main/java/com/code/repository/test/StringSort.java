package com.code.repository.test;

import java.util.Arrays;

public class StringSort {

    public static void main(String[] args) {
        System.out.println(StringSort.checkInclusion2("ab","eidbaooo"));
    }

    // 通过比较s1长度的s2子串和s1中字符的个数是否相同来进行判断
    // 用那么我们可以定义一个26字母的字母表数组来存储个数。两个数组相减如果都为0，那么肯定相同；
    // 如果不为0，那么此时子链不相同，将s1整体在s2的位置右移一位，也就是说将s2此时与s1对应的头部的元素的个数减一，
    // 将要对应的下一位元素的个数加一，这样就完成的s1数组对于s2的滑动比较。
    public static boolean checkInclusion2(String s1, String s2) {//
        if(s1==null || s2==null ||s1.length()>s2.length()){
            return false;
        }
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for(int i=0 ; i<s1.length();i++){
            count1[s1.charAt(i)-'a']++;
            count2[s2.charAt(i)-'a']++;
        }
        if(cmp(s1,count1,count2)){
            return true;
        }

        for(int i=s1.length();i<s2.length();i++){
            count2[s2.charAt(i-s1.length())-'a']--;
            count2[s2.charAt(i)-'a']++;
            if(cmp(s1,count1,count2)){
                return true;
            }
        }
        return false;
    }

    public static boolean isEquals(int[] count1,int[] count2){
        for(int i = 0; i < count1.length; i++){
            if(count1[i] != count2[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean cmp(String s1,int[] count1,int[] count2){
        for(int s : s1.toCharArray()){
            if(count1[s-'a']!=count2[s-'a']){
                return false;
            }
        }
        return true;
    }

    // s1字符排序，滑动从S2取相同长度字符进行排序比较
    public static boolean checkInclusion(String s1, String s2) {//834ms
        char[] s1Array = s1.toCharArray();
        Arrays.sort(s1Array);
        String s1p = new String(s1Array);
        int length = s1.length();
        for(int i=0,j=length;j<=s2.length();i++,j++){
            String cmpStr = s2.substring(i,j);
            char[] cmpArray = cmpStr.toCharArray();
            Arrays.sort(cmpArray);
            if(s1p.equals(new String(cmpArray))){
                return true;
            }
        }
        return false;
    }
}
