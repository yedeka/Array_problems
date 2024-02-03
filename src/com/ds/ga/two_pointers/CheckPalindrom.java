package com.ds.ga.two_pointers;

public class CheckPalindrom {
    public static boolean isPalindrome(String s) {
        if(null == s || s.length() == 0) return false;
        int left = 0, right = s.length() - 1;
        while(left <= right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if(leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args){
        String strTest = "kayak";
        System.out.println("Is given string palindrome => "+isPalindrome(strTest));
    }
}
