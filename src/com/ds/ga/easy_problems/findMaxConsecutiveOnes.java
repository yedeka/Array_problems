package com.ds.ga.easy_problems;

public class findMaxConsecutiveOnes {
    private static int findMaxConsecutiveOnes(int[] input) {
        int maxSum = Integer.MIN_VALUE, runningSum = 0;
        for(int i=0; i<input.length; i++) {
            if(input[i] == 0) {
                maxSum = Math.max(maxSum, runningSum);
                runningSum = 0;
            } else {
                runningSum += 1;
            }
        }
        return maxSum;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 0, 1, 1, 1, 0, 0, 1, 1};
        System.out.println("Maximum consecutive ones in the input array are => "+findMaxConsecutiveOnes(input));
    }
}
