package com.ds.ga.sliding_window.medium;

/**
 * You are given a string s and an integer k. You can choose any character of the string
 * and change it to any other uppercase English character. You can perform this operation at most
 * k times.Return the length of the longest substring containing the same letter you can get
 * after performing the above operations.
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 * Brute Force - Find out all the possible substrings and check for each substring if it's
 * a valid substring or not by finding the index of maximum occured character, subtracting
 * it from the length of the sub-string and checking whether the subtraction is <= k.
 * TC - O(N^2)
 * SC - O(1) Since an array of constant size is used to store the count of characters per
 * substring.
 *
 *Better solution - A better solution that the brute force approach includes use of binary search
 * and a sliding window. in this case we take lo as substring of length 1 which we know will
 * always be true and replceable and hi = length of string. We try various lengths to ensure
 * that the substring of that length can be made. Since we have to find the longest such string
 * upon finding a possible match we increment lo = mid + 1 and when we dont find we do hi = mid -1
 * TC - nlogN
 * Sc - O(1)
 *
 * Optimal solution - Using sliding window we can easily achieve a better solution. We keep checking for a valid string over a window. If no valid string
 * is found we shrink the window from start. If a valid string is found we simply extend the window to check for valid string of maximum length.
 * TC - O(N)
 * SC - O(1)
 * One key thing to note here is the fact the we do not reduce maximum frequency at all at any window. Why ? Because the maximum frequency seen so far
 * gives us the maximum frequency for last valid window and hence we do not reduce from that window size.
 * This can be done in 2 ways the one we told above and the acquire/release pattern which becomes more readable
 */
public class LongestRepeatingCharacterReplacement {
    private static boolean isInputValid(String input) {
        if(null == input || input.length() == 0){
            return false;
        }
        return true;
    }

    private static boolean isSubstringLengthValid(String input, int length, int k){
        int begin = 0, maxFrequency = 0;
        int[] frequencyMap = new int[26];
        for(int end=0; end<input.length(); end++) {
            frequencyMap[input.charAt(end)-'A'] += 1;
            if(end-begin+1 > length){
                frequencyMap[input.charAt(begin)-'A'] -= 1;
                begin++;
            }
            maxFrequency = Math.max(maxFrequency, frequencyMap[input.charAt(end)-'A']);
            if(length - maxFrequency <= k){
                return true;
            }
        }
        return false;
    }
    private static int longestRepeatingCharacterSubstringLengthBetter(String input, int k){
        if(!isInputValid(input)){
            return 0;
        }
        int left = -1, right = 0, length = input.length(), maxFrequency = 0, maxLength = 0;
        int[] freqMap = new int[26];
        while(right < length){
            char currentChar = input.charAt(right);
            freqMap[currentChar - 'A'] += 1;
            // Acquire
            right++;
            // Validity Check
            maxFrequency = Math.max(maxFrequency, freqMap[currentChar - 'A'] );
            int currentLength = right - left - 1;
            // release
            if(currentLength - maxFrequency > k) {
                left++;
                freqMap[input.charAt(left) - 'A'] -= 1;
            }
            maxLength= Math.max(maxLength, right-left-1);
        }
        return maxLength;
    }

    private static int longestRepeatingCharacterSubstringlength(String input, int k) {
        if(!isInputValid(input)){
            return 0;
        }
        int lo = 0, hi = input.length();
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(isSubstringLengthValid(input, mid, k)){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return hi;
    }
    public static void main(String[] args){
        String input = new String("AABABBA");
        int k = 1;
        System.out.println("Longest repeating Substring with character replacement count of "+k+" => "+longestRepeatingCharacterSubstringlength(input, k));
        System.out.println("Longest repeating Substring with character replacement count of "+k+" using sliding window only => "+longestRepeatingCharacterSubstringLengthBetter(input, k));

    }
}
