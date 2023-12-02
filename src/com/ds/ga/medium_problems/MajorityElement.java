package com.ds.ga.medium_problems;

/**
 * Better approach - Use a HashMap to store frequency of all elements and then scan the hashMap in one more iteration to check for frequency to be  > n/2.
 * Optimal approach -
 */
public class MajorityElement {
    /**
     * Use Moore's voting algorithm. It is a simple algorithm where every element votes for itself meaning the element will count itself during iteration.
     * When we visit an element if the count is 0 we increment the count and store the element. Next time if we see same element we increment the count and
     * if we see different element we reduce the count thus is count is 0 then we store the element before incrementing the count. Thus the element that remains
     * gives us the majority element.
     *
     * @param input - Array of numbers
     * @return - integer representing majority element within input.
     */
    private static int findMajorityElementOptimalApproach(int[] input) {
        int frequency = 1;
        Integer element  = input[0];
        for(int i=1; i<input.length; i++) {
            if (0 == frequency) {
                element = input[i];
            }
            if(element == input[i]) {
                ++frequency;
            } else {
                --frequency;
            }
        }
        return element;
    }
    public static void main(String[] args) {
        int[] input = new int[]{7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 5, 5, 5, 5};
        System.out.println("Majority element => "+findMajorityElementOptimalApproach(input));
    }
}
