package com.ds.ga.hard_problems;

import java.util.Arrays;

/**
 * Problem statement: Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order.
 * Merge them in sorted order. Modify arr1 so that it contains the first N elements and modify arr2 so that it contains the last M elements.
 *
 * Example 1:
 *
 * Input:
 * n = 4, arr1[] = [1 4 8 10]
 * m = 5, arr2[] = [2 3 9]
 *
 * Output:
 * arr1[] = [1 2 3 4]
 * arr2[] = [8 9 10]
 *
 * Brute Force - Just use a third array of size m + n to store the elements of both sorted arrays and then use the third array to populate elements.
 * TC - O(m+n)
 * SC - O(m+n)
 *
 * Better Solution - Use a 2 pointer approach with pointer 1 at the end of arr1 and pointer 2 at the beginning of arr2. if arr1[pointer1] >= arr2[pointer2] swap the elements.
 * Break out from the loop when arr1[pointer1] < arr2[pointer2] since because sorted nature of the arrays everything from this point onwards is in correct order.
 * As step 2 sort both arrays to ensure the swapped elements are at their right place.
 * TC - Max[O(n), O(m)] + max(O(nlogn), (mlogm))
 * SC - O(1)
 *
 * Second better solution - Uses gap method as used in shell sort. Use 2 pointer technique with a gap of math.ceil(m+n/2). keep on reducing the gap everytime by Math.ceil(gap/2) and keep swapping the elements at pointer
 * left and right if element at left > element at right.
 * TC - O(log(n+m)) * O(n+m) since everytime the gap is reduced by half.
 */
public class MergeTwoSortedArrays {
    private static int[] mergeSortedArraysBrute(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int[] result = new int[length1 + length2];
        int resultLength = length1 + length2;
        int i = 0, j=0, k=0;
        // Step 1 - Adding all the sorted elements into a third array
        while(i < length1 && j < length2) {
            if(arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while(i < length1) {
            result[k++] = arr1[i++];
        }
        while(j< length2) {
            result[k++] = arr1[j++];
        }
        //Step 2 - Iterate over the third array and distribute the elements in two arrays
        for(i=0; i< resultLength; i++) {
           if(i < length1) {
               arr1[i] = result[i];
           } else {
               arr2[i-length1] = result[i];
           }
        }
        return result;
    }

    private static void mergeSortedArraysBetter(int[] arr1, int[] arr2){
        int length1 = arr1.length, length2 = arr2.length, left = length1-1, right = 0;
        //Step 1 - Swap the out of place elements between arr1 and arr2
        while(left >=0 && right < length2 && arr1[left] > arr2[right]) {
                swapElementsIn2Arrays(arr1, left--, arr2, right++);
        }
        //Step 2 - Sort both the arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return;
    }

    private static void mergeSortArraysBetter2(int[] arr1, int[] arr2) {
        int length1 = arr1.length, length2 = arr2.length, length = length1 + length2;
        //Another way of ceil here is (length1 + length2 /2) + (length1+length2) % 2
        int gap = length / 2 + length % 2;
        while(gap > 0 ) {
            int left = 0;
            int right = gap + left;
            while(right < length) {
                if(left < length1 && right >= length1) { // left in first array and right in second array
                    int actualRight = right-length1;
                    if(arr1[left] > arr2[actualRight]){
                        swapElementsIn2Arrays(arr1, left, arr2, actualRight);
                    }
                } else if(left >= length1) { // Left and right both in the second array, since right is always left + gap meaning greater than left;
                    // if left is in the second array, right will automatically be in the second array.
                    int actualLeft = left-length1;
                    int actualRight = right - length1;
                    if(arr2[actualLeft] > arr2[actualRight]) {
                        swapElementsIn2Arrays(arr2, actualLeft, arr2, actualRight);
                    }
                } else if (right < length1) { // Both left and right are in arr1. Since right = left + gap if right is in arr1 it indicates that left is also in arr1
                    if(arr1[left] > arr1[right]) {
                        swapElementsIn2Arrays(arr1, left, arr1, right);
                    }
                }
                left++;
                right++;
            }
            if(gap == 1) {
                break;
            } else {
                gap = gap / 2 + gap % 2;
            }
        }
    }

    private static void swapElementsIn2Arrays(int[] arr1, int i, int[] arr2, int j) {
        int temp = arr1[i];
        arr1[i] = arr2[j];
        arr2[j] = temp;
    }

    private static String printArray(int[] input){
        String strElements = "[ ";
        for(int element: input) {
            strElements += element+", ";
        }
        String formattedString = strElements.substring(0, strElements.length()-2)+ " ]";
        return formattedString;
    }
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 4, 8, 10};
        int arr2[] = new int[]{2, 3, 9};
        int[] merged = mergeSortedArraysBrute(arr1, arr2);
        System.out.println("Merged Array => "+printArray(merged));
        System.out.println("Rearranged Arrays => "+printArray(arr1)+" AND "+printArray(arr2));
        System.out.println("DONE Brute Force Approach");
        arr1 = new int[]{1, 4, 8, 10};
        arr2 = new int[]{2, 3, 9};
        mergeSortedArraysBetter(arr1, arr2);
        System.out.println("Rearranged Arrays => "+printArray(arr1)+" AND "+printArray(arr2));
        System.out.println("Done with Better approach");
        arr1 = new int[]{1, 3, 5, 7};
        arr2 = new int[]{0, 2, 3, 6, 8, 9};
        mergeSortArraysBetter2(arr1, arr2);
        System.out.println("Rearranged Arrays => "+printArray(arr1)+" AND "+printArray(arr2));
        System.out.println("Done with second better approach");
    }
}
