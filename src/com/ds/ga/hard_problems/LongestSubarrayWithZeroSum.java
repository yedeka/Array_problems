package com.ds.ga.hard_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Input Format: N = 6, array[] = {9, -3, 3, -1, 6, -5}
 * Result: 5
 * Explanation: The following subarrays sum to zero:
 * {-3, 3} , {-1, 6, -5}, {-3, 3, -1, 6, -5}
 * Since we require the length of the longest subarray, our answer is 5.
 *
 * Brute Force - Find all the sub arrays possible from given input array and check for the subarray sum. Find the sum of those sub arrays that have zero sum
 * and return the length of longest such sub array.
 * Time Complexity - O(n^2) - Run a nested for loop with outer loop iterating over i -> 0 ... n and inner loop iterating 4
 *
 * Optimal Solution - Optimal solution is implemented using the Prefix sum and hashing. The intuition behind the logic is simple.
 * If we get same prefix sum at 2 indices of the array then it means that the sum of the elements between these 2 elements is zero and thus we get the the subarray
 * with zero sum. Thus if we store the prefix sum of all the elements in a HashMap with key as prefix sum and value as index we can get the subarry with prefix sum.
 */

public class LongestSubarrayWithZeroSum {
    private static boolean validateInput(int[] input) {
        if(null == input || input.length == 0) {
            return false;
        }
        return true;
    }
    private static int findLongestSubarrayWithZeroSum(int[] input) {
        if(!validateInput(input)) {
            return 0;
        }
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0, subarrayLength = 0;
        for(int i=0; i<input.length; i++) {
            prefixSum += input[i];
            if(0 == prefixSum) {
                subarrayLength = i + 1;
            } else {
                if(prefixSumMap.containsKey(prefixSum)) {
                    subarrayLength = Math.max(subarrayLength, i - prefixSumMap.get(prefixSum));
                } else {
                    prefixSumMap.put(prefixSum, i);
                }
            }
        }
        return subarrayLength;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1, -1, 3, 2, -2, -8, 1, 7, 10, 2, 3};
        System.out.println("Length of longest subarray with zero sum => "+findLongestSubarrayWithZeroSum(input));
    }
}
