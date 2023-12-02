package com.ds.ga.easy_problems;

public class FindMissingNumber {
    /**
     *
     * @param n - Count of numbers required
     * @param input - Input array of numbers
     * @return
     *
     * XOR of every number with itself is zero. Now if we take XOR of all n numbers with the numbers in the array since all remaining numbers with cancel each other
     * out only the missing number will be returned as the result of operation. So we find XOR of first numbers and then we find XOR of array elements. We then
     * take an XOR of these two terms to find out the missing number.
     */
    private static int findMissingNumberByXor(int n, int[] input) {
        int expectedXorResult = 0, actualXorResult = 0;
        for(int i= 0; i<n-1; i++) {
            actualXorResult ^= input[i];
            expectedXorResult ^=  i+1 ;
        }
        return expectedXorResult ^ actualXorResult ^ n;

    }

    private static int findMissingNumberBySum(int n, int[] input) {
        int expectedSum = (n * (n+1))/2, sum = 0;
        for(int i=0; i<input.length; i++) {
            sum += input[i];
        }
        return expectedSum - sum;
    }
    public static void main(String[] args){
        int n = 5;
        int[] input = new int[]{1, 2, 3, 5};
        System.out.println("Missing number found by summation => "+findMissingNumberBySum(n, input));
        System.out.println("Missing number found by XOR => "+findMissingNumberByXor(n, input));
    }
}
