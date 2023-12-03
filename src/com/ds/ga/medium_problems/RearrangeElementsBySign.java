package com.ds.ga.medium_problems;

/**
 * Brute Force - Simply split the array into 2 arrays of positive and negative elements and then pick elements alternatively from those 2 arrays to arrange the
 * elements in required order.
 * TC - O(N) SC - O(N) total O(N) space required to store 2 arrays of size n/2.
 *
 * Optimal approach - Optimal approach does this in single pass. Basically since the resultant array needs to start from positive element we assign positive index
 * as 0 and negative index as 1 in the resultant array. Upon parsing the input array whenever we get a positive number we put it at index 0 and a negative number is
 * put at index 1 and then we increment the indices by 2 to make the alternative arrangement. When both positive and negative indices exceed n the program finishes, or
 * when all the elements of the original array are parsed the program is finished.
 */
public class RearrangeElementsBySign {
    private static int[] rearrangeElementsBySign(int[] input) {
        int positiveIndex = 0, negativeIndex = 1;
        int[] result = new int[input.length];
        for(int i=0; i<input.length; i++) {
            if(input[i] > 0){
                result[positiveIndex] = input[i];
                positiveIndex += 2;
            } else {
                result[negativeIndex] = input[i];
                negativeIndex += 2;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, -1, -2, -3};
        int[] result = rearrangeElementsBySign(input);
        System.out.println("DONE");
    }
}
