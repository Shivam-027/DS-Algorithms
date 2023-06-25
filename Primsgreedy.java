//Prims using greedy approach

import java.util.*;

public class Primsgreedy {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int[][] graph = new int[5][5];
        System.out.println("Enter the elements");
        for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            graph[i][j] = obj.nextInt();
            }
        }
        int n = 5;
        int[] parent = new int[n];
        int[] key = new int[n];
        boolean[] mst = new boolean[n];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mst, false);
        key[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < n - 1; i++) {
            int u = Minin(key, mst);
            mst[u] = true;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !mst[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        System.out.println("\nPrims");
        System.out.println("Edge \tcost");
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    public static int Minin(int[] key, boolean[] mst) {
        int minKey = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < key.length; i++) {
            if (!mst[i] && key[i] < minKey) {
                minKey = key[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
