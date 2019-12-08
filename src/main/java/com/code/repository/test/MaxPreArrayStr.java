package com.code.repository.test;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀
 */
public class MaxPreArrayStr {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String result = MaxPreArrayStr.longestCommonPrefix(strs);
        System.out.println(result);

        String s1 = "flower";
        String s2="flight";
        System.out.println(s2.indexOf(s1));
    }

    public static  String longestCommonPrefix2(String[] strs) {
        if(strs==null || strs.length==0){
            return "";
        }
        String firstStr = strs[0]; // 以第一个字符串为基准
        String result = "";
        for(int i=0;i<firstStr.length();i++){ // 从第一个字符开始
            char ch = firstStr.charAt(i);
            for(String s : strs){
                if(s.length()<=i){
                    return result;
                }
                char compChar = s.charAt(i);
                if(ch!=compChar){// 不相同，结束查找，返回字符串
                    return result;
                }
            }
            result = result + ch;
        }
        return result;
    }


    public static  String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        int i=0;
        String result = "";
        boolean stop=false;
        while(true){ // 从第一个字符开始，遍历每个字符串，取第一个字符是否相同，不相同就结束
            if(strs[0].length()<=i){
                break;
            }
            char ch = strs[0].charAt(i);
            for(String str : strs){
                if(str.length()<=i ||ch != str.charAt(i) ){
                    stop=true;
                }
            }
            if(stop){
                break;
            }
            result = result +ch;
            i++;
        }
        return result;
    }
}
