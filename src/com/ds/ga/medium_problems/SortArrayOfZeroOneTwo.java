package com.ds.ga.medium_problems;

/**
 * The 3 algorithms that can used to solve this problem are given as follows.
 * 1] Brute Force - Simply use Merge sort to sort the array and give the sorted array. TC = O(nlogn), SC = O(n) exgtra array required to keep the interim elements.
 * 2] Better Solution - Count the number of zeros, ones and 2s and simply overrwite the array with those many counts. TC = O(n) but 2 iterations.
 */
public class SortArrayOfZeroOneTwo {
    /**
     * Optimal solution is given by Dutch National Flag.
     * Algorithm - 0 .... low-1 = 0
     *             low .... mid -1 = 1
     *             hi+1 .... n-1 = 2
     * If we consider the rule what they signify is the array is sorted from 0 ....mid-1 and high+1.....n-1. That means the array between mid ..... hi is unsorted.
     * Since the entire array is unsorted mid can point at index 0 and hi can point at index n-1.
     * Since low is at the beginning we put low as well at 0 this the condition of everything from 0 ... low-1 which is non-existent is still sorted.
     *
     * After this the algorithm works as follows
     * 1] If a[mid] == 0 then we swap a[mid] and a[low] since first occurance of 1 starts at index low and it ends at index mid. So swapping the elements at low and mid still keep the sorted part of the array sorted.
     * This makes the array from mid + 1 .... hi + 1 unsorted. i.e. mid ++ and since we need everything from 0 ... low - 1 to be 0 we will also have low ++ since recently swapped 0 is at index low.
     * 2] If a[mid] == 1 since everything from 0 ... mid-1 is sorted from 0 ... 1 we dont need to do anything, we can simply increment mid to be mid ++.
     * 3] if a[mid] == 2 we just swap a[mid] with a[hi] and decrement hi to be hi-- so that the condition of everything between 0 .... mid-1 and hi+1 ... n-1 still remains true.
     */
    private static void sortArrayOfZeroOneTwoOptimalSolution(int[] input){
        int mid = 0, low = 0, hi = input.length - 1;
        while(mid <= hi) {
            if(input[mid] == 0) {
                swapElements(input, low, mid);
                low++;
                mid++;
            } else if(input[mid] == 1) {
                mid++;
            } else {
                swapElements(input, mid, hi);
                hi--;
            }
        }
    }
    private static void swapElements(int[] input, int low, int hi) {
        int temp = input[low];
        input[low] = input[hi];
        input[hi] = temp;
    }
    private static void sortArrayOfZeroOneTwoBetterSolution(int[] input) {
        if(null == input || input.length == 0) {
            return;
        }
        // Step 1 - Populate count of zero, one and two elements.
        int zeroCnt = 0, oneCnt = 0, twoCnt = 0;
        for(int i=0; i< input.length; i++) {
            if(input[i] == 0) {
                ++zeroCnt;
            } else if(input[i] == 1) {
                ++oneCnt;
            } else {
                ++twoCnt;
            }
        }
        // Step 2 - Now populate the array with corresponding number of zeros, ones and twos.
        for(int i=0; i<zeroCnt; i++) {
            input[i] = 0;
        }
        for(int i = zeroCnt; i<zeroCnt+oneCnt; i++) {
            input[i] = 1;
        }
        for(int i = zeroCnt+oneCnt; i<zeroCnt+oneCnt+twoCnt; i++) {
            input[i] = 2;
        }
        return;
    }
    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 2, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        //sortArrayOfZeroOneTwoBetterSolution(input);
        System.out.println("DONE with Better solution");
        sortArrayOfZeroOneTwoOptimalSolution(input);
        System.out.println("DONE with Optimal solution");
    }
}
