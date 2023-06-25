//travelling salesman problem using dynamic programming 

import java.util.*;

public class Tspdynamic {

    static int N = 4; // number of cities
    static int[][] distance = { 
            { 0, 10, 15, 20 }, // cost matrix
            { 5, 0, 9, 10 },
            { 6, 13, 0, 12 },
            { 8, 8, 9, 0 } };
    static boolean[] visited = new boolean[N]; // to keep track of visited cities

    public static void main(String[] args) {
        int start = 0; // starting city
        Arrays.fill(visited, false); // mark all cities as unvisited
        visited[start] = true; // mark starting city as visited
        int[][] dp = new int[N][(int) Math.pow(2, N)]; // memoization table

        for (int[] row : dp) { // fill memoization table with -1
            Arrays.fill(row, -1);
        }

        int ans = tsp(start, 1, dp); // call TSP function with initial visited set

        System.out.println("The shortest possible route is " + ans);
    }

    // recursive TSP function with memoization
    public static int tsp(int city, int visitedSet, int[][] dp) {
        if (visitedSet == (int) Math.pow(2, N) - 1) { // if all cities have been visited
            return distance[city][0]; // return distance to starting city
        }

        if (dp[city][visitedSet] != -1) { // if solution already computed
            return dp[city][visitedSet];
        }

        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (i == city || visited[i]) { // skip visited cities and current city
                continue;
            }

            visited[i] = true; // mark i as visited
            int newVisitedSet = visitedSet + (int) Math.pow(2, i); // add i to visited set
            int dist = distance[city][i] + tsp(i, newVisitedSet, dp); // compute distance
            minDist = Math.min(minDist, dist); // take minimum distance
            visited[i] = false; // unmark i as visited
        }

        dp[city][visitedSet] = minDist; // memoize solution
        return minDist;
    }
}