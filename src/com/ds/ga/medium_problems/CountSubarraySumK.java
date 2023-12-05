package com.ds.ga.medium_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array and an expected sum count the number of sub-arrays with required sum.
 * array[] = {3, 1, 2, 4}, k = 6
 * answer - 2, The sub-arrays that have sum =6 are [3, 1, 2] and [2, 4]
 *
 * Brute Force - Generate all sub-arrays and sum up the elements of the sub-arrays to find out whether the sum matches the expected sum and return the number of
 * sub-arrays with matching sum. This consists of a total of 3 for loops, an outermost for loop for looping over each element to get the start index of every
 * sub-array, an inner loop to cover the end index of every subarray and the innermost loop to calculate sum of the indices formed by the outer 2 loops.
 * This way we can calculate the sum of the array elements and match them with expected sum.
 * TC - O(N^3)
 *
 * Better solution - We can eliminate the internal loop for adding up the indices while looping from the second for loop, thus reducing complexity from O(N^3)
 * to O(N^2)
 *
 * Optimal approach - This is handled using Prefix sum. At each index we calculate the prefix-sum for subarray ending at that index. If prefix sum for index i
 * is X then if there is already a sub-array with remainder prefix-sum of k-X then it means we found the required sub-array, hence we will maintain a map of
 * prefix-sum -> count mapping and thus at each prefix sum we will add the values to the map with prefix_sum, count tuple.
 *
 * TC - O(N)
 */
public class CountSubarraySumK {
    private static int findSubarraySumEqualsK(int[] input, int k){
        Map<Integer, Integer> prefixMap = new HashMap<>();
        // Added to handle the use case of first element being equal to k
        prefixMap.put(0, 1);
        int prefixSum = 0, cnt = 0;
        for(int i=0; i<input.length; i++){
            prefixSum+= input[i];
            cnt += prefixMap.getOrDefault(prefixSum -k, 0);
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[] input = new int[]{3, 1, 2, 4};
        int k = 3;
        System.out.println("Number of subsets with sum = "+k+" => "+findSubarraySumEqualsK(input, k));
    }
}
