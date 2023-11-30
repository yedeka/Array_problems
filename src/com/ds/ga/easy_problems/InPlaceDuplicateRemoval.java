package com.ds.ga.easy_problems;

/**
 * The key idea here is to understand the fact that first element is always at its right position so start the first pointer there and then we start checking from
 * element at index 1. If we found a non duplicate element, the place of that element is at index 1 so we put the element at it's correct position by incrementing
 * the pointer at first element and this is how we proceed.
 */
public class InPlaceDuplicateRemoval {
    private static int removeDuplicates(int[] input){
        int uniqueSize = 0;
        for(int i=1; i<input.length; i++) {
            if(input[i] != input[uniqueSize]) {
                input[uniqueSize + 1] = input[i];
                ++uniqueSize;
            }
        }
        return uniqueSize+1;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        System.out.println("number of unique elements in the array => "+removeDuplicates(input));
    }
}
