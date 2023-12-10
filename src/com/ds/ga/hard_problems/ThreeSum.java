package com.ds.ga.hard_problems;

import java.util.*;

/**
 * Find 3 elements in the given array whose sum is 0.
 * Brute Force - Run 3 loops with i -> 0 to n-3, j = i+1 to n-2 and k = j+1 to n-1 and calculate the sum of input[i] + input[j] + input[k].
 * If the sum is 0 then we have found out triplet for three sum.
 *
 * Better solution - get rid of the third loop with calculation of arr[k] = -(arr[i] + arr[j]).
 * This can be done by hashing the array but one thing to note here is the fact that we do not need the duplicates in our output. In order to do that
 * we will keep the elements except i and j in the set and will check against those elements for 0 sum thus we can avoid repeating the same element in
 * the output.
 */
public class ThreeSum {
    /**
     * Better solution is of TC O(N^2) since we eliminate the third loop, it uses an extra space of maximum O(N) to store the triplet set which is the worst case
     * if all the array elements are triplets.
     */
    private static List<List<Integer>> findThreeSumBetter(Integer[] input) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> tripletSet = new HashSet<>();
        for(int i=0;i <input.length-1; i++) {
            HashSet<Integer> tripletCandidatesSet = new HashSet<>();
            for(int j =i+1; j<input.length; j++) {
                int candidateValue = -(input[i] + input[j]);
                if(tripletCandidatesSet.contains(candidateValue)) {
                    Integer[] triplet = new Integer[]{input[i], input[j], candidateValue};
                    // We sort the array to ensure that we get unique triples to avoid different combinations of elements to be treated as different triplets.
                    Arrays.sort(triplet);
                    List sortedTriplet = new ArrayList(Arrays.asList(triplet));
                    tripletSet.add(sortedTriplet);
                } else {
                    tripletCandidatesSet.add(input[j]);
                }
            }
        }
        for(List<Integer> triplet: tripletSet){
            result.add(triplet);
        }
        return result;
    }

    /**
     * We are getting rid of the external set used for creating unique triplets to be returned. This can simply be done by sorting the array and then using it
     * for finding the triplets.
     * So this approach works in 2 steps
     * Step 1 - Sort the array
     * Step 2 - Start with i at index 0, j at i + 1 and k at n-1. If  a[i] + a[j] + a[k] is less than 0 then since array is sorted to increase the sum we will
     * increase j, if the sum is greater than 0 to decrease the sum we will decrement k, this ways we wil find the unique triplets.
     */
    private static List<List<Integer>> findThreeSumOptimal(Integer[] input) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        int size = input.length;
        for(int i=0; i<input.length; i++) {
            // don't take i if it's value is same as previous
            if(i > 0 && input[i] == input[i-1]) {
                continue;
            }
            // Now for a non-duplicate i start the loop for j
            int j = i+1;
            int k = size - 1;
            while(j < k) {
                // We dont get sorted order if j and K cross so we dont consider the elements where j crosses k.
                // This also takes care of the condition of i reaching to n-2 or n-1 in that case k and j will not be valid indices but it will come up as a situation
                // such that either j == k for i = n-2 and j > k for i == n-1 we can successfully avoid that situation with just the j < k condition.
                int sum = input[i] + input[j] + input[k];
                if(sum < 0) {
                    j++;
                } else if(sum > 0) {
                    k--;
                } else {
                    // We found the required triplet now add it in result array
                    List<Integer> tripletList = Arrays.asList(input[i], input[j], input[k]);
                    result.add(tripletList);
                    j++;
                    k--;
                    while(j < k && input[j] == input[j-1]) {
                        j++;
                    }
                    while(j < k && input[k] == input[k+1]){
                        k--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] input = new Integer[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> threeSumResult = findThreeSumBetter(input);
        System.out.println("DONE Better");
        List<List<Integer>> threeSumResultOptimal = findThreeSumOptimal(input);
        System.out.println("DONE Optimal");

    }
}
