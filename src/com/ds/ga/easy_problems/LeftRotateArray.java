package com.ds.ga.easy_problems;

public class LeftRotateArray {
    private static void leftRotateArray(int[] input) {
        int firstElement = input[0];
        for(int i=1; i<input.length; i++) {
            input[i-1] = input[i];
        }
        input[input.length - 1] = firstElement;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7};
        leftRotateArray(input);
        System.out.println("DONE");
    }
}
