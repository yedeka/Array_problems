package com.ds.ga.medium_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
* Given an array of elements find the longest consecutive sequence of elements that can be found in the array.
* Brute Force - For every element we try to find the consecutive element using Liner search. Run an outer loop to iterate over the elements and then run a liner search
* to find consecutive elements. This will be done in O(n^2) TC.
*
* Better solution - This can be done in 2 steps
*          Step 1 - Sort the entire array.
*          Step 2 - Now start iterating from index 0 to find the consecutive element by maintaining prev_smallest, count and result variables. prev_smallest stores
*                   the number at previous index to check for consecutive sequence, count stores the current count of sequence if a[i] = prev_smallest + 1 and
*                   result stores the global maximum for the maximum consecutive length.
*                   This way on each element we will calculate the count and when the sequence is broken i.e. a[i] != prev_smallest then we will update
*                   result = Math.max(result, count) and begin count as 1 indicating start of a new sequence. After all elmenets are done iterating we will
*                   return result.
*          Time Complexity - O(n + logn) - log(n) for sorting in step 1 and O(n) for iteration in step 2.
*
* Optimal solution - the optimal solution can also be done in 2 steps without distorting the array but taking O(N) extra space.
*           Step 1 - Store the elements of the array into a set.
*           Step 2 - iterate over the elements and if an element is the starting element i.e. at index i if we get a number J and J-1 does not exist in the set
*                    we start finding the numbers starting from J+1 until we can't find the numbers any more. While doing this we keep incrementing count from 1
*                    to count the number of elements in the sequence.
* */
public class LongestConsecutiveSequenceInArray {
    private static int findLongestConsecutiveSequenceLengthOptimal(int[] input) {
        int sequenceLengthMax = 1;
        Set<Integer> elementsSet = Arrays.stream(input).boxed().collect(Collectors.toSet());
        for(Integer element: elementsSet) {
            if(elementsSet.contains(element - 1)) {
                continue;
            } else { // This element is a contender to be the start of sequence now try going forwards to check if the sequence exists
                int currentElement = element;
                int currentSequenceLength = 1;
                while(elementsSet.contains(currentElement + 1)) {
                    currentElement += 1;
                    currentSequenceLength += 1;
                }
                sequenceLengthMax = Math.max(sequenceLengthMax, currentSequenceLength);
            }
        }
        return sequenceLengthMax;
    }

    public static void main(String[] args) {
        int[] input = new int[]{102, 4, 100, 1, 101, 3, 2, 1, 1};
        System.out.println("Maximum length of consecutive sequence in the input => "+findLongestConsecutiveSequenceLengthOptimal(input));

    }
}
