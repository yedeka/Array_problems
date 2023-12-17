package com.ds.ga.hard_problems;

/**
 * Find repeating and missing numbers -
 * Problem Statement: You are given a read-only array of N integers with values also in the range [1, N] both inclusive.
 * Each integer appears exactly once except A which appears twice and B which is missing.
 * The task is to find the repeating and missing numbers A and B where A repeats twice and B is missing.
 *
 * Example :
 * Input Format: array[] = {3,1,2,5,4,6,7,5}
 * Result: {5,8)
 * Explanation: A = 5 , B = 8
 * Since 5 is appearing twice and 8 is missing
 *
 * Brute Force approach - Nested loop with outer loop iterating over 1 ... n and inner loop will search for the number in outer loop and keep track of it's
 * frequency. we will populate the number appearing twice and the number with frequency 0.
 * TC - O(n^2)
 * SC - O(1) since we do not need any extra space.
 *
 * Better approach - Can be done in 2 ways.
 * Step 1 - Sort the array o(nlogn)
 * Step 2 - run a single for loop iterating over the array and checking whether arr[i] = i + 1, the index at which this condition is not met we return
 * arr[i] and i+1 as the required numbers O(n)
 * TC - O(nlogn) + O(n)
 * SC - O(1) no additional space is required.
 *
 * Optimal approach 1 - Done using mathematics
 * Sum of first n natural numbers is given as [n*(n+1)]/2. We will sum the elements of the given array. Assume X to be the repeating number and Y to be the
 * missing number.
 * When we subtract sum of array elements from the sum of first n natural numbers we get x - y
 * Now since we have 2 unknowns we need 2 equations to find out values of both the unknowns.
 * Second equation can be found out by subtracting square of elements of arrays with square of first n numbers. that value gives us X^2 - y^2
 * Now X^2 - Y^2 = (x+y)*(x-y) and we already have X - Y hence if we divide X^2 - Y^2 with X - Y we get X + Y.
 * Since we have 2 equations X - Y and X + Y we can solve these equations to find values of X and Y.
 *
 */
public class FindRepeatingMissingNumbers {
    private static boolean validateInput(int[] input) {
        if(null == input || input.length < 2) {
            return false;
        }
        return true;
    }
    private static int[] findRepeatingNMissingNumbers(int[] input) {
        int[] result = new int[2];
        if(!validateInput(input)) {
            return result;
        }
        int length = input.length;
        int sumN = (length * (length+1))/ 2, sumNSquared = (length * (length + 1) * (2*length + 1))/6;
        int arraySum = 0, arraySquaresum = 0;
        for(int i=0; i<length; i++) {
            arraySum += input[i];
            arraySquaresum += (input[i] * input[i]);
        }
        int sumDifference = arraySum - sumN;
        int sumSquareDifference = arraySquaresum - sumNSquared;
        int sumAddition = sumSquareDifference/sumDifference;
        int repeatingNumber = (sumDifference + sumAddition)/2;
        int missingNumber =  repeatingNumber - sumDifference;
        result[0] = repeatingNumber;
        result[1] = missingNumber;
        return result;
    }
    public static void main(String[] args) {
       int[] input = new int[] {3,1,2,5,4,6,7,5};
       int[] result = findRepeatingNMissingNumbers(input);
       System.out.println("Repeating and missing numbers in given input are ["+result[0]+", "+result[1]+"]");
    }
}
