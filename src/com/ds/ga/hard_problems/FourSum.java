package com.ds.ga.hard_problems;

import java.util.*;

/**
 * Given an array of integers and a target sum find quadruplet of numbers in the array whose sum is euql to the given sum
 * For e.g. [1,0,-1,0,-2,2], target = 0 the answer will be [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Brute Force solution - Use 4 loops to generate all possible quads within the array to find out the quad with required sum.
 * TC - O(n^4) - Running 4 for loops of approximately n elements.
 */
public class FourSum {
    private static boolean validateInput(int[] input) {
        if(null == input || input.length < 4) {
            return false;
        }
        return true;
    }

    /**
     * Better solution here follows the path of 3 sum better solution. INstead of just keeping i fixed here we will keep i and j fixed and move the internal loop
     * between J + 1 to n to find out k and the 4th element that maatch the given sum. Thus time complexityh of this solution will reduce from O(n^4) to O(n^3) as
     * we are running just 3 for loops.
     * Rest all logic of using the set to store unique elements remains the same.
     * TC - O(N^3)
     * SC - O(N) using to store the unique elements in the set.
     */
    private static List<List<Integer>> fourSumBetter(int[] input, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> solutions = new HashSet<>();
        int size = input.length;
        if(!validateInput(input)) {
            return result;
        }
        for(int i=0; i<size; i++) {
            for(int j = i+1; j<size; j++) {
                Set<Integer> quadrupletSet = new HashSet<>();
                for(int k= j+1; k<size; k++){
                    int sum = targetSum - (input[i] + input[j] + input[k]);
                    if(quadrupletSet.contains(sum)) {
                        Integer[] candidates = new Integer[]{input[i], input[j], input[k], sum};
                        Arrays.sort(candidates);
                        solutions.add(new ArrayList<Integer>(Arrays.asList(candidates)));
                    }
                    else {
                        quadrupletSet.add(input[k]);
                    }
                }
            }
        }
        for(List<Integer> candidate : solutions) {
            result.add(candidate);
        }
        return result;
    }

    // Same as 3 sum optimal solution. Here we fix i and j and keep moving k and l to find out the required sum
    // TC - O(N^3)
    // SC - O(1) no aditional space is required for find out the sorted elements.
    private static List<List<Integer>> fourSumOptimal(int[] input, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        int size = input.length;
        if(!validateInput(input)) {
            return result;
        }
        Arrays.sort(input);
        for(int i=0; i<size; i++) {
            if(i > 0 && input[i] == input[i - 1]) {
                continue;
            }
            for(int j=i+1; j<size; j++) {
                if(input[j] == input[j-1]) {
                    continue;
                }
                int k = j+1;
                int l = size - 1;
                while(k < l) {
                    int sum = input[i] + input[j] + input[k] + input[l];
                    if (sum < targetSum) {
                        k++;
                    }
                    else if(sum > targetSum) {
                        l--;
                    } else {
                        // We got the required sum add it to result and find new quadruplet
                        List<Integer> candidateList = Arrays.asList(input[i], input[j], input[k], input[l]);
                        result.add(candidateList);
                        k++;
                        l--;
                        while(k < l && input[k] == input[k-1]){
                            k++;
                        }
                        while(k < l && input[l] == input[l+1]) {
                            l--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,0,-1,0,-2,2};
        int k = 0;
        List<List<Integer>> betterSolution = fourSumBetter(input, k);
        System.out.println("DONE for better solution");
        List<List<Integer>> optimalSolution = fourSumOptimal(input, k);
        System.out.println("DONE for optimal solution");
    }
}
