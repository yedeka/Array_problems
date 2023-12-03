package com.ds.ga.medium_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * A leader in an array is a number that is greater than all the numbers on it's right side.
 *
 * Brute Force solution - A brute force solution for this will be running a nested loop for each number to test the numbers on it's right to check whether the number
 * is greater than all the numbers to it's right. This will result in a TC of O(n^2).
 *
 * Optimal solution - Since we have to find the elements such that all the elements on the right are smaller than the elements, if we look closely we are trying
 * to find the greatest elements in the array travelling from right. Since the last element does not have any elements greater than itself it will always be one
 * of the leaders and current max. Now we maintain a global max and add an element in the leader list if it is greater than current max. This way we can get
 * all the leaders in the array.
 */
public class LeadersInTheArray {
    private static int[] findLeaders(int[] input) {
        if (null == input || 0 == input.length || 1 == input.length) return input;
        int length = input.length;
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> result = new ArrayList<>();
        for(int i= length-1; i>=0; i--) {
            if(input[i] > max) {
                result.add(input[i]);
                max = input[i];
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    public static void main(String[] args) {
        int[] input = new int[]{4, 7, 1, 0};
        int[] leaders = findLeaders(input);
        String leaderElements = "";
        for(int i=0; i<leaders.length; i++) {
            leaderElements += leaders[i] + ", ";
        }
        System.out.println(leaderElements);
    }
}
