package demo.Classes;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static class Edge {
        int src, dest, weight;
        
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public int V; // number of vertices
    public List<Edge> edges;
    public List<Integer> finalDist;
    public List<List<Integer>> finalDistMatrix;
    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }
}
