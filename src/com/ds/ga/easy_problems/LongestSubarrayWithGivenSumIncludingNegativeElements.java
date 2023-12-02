package com.ds.ga.easy_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * This approach varies from the regular 2 pointer approach is such a way that we are calculating sum over all the elements since negatives are possible.
 * Hence instead of 2 pointer approach we use a prefix sum approach here where we maintain a HashMap of prefix sum and at every element we keep on checking
 * for the remainder of the sum in the prefix array to find the difference.
 */
public class LongestSubarrayWithGivenSumIncludingNegativeElements {
    private static int findLongestSubArrayIncludingNegatives(int[] input, int k) {
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        int maxLength = 0;
        long sum = 0;
        for(int i=0; i<input.length; i++){
            sum += input[i];
            if(sum == k) {
                maxLength = Math.max(maxLength, i + 1);
            }
            long remaining = sum - k;
            // If we find that the pre-fix sum already exists we do not have to add 1 to find the length since we are already at correct index so just return the
            // difference. This probably is gonna be the same logic for all prefix sum problems
            if(prefixSumMap.containsKey(remaining)) {
                maxLength = Math.max(maxLength, i - prefixSumMap.get(remaining));
            }
            // The exists condition is added to cater to zeroes since in case of zeros we will go as left as possible thus hampering the capability to look beyond
            //zeros for maximum sum.
            if(prefixSumMap.get(sum) == null) {
                prefixSumMap.put(sum, i);
            }

        }
        return maxLength;
    }
    public static void main(String[] args) {
        int[] input = new int[]{-1, 1, 1};
        int k = 1;
        System.out.println("Length of longest subarray with sum = "+k+" => "+findLongestSubArrayIncludingNegatives(input, k));
        input = new int[]{0, 2, 0, 3};
        k = 3;
        System.out.println("Length of longest subarray with sum = "+k+" => "+findLongestSubArrayIncludingNegatives(input, k));

    }
}
