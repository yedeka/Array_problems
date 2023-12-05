package com.ds.ga.medium_problems;

import java.util.ArrayList;

/**
 * This is done via 4 loops.
 * Loop 1 - Print left to right.
 * Loop 2 - print top to bottom.
 * Loop 3 - print right to left.
 * Loop 4 - Print bottom to Top.
 */
public class PrintMatrixSpirally {
    private static ArrayList<Integer> printSpiralMetrics(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int left = 0, top = 0, right = matrix[0].length-1, bottom = matrix.length-1;
        while(left <= right && top <= bottom) {
            //Step 1 - Print the top row from left to right, done by keeping top constant and moving from left to right i.e. [top][left-> right]
            for(int i=left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // top is decremented to start for the next number going down
            // Here we now go from top to down so the elements printed are [top->bottom][right]
            top++;
            for(int j=top; j<=bottom; j++) {
                result.add(matrix[j][right]);
            }
            right --;
            // Now we need to go from right -> left by keeping bottom constant i.e. [bottom][right->left]
            if(top <= bottom) {
                for(int k=right; k>= left; k--){
                    result.add(matrix[bottom][k]);
                }
            }
            bottom --;
            // now we go from bottom to top keeping left constant i.e. [bottom->top][left]
            if(left <= right) {
                for(int l = bottom; l>= top; l--){
                    result.add(matrix[l][left]);
                }
            }
            left++;
        }
        return result;
    }
    public static void main(String[] args){
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {20, 21, 22, 23, 24, 7},
                {19, 32, 33, 34, 25, 8},
                {18, 31, 36, 35, 26, 9},
                {17, 30, 29, 28, 27, 10},
                {16, 15, 14, 13, 12, 11}
        };
        System.out.println("Spiral printing of matrix => "+printSpiralMetrics(matrix));
    }
}
