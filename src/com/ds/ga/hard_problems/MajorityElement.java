package com.ds.ga.hard_problems;

/**
 * Variation of Majority Element problem - Here the majority element is the element that appears > N/3 times in an array of N elements. There can only be maximum
 * 2 such elements in the given array.
 */
public class MajorityElement {
    private static int[] findMajorityElements(int[] input) {
        int[] result = new int[]{-1 , - 1};
        int count1 = 0, count2 = 0, element1 = -1, element2 = -1;
        for(int i=0; i<input.length; i++) {
            int currentElement = input[i];
            if(count1 == 0 && element2 != currentElement) {
                ++count1;
                element1 = currentElement;
            } else if(count2 == 0 && element1 != currentElement) {
                ++count2;
                element2 = currentElement;
            } else {
                --count1;
                --count2;
            }
        }
        int currentCount1 = 0, currentCount2 = 0, majoritySize = (int) Math.floor(input.length/3);
        for(int element: input) {
            if(element == element1) {
                ++currentCount1;
            } else if(element == element2) {
                ++currentCount2;
            }
        }
        if(currentCount1 > majoritySize ) {
            result[0] = element1;
        }
        if(currentCount2 > majoritySize ) {
            result[0] = element2;
        }

        return result;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1,2,2,3,2};
        int[] result = findMajorityElements(input);
        System.out.println("DONE");
    }

}
