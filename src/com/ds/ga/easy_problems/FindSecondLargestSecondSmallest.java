package com.ds.ga.easy_problems;

/**
 * Problem description - Find the second largest and second smallest element in the array without sorting.
 */
public class FindSecondLargestSecondSmallest {
    private static int[] findSecondLargestSmallest(int[] input) {
        int[] result = new int[2];
        int min, secondMin = Integer.MIN_VALUE;
        result[0] = findSecondSmallest(input);
        result[1] = findSecondLargest(input);
        return result;
    }

    private static int findSecondSmallest(int[] arr) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for(int i = 0; i<arr.length; i++){
            if(min > arr[i]) {
                secondMin = min;
                min = arr[i];
            } else if(arr[i] < secondMin && arr[i] > min) {
                // Adding additional condition to handle duplicate max elements.
                secondMin = arr[i];
            }
        }
        return secondMin;
    }

    private static int findSecondLargest(int[] arr) {
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;
        for(int i = 0; i<arr.length; i++){
            if(max < arr[i]) {
                secondMax = max;
                max = arr[i];
            } else if(arr[i] > secondMax && arr[i] < max) {
                // Adding additional condition to handle duplicate max elements.
                secondMax = arr[i];
            }
        }
        return secondMax;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 7, 7, 5};
        int[] result = findSecondLargestSmallest(arr);
        System.out.println("Second smallest and second largest elements are => " +result[0]+", "+result[1]);
    }
}
