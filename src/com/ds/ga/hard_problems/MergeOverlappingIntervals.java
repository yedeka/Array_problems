package com.ds.ga.hard_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals merge the overlapping intervals such that we have a resultant array of non overlapping intervals.
 * Example 1:
 *
 * Input: intervals=[[1,3],[2,6],[8,10],[15,18]]
 *
 * Output: [[1,6],[8,10],[15,18]]
 *
 * Explanation: Since intervals [1,3] and [2,6] are overlapping we can merge them to form [1,6]
 *  intervals.
 *
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 *
 * Output: [[1,5]]
 *
 * Explanation: Since intervals [1,4] and [4,5] are overlapping we can merge them to form [1,5].
 *
 * Brute Force Solution - Sort the pairs based on the first element of the interval. Check for the overlapping intervals over the sorted list. i.e. check the first
 * element of the current element with last element of the previous element, if that is less than the last of previous then the intervals will overlap and can be
 * merged.
 */
public class MergeOverlappingIntervals {

    // TC - O(nlogN) sorting + O(2n) in the nested loop every element is visited twice once in the j loop and second time as part of i loop so the inner loop
    // actually does not run exactly n times or even n times in amortized manner.
    private static ArrayList<int[]> mergeIntervalsBrute(int[][] input) {
        ArrayList<int[]> result = new ArrayList<>();
        if(null == input || 0 == input.length ) {
            return result;
        }
        // Step 1 - Sort the input array to start the brute force approach
        Arrays.sort(input, (a, b) -> {
            if(a[0] == b[0] )return Integer.compare(a[1], b[1]);
            else return Integer.compare(a[0], b[0]);
        });
        // Step 2 - now start looking at the intervals to sort
        for(int i=0; i<input.length; i++) {
            int start = input[i][0];
            int end = input[i][1];

            if(!result.isEmpty() && end <= result.get(result.size() - 1)[1]) {
                continue;
            }
            for(int j = i+1; j<input.length; j++) {
                if(input[j][0] <= end){
                    end = Math.max(end, input[j][1]);
                }
                else {
                    break;
                }
            }
            result.add(new  int[]{start, end});
        }
        return result;
    }

    private static ArrayList<int[]> mergeIntervals(int[][] input) {
        ArrayList<int[]> result = new ArrayList<>();
        if (null == input || input.length == 0) {
            return result;
        }
        result.add(input[0]);

        for(int i=1; i<input.length; i++) {
            int[] resultElement = result.get(result.size() - 1);
            if(input[i][0] <= resultElement[1]) {
                resultElement[1] = Math.max(input[i][1], resultElement[1]);
            } else {
                result.add(input[i]);
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[][] intervalsInput = new int[][]{
                {1, 3}, {2, 6},{8, 10}, {8, 9}, {9, 11}, {2, 4}, {15, 18}, {16, 17}
        };
        ArrayList<int[]> result = mergeIntervalsBrute(intervalsInput);
        System.out.println("DONE brute");
        result = mergeIntervals(intervalsInput);
        System.out.println("DONE Optimal");
    }
}
