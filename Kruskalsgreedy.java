//kruskals using greedy approach

import java.util.*;

public class Kruskalsgreedy {
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
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        Collections.sort(edges);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        System.out.println("\nKruskals");
        System.out.println("Edge \tcost");
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            int weight = edge.weight;
            int parentU = find(parent, u);
            int parentV = find(parent, v);
            if (parentU != parentV) {
                parent[parentU] = parentV;
                System.out.println(u + " - " + v + "\t" + weight);
            }
        }
    }

    public static int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(weight, other.weight);
        }
    }
}
