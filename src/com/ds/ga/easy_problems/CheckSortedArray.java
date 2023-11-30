package com.ds.ga.easy_problems;

public class CheckSortedArray {
    private static boolean isSortedArray(int[] arr) {
        if(arr.length == 1) {
            return true;
        }
        for(int i=1; i<arr.length; i++) {
            if(arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println("Is given array sorted => "+isSortedArray(arr));
        arr = new int[]{7, 1, 2, 3, 4, 5};
        System.out.println("Is given array sorted => "+isSortedArray(arr));
        arr = new int[]{7};
        System.out.println("Is given array sorted => "+isSortedArray(arr));
        arr = new int[]{7, 7, 7, 7};
        System.out.println("Is given array sorted => "+isSortedArray(arr));
    }
}
