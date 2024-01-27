package com.ds.ga.sliding_window.medium.acquireRelease;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's
 * in the array if you can flip at most k 0's.
 */
public class MaximumConsecutiveOnesIII {
    private static boolean isInputValid(int[] input) {
        if(null == input || input.length == 0 ){
            return false;
        }
        return true;
    }

    private static int findMaxConsecutiveOnes(int[] input, int k) {
        if(!isInputValid(input)){
            return 0;
        }
        int zeroCount = 0, length = input.length, left = -1, right = 0, maxLength = 0;
        while(right < length) {
            //acquire
            if(input[right] == 0){
                zeroCount++;
            }
            right++;
            // Validity check
            while(zeroCount > k) {
                left++;
                if(input[left] == 0) {
                    zeroCount--;
                }
            }
            // Calculate size after getting valid window
            maxLength = Math.max(maxLength, right -left -1);
        }
        return maxLength;
    }
    public static void main(String[] args){
        int[] input = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        System.out.println("Maximum consecutive Ones count with "+k+" replaceable zeros => "+findMaxConsecutiveOnes(input, k));
    }
}
