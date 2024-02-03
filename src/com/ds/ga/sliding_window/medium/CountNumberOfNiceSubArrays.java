package com.ds.ga.sliding_window.medium;

/**
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if
 * there are k odd numbers on it. Return the number of nice sub-arrays.
 * Example 1:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 */
public class CountNumberOfNiceSubArrays {
    private static boolean isValidInput(int[] input, int k) {
        if(null == input || input.length < k) {
            return false;
        }
        return true;
    }

    private static int findNiceSubarrayCount(int[] input, int k) {
        if(!isValidInput(input, k)){
            return 0;
        }
        int left = -1, right = 0, count = 0, oddCount = 0, length = input.length;
        while(right < length) {
            // acquire
            if(input[right] % 2 == 1) {
                oddCount += 1;
            }
            right++;
            //Check
            while(oddCount > k) {
                left++;
                // release
                if(input[left] % 2 == 1){
                    oddCount--;
                }
            }
            if(oddCount == k){
                count += 1;
            }
        }
        return count;
    }
    public static void main(String[] args){
        int[] input = new int[]{1,1,2,1,1};
        int k = 3;
        input = new int[]{2,2,2,1,2,2,1,2,2,2};
        k = 2;
        System.out.println("Number of nice subarrays for k = "+k+" => "+findNiceSubarrayCount(input, k));
    }
}
