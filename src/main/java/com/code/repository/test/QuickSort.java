package com.code.repository.test;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序，找一个基准值，设置low和high标，将小值放前，大值放后；
 * 直至low=high；再对左右进行快速排序，直至结束
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] s = new int[]{2,1,4,2,6,7,8};
        System.out.println(JSON.toJSONString(quickSort(s)));
    }

    public static int[] quickSort(int[] s){
        if(s.length==0){
            return s;
        }
        quick(s,0,s.length-1);
        return s;
    }
    //  0 l 0 0 m 0 0 h
    public static void quick(int[] s,int low,int high){
        if(low>=high){
            return;
        }
        int l=low;
        int r=high;
        int temp = s[low];
        while(low<high){
            while(low<high && temp < s[high]){
                high--;
            }
            if(low<high){
                s[low]=s[high];
                low++;
            }
            while(low<high && temp > s[low]){
                low++;
            }
            if(low < high){
                s[high] = s[low];
                high++;
            }
        }
        int mid = low;
        s[mid]=temp;
        quick(s,l,mid-1);
        quick(s,mid+1,r);
    }
}
