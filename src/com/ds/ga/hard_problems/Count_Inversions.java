package com.ds.ga.hard_problems;

/**
 * What is an inversion in the array - For an array if we have 2 indices i and j such that i < j then for all pairs of i and j such that i < j inversions is a list
 * of elements such that a[i] > a[j] for all i < j
 *
 * Example 1:
 * Input Format: N = 5, array[] = {1,2,3,4,5}
 * Result: 0
 * Explanation: we have a sorted array and the sorted array has 0 inversions as for i < j you will never find a pair such that A[j] < A[i].
 * More clear example: 2 has index 1 and 5 has index 4 now 1 < 5 but 2 < 5 so this is not an inversion.
 *
 * Example 2:
 * Input Format: N = 5, array[] = {5,4,3,2,1}
 * Result: 10
 * Explanation: we have a reverse sorted array and we will get the maximum inversions as for i < j we will always find a pair such that A[j] < A[i].
 * Example: 5 has index 0 and 3 has index 2 now (5,3) pair is inversion as 0 < 2 and 5 > 3 which will satisfy out conditions and for reverse sorted array we will get maximum inversions and that is (n)*(n-1) / 2.For above given array there is 4 + 3 + 2 + 1 = 10 inversions.
 *
 * Example 3:
 * Input Format: N = 5, array[] = {5,3,2,1,4}
 * Result: 7
 * Explanation: There are 7 pairs (5,1), (5,3), (5,2), (5,4),(3,2), (3,1), (2,1) and we have left 2 pairs (2,4) and (1,4) as both are not satisfy our condition.
 *
 * Brute force - Nested loop with outer loop as i = 0 ... n and inner loop as j = i + 1 .... n and then if we get a[i] > a[j] we increment count.
 * TC - o(n^2) - approximately
 * SC - O(1)
 *
 * Optimal solution - This is an implementation of merge sort.
 * We can count for inversions at the time of merge operation.
 */
public class Count_Inversions {
    private static int[] mergeSort(int lo, int hi, int[] input, int[] inversionCnt) {
        if(lo == hi) {
            return new int[]{input[lo]};
        }
        int mid =(hi + lo) / 2;
        int[] leftSorted = mergeSort(lo, mid, input, inversionCnt);
        int[] rightSorted = mergeSort(mid+1, hi, input, inversionCnt);
        int[] result = mergeParts(leftSorted, rightSorted, inversionCnt);
        return result;
    }

    private static int[] mergeParts(int[] left, int[] right, int[] inversionCnt) {
        int i =0, j =0, k= 0;
        int leftLength = left.length, rightLength = right.length;
        int[] result = new int[leftLength + rightLength];
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                // for i < j we have input[i] > input[j] and hence we have found an inversion. Since entire left array is sorted everything form i onwards is
                //sorted and hence we simply increment the inversions by left.length - i
                inversionCnt[0] += (leftLength - i);
                result[k++] = right[j++];
            }
        }
        while(i < leftLength) {
            result[k++] = left[i++];
        }
        while(j < rightLength){
            result[k++] = right[j++];
        }
        return result;
    }

    public static void main(String[] args){
        int[] input = new int[]{8, 5, 3, 4, 1, 6, 2};
        int[] inversionCnt = new int[1];
        int[] sortedArray = mergeSort(0, input.length - 1, input, inversionCnt);
        System.out.println("Total number of onversions in the array => "+inversionCnt[0]);
        for(int i: sortedArray){
            System.out.print(i+" ");
        }
        System.out.println();

    }
}
