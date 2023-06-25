//knapsack using dynamic programming

import java.util.*;

public class Knapsackdynamic {
    
    public static int knapSack(int W, int wt[], int val[], int n) {
        
        int[][] Kn = new int[n+1][W+1];
        
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i==0 || w==0) {
                    Kn[i][w] = 0;
                } else if (wt[i-1] <= w) {
                    Kn[i][w] = Math.max(val[i-1] + Kn[i-1][w-wt[i-1]], Kn[i-1][w]);
                } else {
                    Kn[i][w] = Kn[i-1][w];
                }
            }
        }
        
        return Kn[n][W];
    }
    
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter total number of objects: ");
        int n = obj.nextInt();
        int val[] = new int[n];
        int wt[] = new int[n];
        System.out.println("Enter values in array: ");
        for (int i=0; i < n; i++){
            val[i] = obj.nextInt();
        }
        System.out.println("Enter weights in array: ");
        for (int i=0; i < n; i++){
            wt[i] = obj.nextInt();
        }
        System.out.println("Enter the capacity: ");
        int W = obj.nextInt();
        System.out.println("Max value: " + knapSack(W, wt, val, n));
    }
}
