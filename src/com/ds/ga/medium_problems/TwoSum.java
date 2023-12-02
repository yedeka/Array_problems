package com.ds.ga.medium_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    private static int[] findTwoSum(int[] input, long targetSum) {
        int[] result = new int[]{-1, -1};
        Map<Integer, Integer> originalIndexMap = new HashMap<>();
        for(int i=0;i <input.length; i++){
            originalIndexMap.put(input[i], i);
        }
        Arrays.sort(input);
        int left =0, right = input.length-1;
        while(left < right) {
            long sum = input[left] + input[right];
            if(targetSum == sum) {
                result[0] = originalIndexMap.get(input[left]);
                result[1] = originalIndexMap.get(input[right]);
                return result;
            } else if(targetSum > sum) {
                ++left;
            } else {
                right--;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] input = new int[]{2,6,5,8,11};
        long target = 14;
        int[] result = findTwoSum(input, target);
        System.out.println("Two numbers with target sum "+target+" are at index "+result[0]+" and "+result[1]);
    }
}
