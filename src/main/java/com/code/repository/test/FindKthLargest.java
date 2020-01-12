package com.code.repository.test;

public class FindKthLargest {

    public static void main(String[] args) {
        int[] nums = new int[]{99,99};
        int k=1;
        System.out.println(findKthLargest(nums,k));

    }

    public static final  int findKthLargest(int[] nums, int k) {
        return sideSearch(nums,k-1,0,nums.length-1);
    }

    public static int sideSearch(int[] nums, int k,int low,int high){
        int tmp = nums[low]; // 取一个参考数，确定其排序后的位置，用快排方法
        int l = low;
        int h = high;
        while(l < h){ // 进行一轮排序定位
            while(l < h && nums[h]<tmp){ // 从大到小，小数不动
                h--;
            }
            if(l < h){ // 大数左移,左指针+1
                nums[l] = nums[h];
                l++;
            }
            while (l < h && nums[l] > tmp){
                l++;
            }
            if(l < h){ // 小数右移，右指针-1
                nums[h] = nums[l];
                h--;
            }
        }
        nums[h] = tmp;
        int pos = h;
        if(pos > k){ // 寻找左边
            return sideSearch(nums,k,low,pos-1);
        }else if(pos < k){// 寻找右边
            return sideSearch(nums,k,pos+1,high);
        }
        return tmp;
    }



}
