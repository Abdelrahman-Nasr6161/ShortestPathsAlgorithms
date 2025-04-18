package demo.Classes;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BellmanFord implements Algorithm {

    public boolean run(Graph graph, int source) {
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
                graph.finalDist = Arrays.stream(dist).boxed().collect(Collectors.toList());
                return false;
            }
        }

        graph.finalDist = Arrays.stream(dist).boxed().collect(Collectors.toList());
        return true;
    }

    
}
