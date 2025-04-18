package demo;

import demo.Classes.Graph;
import demo.Classes.BellmanFord;
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        // Add the edges exactly from your graph
        graph.addEdge(0, 1, 3);   // 1 -> 2 (weight 3)
        graph.addEdge(0, 4, -4);  // 1 -> 5 (weight -4)
        graph.addEdge(1, 2, 4);   // 2 -> 3 (weight 4)
        graph.addEdge(2, 3, -5);  // 3 -> 4 (weight -5)
        graph.addEdge(3, 1, 1);   // 4 -> 2 (weight 1)
        graph.addEdge(4, 2, 8);   // 5 -> 3 (weight 8)
        graph.addEdge(4, 3, 7);   // 5 -> 4 (weight 7)

        BellmanFord.bellmanFord(graph, 0); // Start from node 1 (index 0)
    }
}