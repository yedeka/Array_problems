package com.ds.ga.hard_problems;

/**
 * Problem Statement: Given an array of numbers, you need to return the count of reverse pairs. Reverse Pairs are those pairs where i<j and arr[i]>2*arr[j].
 * for e.g. input = [40, 25, 19, 10, 9, 6, 2] for this array find all pairs of i, j such that i < j and a[i] > 2 *a[j]
 * answer for above problem is 15.
 *
 * Brute Force Approach - Generate all possible pairs of the array elements using nested loop as follows and compare the pairs
 *  for i = 0 ... n - 1
 *    for j = i+1 .. n - 1
 *       if(a[i] > 2*a[j]) count++
 * This gives us the reverse paris count.
 * TC - O(n^2)
 * SC - O(1)
 *
 * Optimal approach - Just like count inversions we use the merge part of merge sort to count the inversions in this case.
 *
 */
public class Count_Reverse_Pairs {
    private static int[] mergeSort(int lo, int hi, int[] input, int[] count) {
        if(lo == hi) {
            return new int[]{input[lo]};
        }
        int mid = (hi+lo)/2;
        int[] leftSorted = mergeSort(lo, mid, input, count);
        int[] rightSorted = mergeSort(mid+1, hi, input, count);
        countReversepairs(leftSorted, rightSorted, count);
        return mergeSortedParts(leftSorted, rightSorted);
    }

    private static void countReversepairs(int[] leftPart, int[] rightPart, int[] count) {
        int initRightCnt = 0, rightCnt = 0;
        for(int i=0; i< leftPart.length; i++){
            while(rightCnt < rightPart.length && leftPart[i] > 2 * rightPart[rightCnt]) {
                rightCnt++;
            }
            count[0] += rightCnt - initRightCnt;
        }
    }

    private static int[] mergeSortedParts(int[] leftPart, int[] rightPart) {
        int i = 0, j=0, k =0;
        int leftLength = leftPart.length, rightLength = rightPart.length, mergedLength = leftLength + rightLength;
        int[] mergedArray = new int[mergedLength];
        while(i < leftLength && j < rightLength) {
            if(leftPart[i] <= rightPart[j]) {
                mergedArray[k++] = leftPart[i++];
            } else {
                mergedArray[k++] = rightPart[j++];
            }
        }
        while(i < leftLength) {
            mergedArray[k++] = leftPart[i++];
        }
        while(j < rightLength) {
            mergedArray[k++] = rightPart[j++];
        }
        return mergedArray;
    }

    public static void main(String[] args) {
        int[] input = new int[]{40, 25, 19, 12, 9, 6, 2};
        int[] count = new int[1];
        int[] sortedArray = mergeSort(0, input.length -1 , input, count);
        System.out.println("Number of reverse pairs => "+count[0]);
    }
}
