package com.ds.ga.medium_problems;

/**
 * If the array has n elements there are n! factorial possible ways of rearrangements possible for all the elements.
 * For e.g. if the array has elements [1, 2, 3] the possible arrangements are as follows
 * 1, 2, 3 | 1, 3, 2 | 2, 1, 3 | 2, 3, 1 | 3, 1, 2 | 3, 2, 1
 * Hence the arrangement after 1, 3, 2 is 2, 1, 3. What we see here is the numbers are in the dictionary order i.e. the combinations of numbers are such that
 * the generated numbers are arranged from lowest to highest.
 *
 * Brute Force solution - Generate all possible permutations and check for the current combination to check for the required configuration. TC - O(n! * n)
 * n! to generarte the permutations and n is the length of elements in the array.
 *
 * Optimal solution - The generic way of looking at this problem is basically trying to take a prefix of the numbers and trying to find the prefix match such that
 * we keep certain digits constant from front anf for the remaining numbers we try to find the number just greater than the current combination. The steps for doing
 * this can be given as follows
 *
 * Step 1] Find the break point in the array such that a[i] < a[i+1].
 * Step 2] Find the number just greater than the number at the break point and swap the numbers the one at break point and the number just greater than that.
 * Step 3] Now sort the remaining numbers from breaking point till index n-1 to get the next permutation. However the breaking point was obtained such that all
 *         the numbers after breaking point were in increasing order. Hence we do not need to actually sort the numbers but just reverse the numbers to get the
 *         sorted order.
 *
 * This problem can come up in different variants. It can be asked as find next greater number as well. The logic for solving this kind of problem remains the same.
 */
public class FindNextPermutation {
    private static void findNextPermutation(int[] input) {
        int n = input.length, nextGreater = Integer.MAX_VALUE, nextGreaterIndex = -1;
        // Step 1 - Find break index.
        int breakIndex = findBreakingPoint(input);
        if(-1 == breakIndex) {
            reverseInPlace(input, 0, n-1);
        }
        //Step 2 - Now find the number from break index onwards which is just greater than the number at break index.
        for(int i =n-1; i>= breakIndex; i--){
            if(input[i] > input[breakIndex] && input[i] < nextGreater){
                nextGreaterIndex = i;
            }
        }
        if(nextGreaterIndex != -1) {
            swapElements(input, nextGreaterIndex, breakIndex);
        }
        // Step 3 - After swapping break index and next greater index elements now reverse the array from breakIndex -1 to complete the last step.
        reverseInPlace(input, breakIndex + 1, n-1);
    }
    private static void reverseInPlace(int[] input, int startIndex, int endIndex) {
        while(startIndex < endIndex) {
            swapElements(input, startIndex, endIndex);
            ++startIndex;
            --endIndex;
        }
    }
    private static void swapElements(int[] input, int left, int right) {
        int temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }
    private static int findBreakingPoint(int[] input) {
        for(int i=0; i<input.length - 1; i++) {
            if(input[i] < input[i+1]) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] input = new int[]{2, 1, 5, 4, 3, 0, 0};
        findNextPermutation(input);
        System.out.println("DONE");
    }
}
