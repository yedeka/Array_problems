package com.ds.ga.medium_problems;

/**
 * Brute Force - In the brute force solution we will take a new empty matrix and iterate over rows and columns of the original matrix to fill in the columns and rows
 * of the new matrix and return the new matrix.
 * Time complexity - O(n*m) , space O(n*m)
 *
 * Optimal approach - If we look at the original matrix and the desired matrix we can see that this transformation can be done in 2 steps
 * Step 1 - Transpose the original matrix
 * Step 2 - Reverse each row.
 */
public class RotateMatrixBy90Degrees {
    private static void rotateMatrix(int[][] matrix) {
        transposeMatrix(matrix);
        reverseRows(matrix);
    }
    private static void transposeMatrix(int[][] matrix) {
        int length = matrix.length;
        for(int i=0; i<length; i++){
            for(int j=i; j<length; j++) {
                swapElements(matrix, i, j, j, i);
            }
        }
    }
    private static void reverseRows(int[][] matrix) {
        int length = matrix.length;
        for(int i=0; i<length; i++) {
            for(int j=0; j<length/2; j++) {
                swapElements(matrix, i, j, i, length-1-j);
            }
        }
    }
    private static void swapElements(int[][] matrix, int sourceRow, int sourceColumn, int destinationRow, int destinationColumn){
        int temp = matrix[sourceRow][sourceColumn];
        matrix[sourceRow][sourceColumn] = matrix[destinationRow][destinationColumn];
        matrix[destinationRow][destinationColumn] = temp;
    }
    public static void main(String[] args){
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotateMatrix(matrix);
        System.out.println("DONE");
    }
}
