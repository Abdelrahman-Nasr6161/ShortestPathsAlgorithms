package demo.Classes;

public class Algorithms {
    public static int[][] adjMatrix;

    public static void setAdjMatrix(int[][] adjMatrix) {
        Algorithms.adjMatrix = adjMatrix;
    }

    public static boolean BellmanFord(int source, int[] cost, int[] parent) {
        int n = adjMatrix.length;
        
        // Initialize cost and parent arrays
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        cost[source] = 0;

        // Relax edges n - 1 times
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (adjMatrix[u][v] != 0) {
                        if (cost[u] != Integer.MAX_VALUE && cost[u] + adjMatrix[u][v] < cost[v]) {
                            cost[v] = cost[u] + adjMatrix[u][v];
                            parent[v] = u;
                        }
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (adjMatrix[u][v] != 0) {
                    if (cost[u] != Integer.MAX_VALUE && cost[u] + adjMatrix[u][v] < cost[v]) {
                        return false; // Negative cycle detected
                    }
                }
            }
        }

        return true; // No negative cycles
    }
    public static void Dijkstra(int source , int[] cost , int[] parent){
        throw new UnsupportedOperationException("Unimplemented method 'Dijkstra'");
    }
    public static boolean FloydWarshall(int[][] allPairsCost, int[][] allPairsParent){
        int V = adjMatrix.length;
        int INF = Integer.MAX_VALUE;

        // Initialize all pairs as INF at first
        for (int i=0; i<V; i++){
            for (int j=0; j<V; j++){
                allPairsCost[i][j] = INF;
            }

            // Initialize min distance of a vertix to itself to 0
            allPairsCost[i][i] = 0;
        }


        // Initialize min distance between each 2 neighbor edges to be their weight
        for (int u=0; u<V; u++){
            for (int v=0; v<V; v++){
                allPairsCost[u][v] = adjMatrix[u][v];
            }
        }

        // Initialize all pairs paths
        for (int u=0; u<V; u++){
            for (int v=0; v<V; v++){
                if (u == v || adjMatrix[u][v] == INF){
                    allPairsParent[u][v] = -1;
                }
                else{
                    allPairsParent[u][v] = u; //u -> v
                }
            }
        }
        

        // Main algorithm
        for (int k=0; k<V; k++){
            for (int i=0; i<V; i++){
                for (int j=0; j<V; j++){
                    if (allPairsCost[i][k] != INF && allPairsCost[k][j] != INF && allPairsCost[i][j] > allPairsCost[i][k] + allPairsCost[k][j]){
                        allPairsCost[i][j] = allPairsCost[i][k] + allPairsCost[k][j];
                        allPairsParent[i][j] = allPairsParent[k][j]; 
                    }
                }
            }
        }

        // Check for negative cycles
        for (int i=0; i<V; i++){
            if (allPairsCost[i][i] < 0){
                return false;
            }
        }

        return true;
    }
}
