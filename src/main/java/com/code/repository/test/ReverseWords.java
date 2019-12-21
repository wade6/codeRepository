package com.code.repository.test;

public class ReverseWords {

    public static void main(String[] args) {
        String str = "  this   is  a  word!";
        System.out.println(reverseWords(str));
    }

    public static String reverseWords(String s) {
        String[] strArray = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = strArray.length-1;i>=0;i--){
            if(!strArray[i].equals("")){
                sb.append(strArray[i]);
                sb.append(" ");
            }
        }
        if(sb.length()>0){
            sb.delete(sb.length()-1,sb.length());
        }

        return sb.toString();
    }
}
