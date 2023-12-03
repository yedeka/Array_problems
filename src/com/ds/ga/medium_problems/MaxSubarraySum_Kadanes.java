package com.ds.ga.medium_problems;

/**
 * Brute Force approach - Run an outer loop for starting index of every subArray i.e. i = 0 .... n-1, run an inner loop for every possible end index of sub array
 * i.e. j = i .... n-1. Then run the third loop to calculate subarray sum from i to j i.e. k = i .... j. With this approach we find out the sum and keep the max sum.
 * TC - O(n^3)
 *
 * Better solution - The sum of current subarray i.e. subarray ending at index j can be found out by adding current element at index j to the subarray sum of elements
 * till index j-1. This means we can eliminate the third loop which is used for finding the sum and can calculate the sum in TC O(n^2)
 *
 * Optimal Solution - Also known as Kadane's algorithm. While picking the subarray sum if the current element is greater than the subarray sum always pick the
 * current element since ths sum of subarray starting at that element is greater than the sum of sub-array ending at that element. What it means is if adding current
 * element generates a -ve sum we will not carry the element in sum and reduce the sum to zero. However we will always add the element in sum and take a call based
 * on the value of the sum and not on the value of the element itself.
 */
public class MaxSubarraySum_Kadanes {
    private static int findMaxSubArraySum(int[] input) {
        int result = Integer.MIN_VALUE, sum = 0;
        for(int i=0; i<input.length; i++) {
            sum += input[i];
            if (sum < 0) {
                sum = 0;
            }
            result = Math.max(result, sum);
        }
        return result;
    }
    public static void main(String[] args) {
        int[] input = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum sub array sum => "+findMaxSubArraySum(input));
    }
}
