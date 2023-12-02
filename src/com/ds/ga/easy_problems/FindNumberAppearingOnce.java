package com.ds.ga.easy_problems;

public class FindNumberAppearingOnce {
    private static int findSingleApprearingNumber(int[] input){
        int answer = 0;
        for(int i=0; i<input.length; i++) {
            answer ^= input[i];
        }
        return answer;
    }
    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 2, 3, 3, 4, 4};
        System.out.println("Number appearing only once => "+findSingleApprearingNumber(input));
    }
}
