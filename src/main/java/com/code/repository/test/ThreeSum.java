package com.code.repository.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    // 直接的方法全部遍历一遍，3个for，去重是个问题
    public static void main(String[] args) {
        int[] nums = new int[]{-2,-1,0,1,2};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 先排序，然后用双指针模式
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length<3){
            return result;
        }
        Arrays.sort(nums); // 从小到大排序
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                break;
            }
            if(i > 0 && nums[i-1]==nums[i]){
                continue;
            }
            int left=i+1;
            int right=nums.length-1;
            while(left<right){
                int sum = nums[i]+nums[left]+nums[right];
                if(sum==0){
                    // 记录
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right && nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum<0){
                    left++;
                }else if(sum>0){
                    right--;
                }
            }
        }
        return result;
    }
}
