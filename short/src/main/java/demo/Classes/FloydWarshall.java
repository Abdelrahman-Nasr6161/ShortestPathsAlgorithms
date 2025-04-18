package demo.Classes;

import java.util.Arrays;

public class FloydWarshall implements Algorithm{
    static int INF = Integer.MAX_VALUE;

    public void run(Graph graph, int source){
        int V = graph.V;
        int E = graph.edges.size();
        int[][] minD = new int[V][V];
        
        // Initialize all pairs as INF at first
        for (int i=0; i<V; i++){
            for (int j=0; j<V; j++){
                minD[i][j] = INF;
            }

            // Initialize min distance of a vertix to itself to 0
            minD[i][i] = 0;
        }


        // Initialize min distance between each 2 neighbor edges to be their weight
        for (Graph.Edge edge : graph.edges){
            int u = edge.src;
            int v = edge.dest;
            int weight = edge.weight;
            minD[u][v] = weight;
        }
        

        // Main algorithm
        for (int k=0; k<V; k++){
            for (int i=0; i<V; i++){
                for (int j=0; j<V; j++){
                    if (minD[i][k] != INF && minD[k][j] != INF && minD[i][j] > minD[i][k] + minD[k][j]){
                        minD[i][j] = minD[i][k] + minD[k][j];
                    }
                }
            }
        }

        printDistances(minD);

    }


    private void printDistances(int[][] minD){
        System.out.println("Min distance between each 2 vertices: ");
        for (int u=0; u<minD.length; u++){
            for (int v=0; v<minD.length; v++) {
                if (minD[u][v] == Integer.MAX_VALUE)
                    System.out.println((u + 1) + " -> " + (v + 1) + "\t\t" + "INF");
                else
                    System.out.println((u + 1) + " -> " + (v + 1) + "\t\t" + minD[u][v]);
            }
        }
        
    }
}
