package com.ds.ga.easy_problems;

public class MoveNonZeroToFront {
    private static void moveNZToFront(int[] input) {
        int zIndex = -1;
        for(int i=0 ;i<input.length; i++) {
            if(input[i] == 0) {
                zIndex = i;
                break;
            }
        }
        if (zIndex == -1) {
            return;
        }
        for(int i=zIndex; i<input.length; i++) {
            if(input[i] != 0) {
                swapElements(input, i, zIndex);
                // We increment zIndex because this is determined at the first non-zero element and since we start the loop 1 index after the first NZ element
                // if the next element in NZ then we swap and anyways the zero index should be the next index and the original 0 has moved and if we have subsequent
                // zeros then zero index is obviously going to be the next index. So in any case we end up incrementing the zero index.
                ++zIndex;
            }
        }
    }

    private static void swapElements(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args){
        int[] input = new int[]{1, 0, 2, 3, 2, 0 , 0, 4, 5, 1};
        moveNZToFront(input);
        System.out.println("DONE");
    }
}
