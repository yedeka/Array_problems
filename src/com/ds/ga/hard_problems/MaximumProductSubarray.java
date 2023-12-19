package com.ds.ga.hard_problems;

/**
 * Problem Statement: Given an array that contains both negative and positive integers, find the maximum product subarray.
 *
 * Input:
 *  Nums = [1,2,-3,0,-4,-5]
 * Output:
 *  20
 * Explanation:
 *  In the given array, we can see (-4)×(-5) gives maximum product value.
 *
 *  Brute Force - Run a nested loop with outer loop starting from i = 0... n-1 and inner loop starting at j = i ... n-1 and find out the product of the subarrays
 *  found for each subarray beginning at i. Maintain a max product of the subarray and return the maximum product found.
 *  TC - Approximately O(N^2)
 *  SC - O(1)
 *
 *  Optimal Solution - While finding out optimal solution we need to consider multiple possibilities with multiple observations.
 *  Observation 1 - All positives. Just take product of all elements to get the maximum product sum.
 *  Observation 2 - Even number of negatives. Again since we have even number of negative elements they will cancel each other out and hence taking a
 *  multiplication of all elements gives us the answer.
 *  Observation 3 - Odd number of negatives. In this case if we ignore any negative element then we get the remaining elements product which can be compared to
 *  find the maximum product subarray. What it means is at each negative element the answer lies in either the prefix product or the suffix product of the subarray.
 *  Observation 4 - We can skip 0 as well since product of any number with 0 will always be a 0. ) can be skipped by changing the prefix product to 1 when a zero
 *  element is encountered.
 *
 *  Algorithm - We will run 2 for loops over the elements of the array. One loop will run from the beginning of the array to find out the prefix product of the
 *  array and one for loop will run from the end of the array to find out the suffix product of the array and the winning product in this case will give us
 *  the maximum product sub-array. We will just have to remember to reset the product to 1 when a prefix or suffix product of 0 is encountered.
 */
public class MaximumProductSubarray {
    private static boolean validateInput(int[] input) {
        if(null == input || input.length == 0) {
            return false;
        }
        return true;
    }
    private static int findMaximumProductSubarray(int[] input) {
        int maxProduct = Integer.MIN_VALUE, prefixProduct = 1, suffixProduct = 1, length = input.length;
        boolean zeroFlag = false;
        if(!validateInput(input)) {
            return maxProduct;
        }

        for(int i=0; i<length; i++) {
            if(prefixProduct == 0) {
                zeroFlag = true;
                prefixProduct = 1;
            }
            if(suffixProduct == 0) suffixProduct = 1;
            prefixProduct *= input[i];
            suffixProduct *= input[length-i-1];
            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));
        }
        if(maxProduct < 0 && zeroFlag) {
            // Special condition to consider zero when the maximum product is a negative number and array has a zero element in this case taking zero element
            // will give us the maximum product.
            return 0;
        }
        return maxProduct;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,0,-4,-5};
        System.out.println("Maximum product of a subarray => "+findMaximumProductSubarray(input));
    }
}
