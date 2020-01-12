package com.code.repository.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 */
public class GetPermutation {
    public static void main(String[] args) {
        System.out.println(getPermutation(3,2));
    }

    public static String getPermutation(int n, int k) {
        boolean[] remains = new boolean[n+1];
        System.out.println(2%1);
        return findNext(n,k,remains);
    }

    public static String findNext(int n,int k,boolean[] remains){

        String indexStr="";

        int s = k%nx(n-1);
        int t = k/nx(n-1) + (s>0?1:0); // 第几组
        int count=0;
        for(int i=1;i<remains.length;i++){
            if(remains[i]!=true){ // 没被使用
                count++;
                if(count==t){
                    indexStr = indexStr+i;
                    remains[i]=true;// 占用
                    break;
                }
            }
        }
        if(n-1>0){
            indexStr = indexStr + findNext(n-1,s==0?k:s,remains);
            return indexStr;
        }
        return indexStr;
    }

    public static int nx(int n){
        int result=1;
        if(n<=0){
            return 0;
        }
        for(int i=1;i<=n;i++){
            result = result*i;
        }
        return result;
    }
}
