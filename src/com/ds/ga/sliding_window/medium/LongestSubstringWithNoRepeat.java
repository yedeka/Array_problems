package com.ds.ga.sliding_window.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* Problem Statement: Given a String, find the length of longest substring without any repeating character.
* Example 1:
* Input: s = ”abcabcbb”
* Output: 3
*Explanation: The answer is abc with length of 3.

* Example 2:
* Input: s = ”bbbbb”
* Output: 1
* Explanation: The answer is b with length of 1 units.
*
* Brute Force solution - Run a nested loop with outer loop marking start of substring
* and inner loop marking end of substring now while calculating the substring store all
* the visited characters into a set. If the character is found in the set then we have
* a repeating character and find the substring length, else stroe the visited character
* in set and proceed.
* TC - O(N^2) nester for loop
* SC - O(N) space required for set.
*
* Better approach - Use a set to keep track of unique characters and 2 pointer approach
* with left and right both pointers starting from index 0. If we do not find the character
* pointed by right pointer in visited set then we move right pointer by calculating
* substring length as right - left + 1. When we encounter a right character in visited set
* we shrink the window by incrementing left pointer until we find all unique elements in
* the window. At this juncture we take maxLength = max(maxLength, currentLength)
* We proceed until right pointer exhausts string length and return maxLength as our answer.
* TC - O(2*n) in worst case scenario a single character will be visited twice once by
* right pointer and once by left pointer. Since we dont compare the entire string for each
* character we do not have TC as O(N^2) but instead we have O(2*N) which is of the order N.
* SC - O(N) additional space needed for set.
*
* Optimal approach - We can reduce the 2N complexity by storing the indices of characters as well. In this case we maintain a map of structure
* character -> index and perform the iteration.
* TC - O(N)
* SC - O(N)
*
*  */
public class LongestSubstringWithNoRepeat {
    private static boolean isValidInput(String input) {
        if(null == input || input.isEmpty()) {
            return false;
        }
        return true;
    }

    private static int findLongestSubstringLengthNoRepeat(String input) {
        if(!isValidInput(input)){
            return 0;
        }
        int left =0, right = 0, length = input.length(), maxLength = 0, currentLength = 0;
        Set<Character> visited = new HashSet<>();
        while(right < length) {
            char checkChar = input.charAt(right);
            if(visited.contains(checkChar)) {
                while(left < right && visited.contains(checkChar)){
                    visited.remove(input.charAt(left++));
                }
            }
            visited.add(checkChar);
            maxLength = Math.max(maxLength, right -left + 1);
            right++;
        }
        return maxLength;
    }

    private static int findLongestSubStringNoRepeatOptimal(String input) {
        if(!isValidInput(input)) {
            return -1;
        }
        int left = 0, maxLength = 0, length = input.length();
        Map<Character, Integer> charMap = new HashMap<>();
        for(int right=0; right < length; right++) {
            char currentChar = input.charAt(right);
            if(charMap.containsKey(currentChar)) {
                // Ignore the element index if it's out of range i.e. if it's already smaller than the value of left.
                // This indicates that the current substring does not consider the duplicate
                left = Math.max(charMap.get(currentChar)+1, left);
            }
            charMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right-left+1);
        }
        return  maxLength;
    }

    public static void main(String[] args){
        String input = "abcaabcdba";
        System.out.println("Length of longest substring without repeating character => "+findLongestSubstringLengthNoRepeat(input));
        System.out.println("Length of longest substring without repeating character using optimal approach => "+findLongestSubStringNoRepeatOptimal(input));
    }
}
