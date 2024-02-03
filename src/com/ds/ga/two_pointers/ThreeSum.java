package com.ds.ga.two_pointers;

import java.util.Arrays;

public class ThreeSum {
    private static boolean isThreeSum(int[] input, int target) {
        if(null == input || input.length < 3) {
            return false;
        }
        Arrays.sort(input);
        int length = input.length;
        for(int i=0; i<input.length -2; i++){
            int newTarget = target - input[i];
            int left = i+1, right = length-1;
            while(left < right) {
                int subSum = input[left] + input[right];
                if(subSum < newTarget) {
                    left++;
                } else if(subSum > newTarget) {
                    right --;
                } else {
                  return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[] input = new int[]{3,7,1,2,8,4,5};
        int target = 10;
        System.out.println("Is 3 sum possible for a sum of "+target+" in given array => "+isThreeSum(input, target));
    }
}
