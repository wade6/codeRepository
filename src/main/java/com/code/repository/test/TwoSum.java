package com.code.repository.test;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-2,-1,0,1,2};
        List<List<Integer>> result = twoSum(nums);
        System.out.println(JSON.toJSONString(result));
    }
    public static List<List<Integer>> twoSum(int[] nums) {

//        int res[] = new int[nums.length];
//        for(int i=0;i<nums.length;i++){
//            res[0-nums[i]]=nums[i];
//        }
//        for(int j=0;j<nums.length;j++){
//            if(res[nums[j]]!=0){
//                System.out.println(nums[j]+","+res[nums[j]]);
//            }
//        }
        return null;

    }
}
