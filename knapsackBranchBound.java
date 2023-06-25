//0/1 knapsack using branch and bound

import java.util.*;
public class knapsackBranchBound {

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter Total number of element: ");
        int n = obj.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        System.out.println("Enter Values: ");
        for(int i = 0; i<n; i++){
            values[i] = obj.nextInt();
        }
        System.out.println("Enter Weights: ");
        for(int i = 0; i<n; i++){
            weights[i] = obj.nextInt();
        }
        System.out.println("Enter Capacity: ");
        int capacity = obj.nextInt();

        // Create a priority queue of nodes
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // Create the root node with 0/0 weight/value and no items included
        Node root = new Node(0, 0, 0);
        // Add the root node to the priority queue
        pq.add(root);
        // Initialize the best solution found so far
        int bestValue = 0;
        // Loop until the priority queue is empty
        while (!pq.isEmpty()) {
            // Get the node with the highest bound
            Node node = pq.poll();
            // If the node has a lower bound than the best value found so far, discard it
            if (node.bound < bestValue) {
                continue;
            }
            // If the node is a leaf node, update the best value found so far
            if (node.level == n) {
                bestValue = node.value;
                continue;
            }
            // Create the left child node, which includes the next item
            Node leftChild = new Node(node.weight + weights[node.level], node.value + values[node.level], node.level + 1);
            // Create the right child node, which excludes the next item
            Node rightChild = new Node(node.weight, node.value, node.level + 1);
            // Compute the bounds of the left and right child nodes
            leftChild.bound = computeBound(leftChild, capacity, values, weights, n);
            rightChild.bound = computeBound(rightChild, capacity, values, weights, n);
            // Add the left and right child nodes to the priority queue
            pq.add(leftChild);
            pq.add(rightChild);
        }
        System.out.println("Maximum value: " + bestValue);
    }

    private static double computeBound(Node node, int capacity, int[] values, int[] weights, int n) {
        if (node.weight > capacity) {
            return 0;
        }
        double bound = node.value;
        int j = node.level;
        int totalWeight = node.weight;
        while (j < n && totalWeight + weights[j] <= capacity) {
            bound += values[j];
            totalWeight += weights[j];
            j++;
        }
        if (j < n) {
            bound += (capacity - totalWeight) * ((double) values[j] / weights[j]);
        }
        return bound;
    }

    private static class Node implements Comparable<Node> {
        private int weight;
        private int value;
        private int level;
        private double bound;
        public Node(int weight, int value, int level) {
            this.weight = weight;
            this.value = value;
            this.level = level;
        }
        @Override
        public int compareTo(Node other) {
            return Double.compare(other.bound, this.bound);
        }
    }
}
