package com.ds.ga.easy_problems;

import java.util.ArrayList;

public class UnionOfSortedArrays {
    private static int[] findUnionOfSortedArrays(int[] input1, int[] input2) {
        ArrayList<Integer> result = new ArrayList<>();
        int size1 = input1.length, size2 = input2.length;
        int i = 0, j = 0;
        while(i < size1 && j < size2) {
            if(input1[i] <= input2[j]) {
                if(result.size() == 0 ||
                        input1[i] != result.get(result.size()-1)) {
                    result.add(input1[i]);
                }
                i++;
            } else {
                if(result.size() == 0 ||
                        input2[j] != result.get(result.size()-1)) {
                    result.add(input2[j]);
                }
                j++;
            }
        }
        while(i < size1) {
            if(input1[i] != result.get(result.size() -1)) {
                result.add(input1[i]);
            }
            i++;
        }
        while(j < size2) {
            if(input2[j] != result.get(result.size() -1)) {
                result.add(input2[j]);
            }
            j++;
        }
        return result.stream().mapToInt(z -> z).toArray();
    }
    public static void main(String[] args) {
        int[] arr1 = new int[] {1,2,3,4,5};
        int[] arr2 =  new int[]{2,3,4,4,5};
        int[] result = findUnionOfSortedArrays(arr1, arr2);
        System.out.println("DONE");
    }
}
