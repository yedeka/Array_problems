package com.ds.ga.medium_problems;

public class StockBuyAndSell {
    private static int findMaxProfit(int[] input) {
        int maxProfit = 0, minPrice = Integer.MAX_VALUE, currentProfit = 0;
        for(int i=0; i<input.length; i++){
            minPrice = Math.min(minPrice, input[i]);
            currentProfit = input[i] - minPrice;
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        return maxProfit;
    }
    public static void main(String[] args){
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println("Maximum profit from given stock prices => "+findMaxProfit(prices));
    }
}
