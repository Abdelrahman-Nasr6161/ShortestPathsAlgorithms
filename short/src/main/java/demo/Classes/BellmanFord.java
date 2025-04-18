package demo.Classes;

import java.util.Arrays;

public class BellmanFord implements Algorithm {

    public static void bellmanFord(Graph graph, int source) {
        int V = graph.V;
        int E = graph.edges.size();
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (Graph.Edge edge : graph.edges) {
                int u = edge.src;
                int v = edge.dest;
                int weight = edge.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (Graph.Edge edge : graph.edges) {
            int u = edge.src;
            int v = edge.dest;
            int weight = edge.weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }

        printDistances(dist, source);
    }

    private static void printDistances(int[] dist, int source) {
        System.out.println("Vertex Distance from Source " + (source + 1));
        for (int i = 0; i < dist.length; ++i) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println((i + 1) + "\t\t" + "INF");
            else
                System.out.println((i + 1) + "\t\t" + dist[i]);
        }
    }
}
