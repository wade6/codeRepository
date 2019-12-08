package com.code.repository.test;

import java.util.Arrays;

public class StringSort {

    public static void main(String[] args) {
        System.out.println(StringSort.checkInclusion2("ab","eidbaooo"));
    }

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
            count2 = new int[26];
            String tmp = s2.substring(i-s1.length()+1,i+1);
            for(int ch : tmp.toCharArray()){
                count2[ch-'a']++;
            }
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
