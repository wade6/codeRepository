package com.code.repository.test;

import java.util.HashSet;
import java.util.Set;

/**
 * 取最大的连续长度值
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,3,3,2};
        System.out.println(longestConsecutive(nums));
    }
    public static int longestConsecutive(int[] nums){
        if(nums.length==0){
            return 0;
        }
        int longest = 0;
        Set<Integer> nSet = new HashSet<>();
        for(int num : nums){
            nSet.add(num);
        }
        for(int num : nums){
            if(!nSet.contains(num-1)){ // 判断是否是连续的第一个数字，其前一个数字肯定不在数组中
                int cnum = num;
                int length =1;
                while(nSet.contains(cnum+1)){
                    cnum = cnum+1;
                    length++;
                }
                longest =  longest < length ? length:longest; // 取最大值
            }
        }
        return longest;
    }
}
