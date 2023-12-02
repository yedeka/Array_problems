package com.ds.ga.easy_problems;

public class LongestSubarrayWithGivenSum {
    private static int findLongestSubArrayWithGivenSum(int k, int[] input) {
        int answer = 0, beginIndex = 0, currentSum = 0;
        for(int i=0; i<input.length; i++){
            currentSum += input[i];
            if(currentSum == k) {
                answer = Math.max(answer, i - beginIndex + 1);
                beginIndex = i+1;
            }
            if(currentSum > k){
                currentSum = 0;
                beginIndex = i+1;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 5, 1, 9};
        // input = new int[]{10, 2, 3, 9};
        // input = new int[]{2, 3, 9, 10};
        int k =10;
        System.out.println("Length of longest subarray with given sum => "+findLongestSubArrayWithGivenSum(k, input));
    }
}
