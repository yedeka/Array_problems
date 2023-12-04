package com.ds.ga.medium_problems;

/**
 * Given a n * n matrix, if an element in the matrix is 0 set the value of the entire row and column containing that element to be 0.
 * For e.g.
 * Input: matrix=[[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Brute Force - The brute force algorithm works in 2 steps.
 * Step 1 - In first step mark all the rows and columns of the cell containing zero to be -1.
 * Step 2 - Mark all the cells containing -1 to be 0.
 * Time Complexity - O(n^2)
 *
 * Better Approach - The better approach reduces the complexity by marking the rows and columns eligible to be zero separately by using extra space.
 * Step 1 - Declare an array of size n to store the indices of rows that are expected to be 0 and an array of size m to store the indices of the columns
 * that are expected to be 0. Initialize both the arrays with 0.
 * Step 2 - Now run a nested loop to go over the matrix
 *
 * Optimal solution - Optimal solution builds on the better solution that keeping track of the zeroes in rows and columns is moved inside the matrix such that
 * oth rows of the matrix keeps track of the columns that are to be marked as zero and the 0th column of the matrix keeps track of the rows to be marked as zero
 * so that we can populate the oth column and 0th row in one pass and then use the second pass to mark corresponding rows and columns to be 0. So to avoid the
 * contention of cell [0, 0] we will keep a separate variable to store the status of column 0.
 * Tip - A special case that needs to be handled after marking is we do not solve for Row 0 and Column 0 in the beginning since it is the marked row and column
 * and thus we need to keep it for the last as handling that first may yield unwanted cells to be marked as zeros.
 *
 * This gives us a TC of O(2*n*m) without using extra space of O(n) + O(m).
 */
public class SetMetricsZeros {
    private static void setZeros(int[][] input) {
        // Step 1 - Mark zero eligible rows and columns using 0th row, 0th column and a [0, 0] variable in the matrix.
        int rows = input.length, cols =  input[0].length, col0 = 1;
        for(int i= 0; i<rows; i++){
            for(int j=0; j< cols; j++) {
                if(0 == input[i][j]) {
                    if(0 == j) {
                        col0 = 0;
                    } else {
                        input[0][j] = 0;
                    }
                    input[i][0] = 0;
                }
            }
        }
        // Step 2 - Now mark the remainder of the matrix as 0 based on 0th row and 0th column starting from reverse
        for(int i = rows - 1; i>0; i--) {
            for(int j= cols-1; j>0; j--){
                // Mark as 0 based of 0th row and 0th column
                if(input[i][0] == 0 || input [0][j] == 0) {
                    input[i][j] = 0;
                }
            }
        }
        // Step 3 - Now handle the marking row from reverse.
        if(input[0][0] == 0){
            // Handle the 0th row to be 0
            for(int j = 0; j<cols; j++){
                input[0][j] = 0;
            }
        }
        if(col0 == 0) {
            // Handle the 0th column to be 0
            for(int i=0; i<rows; i++){
                input[i][0] = 0;
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 1}
        };
        setZeros(matrix);
        System.out.println("DONE");
    }
}
