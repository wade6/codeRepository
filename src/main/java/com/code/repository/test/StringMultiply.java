package com.code.repository.test;

public class StringMultiply {

    // 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    public static void main(String[] args) {
        String num1="123";
        String num2="45";
        System.out.println(StringMultiply.multiply(num1,num2));
    }

    // 竖式乘法
    //   123
    //    45
    //-----------
    //    15
    //   10
    //   5
    //   12
    //   8
    //  4
    // 05535
    public static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }

        int l1 = num1.length();
        int l2 = num2.length();

        int[] result = new int[l1+l2];
        for(int i=l1-1;i>=0;i--){
            int n1 = num1.charAt(i)-'0';
            for(int j=l2-1;j>=0;j--){
                int n2 = num2.charAt(j)-'0';
                int single = n1*n2; // 单数相乘
                single = single + result[i+j+1]; // 本位相加
                int now = single%10; // 取本位数字
                int pre = single/10; // 进位数字
                result[i+j+1] = now;
                result[i+j]+=pre;
            }
        }

        StringBuilder sb  = new StringBuilder();
        int i =0;
        while(result[i]==0){
            i++;
        }
        for(;i<l1+l2;i++){
            sb.append(result[i]);
        }
        return sb.toString();
    }
}
