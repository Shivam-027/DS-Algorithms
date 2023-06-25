//knapsack using greedy approach 

import java.util.*;

public class Knapsackgreedy {
    private static double fractionalKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        double[] ratios = new double[n];

        // Calculate value-to-weight ratios for each item
        for (int i = 0; i < n; i++) {
            ratios[i] = (double)values[i] / weights[i];
        }

        // Sort items in decreasing order of value-to-weight ratio
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ratios[i] < ratios[j]) {
                    // Swap items
                    double tempRatio = ratios[i];
                    ratios[i] = ratios[j];
                    ratios[j] = tempRatio;

                    int tempWeight = weights[i];
                    weights[i] = weights[j];
                    weights[j] = tempWeight;

                    int tempValue = values[i];
                    values[i] = values[j];
                    values[j] = tempValue;
                }
            }
        }

        // Calculate the total value of items that can fit in the knapsack
        double totalValue = 0;
        for (int i = 0; i < n && capacity > 0; i++) {
            if (weights[i] <= capacity) {
                totalValue += values[i];
                capacity -= weights[i];
            } else {
                double fraction = (double)capacity / weights[i];
                totalValue += fraction * values[i];
                capacity = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the Number of Objects: ");
        int n = obj.nextInt();
        System.out.println("Enter the weights: ");
        int[] weights = new int[n];
        for(int i = 0; i < n; i++){
            weights[i] = obj.nextInt();
        }
        System.out.println("Enter the Values: ");
        int[] values = new int[n];
        for(int i = 0; i < n; i++){
            values[i] = obj.nextInt();
        }
        System.out.println("Enter the Capacity: ");
        int capacity = obj.nextInt();

        double maxValue = fractionalKnapsack(weights, values, capacity);
        System.out.println("Maximum value that can be obtained = " + maxValue);
    }
}

//45 40 35 30 25 20 15 10
//70 40 60 80 100 90 120 50