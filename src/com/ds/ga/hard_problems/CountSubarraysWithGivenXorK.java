package com.ds.ga.hard_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers count the number of sub arrays that have bitwise XOr of elements to be equal to k.
 * Input Format: A = [4, 2, 2, 6, 4] , k = 6
 * Result: 4
 * Explanation: The subarrays having XOR of their elements as 6 are  [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]
 *
 * Brute Force solution - Generate all the possible sub-arrays of given input.
 * How to generate all possible sub-arrays of given input array. Run an outer loop i = 0 ... n-1 to begin with all possible starting indices and
 * run an inner loop as well j = i .. n-1 to have all possible end indices. All combinations of i and j in this case give us all possible sub-arrays of
 * given input array. Then simply calculate XOR of all the elements in the subarrays to find the count.
 * TC - O(n ^ 3)
 *
 * Better approach - Instead of running a third inner loop from i .. j  to find out XOR of elements we can simply find out XOR of elements in the loop of j itself
 * by taking XOR of element at jth index with the XOR of previous array elements i.e. with XOR of elements from i .... j - 1.
 * TC - O (n^2)
 *
 * Optimal solution - Works on the approach of prefix XOr and hashing. At any index i we calculate the prefix XOR till that index. Now if we have any subarray with
 * prefix sum k then we must have a subarray starting with sum k ^ X where X is the prefix xor of the subarray with index i. We maintain a map of all subarray
 * prefix xor values
 */
public class CountSubarraysWithGivenXorK {
    private static boolean validateInput(int[] input) {
        if(null == input || 0 == input.length) {
            return false;
        }
        return true;
    }
    private static int subArrayCountWithXorK(int[] input, int k) {
        int count = 0, prefixXor = 0;
        if(!validateInput(input)) {
            return count;
        }
        Map<Integer, Integer> xorMap = new HashMap<>();
        xorMap.put(0, 1);
        for(int i=0; i<input.length; i++) {
            prefixXor ^= input[i];
            int candidateXor = k ^ prefixXor;
            count += xorMap.getOrDefault(candidateXor, 0);
            xorMap.put(prefixXor, xorMap.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }
    public static void main(String[] args) {
        int[] input = new int [] {4, 2, 2, 6, 4};
        int k = 6;
        System.out.println("Count of subarrays with XOR value "+k+" => "+subArrayCountWithXorK(input, k));
    }
}
