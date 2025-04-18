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
    public static void FloydWarshall(int[][] allPairsCost, int[][] allPairsParent){
        throw new UnsupportedOperationException("Unimplemented method 'FloydWarshall'");
    }
}
