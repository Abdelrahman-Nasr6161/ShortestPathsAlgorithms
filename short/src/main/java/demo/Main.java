package demo;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import demo.Classes.Algorithms;

public class Main {
    static int[][] adjMatrix;
    static int V, E;

    static int[] singleSourceCost;
    static int[] singleSourceParent;
    static int[][] allPairsCost;
    static int[][] allPairsParent;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadGraph();

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Single source shortest path");
            System.out.println("2. All pairs shortest path");
            System.out.println("3. Detect negative cycle");
            System.out.println("4. Exit");
            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    singleSourceShortestPath(scanner);
                    break;
                case 2:
                    allPairsShortestPath(scanner);
                    break;
                case 3:
                    detectNegativeCycle(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void loadGraph() {
        try {
            File file = new File("graph.txt");
            Scanner fileScanner = new Scanner(file);

            V = fileScanner.nextInt();
            E = fileScanner.nextInt();
            adjMatrix = new int[V][V];

            for (int i = 0; i < E; i++) {
                int u = fileScanner.nextInt();
                int v = fileScanner.nextInt();
                int w = fileScanner.nextInt();
                adjMatrix[u][v] = w;
            }

            fileScanner.close();
            Algorithms.setAdjMatrix(adjMatrix);
        } catch (FileNotFoundException e) {
            System.out.println("Graph file not found.");
            System.exit(1);
        }
    }

    private static void singleSourceShortestPath(Scanner scanner) {
        System.out.println("Enter source node:");
        int source = scanner.nextInt();

        System.out.println("Choose algorithm:");
        System.out.println("1. Dijkstra");
        System.out.println("2. Bellman-Ford");
        System.out.println("3. Floyd-Warshall");
        int algoChoice = scanner.nextInt();

        singleSourceCost = new int[V];
        singleSourceParent = new int[V];

        boolean success = true;

        switch (algoChoice) {
            case 1:
                // Call Algorithms.Dijkstra(source, singleSourceCost, singleSourceParent);
                System.out.println("Dijkstra is not implemented yet.");
                success = false;
                break;
            case 2:
                success = Algorithms.BellmanFord(source, singleSourceCost, singleSourceParent);
                break;
            case 3:
                // Call Algorithms.FloydWarshallSingleSource(source, singleSourceCost, singleSourceParent);
                System.out.println("Floyd-Warshall (single source) not implemented yet.");
                success = false;
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        if (!success) {
            System.out.println("Negative cycle detected, cannot proceed.");
            return;
        }

        // Sub-menu for single source operations
        while (true) {
            System.out.println("\nSingle Source Sub-Menu:");
            System.out.println("1. Get cost to a node");
            System.out.println("2. Get path to a node");
            System.out.println("3. Exit to main menu");
            int subChoice = scanner.nextInt();

            if (subChoice == 3) break;

            System.out.println("Enter target node:");
            int target = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    if (singleSourceCost[target] == Integer.MAX_VALUE)
                        System.out.println("No path exists.");
                    else
                        System.out.println("Cost to node " + target + " = " + singleSourceCost[target]);
                    break;
                case 2:
                    printPath(source,target, singleSourceParent);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void allPairsShortestPath(Scanner scanner) {
        System.out.println("Choose algorithm:");
        System.out.println("1. Dijkstra");
        System.out.println("2. Bellman-Ford");
        System.out.println("3. Floyd-Warshall");
        int algoChoice = scanner.nextInt();
    
        allPairsCost = new int[V][V];
        allPairsParent = new int[V][V];
    
        switch (algoChoice) {
            case 1:
                // Dijkstra All Pairs
                for (int source = 0; source < V; source++) {
                    int[] cost = new int[V];
                    int[] parent = new int[V];
                    Algorithms.Dijkstra(source, cost, parent);
                    for (int dest = 0; dest < V; dest++) {
                        allPairsCost[source][dest] = cost[dest];
                        allPairsParent[source][dest] = parent[dest];
                    }
                }
                break;
            case 2:
                // Bellman-Ford All Pairs
                for (int source = 0; source < V; source++) {
                    int[] cost = new int[V];
                    int[] parent = new int[V];
                    boolean noNegativeCycle = Algorithms.BellmanFord(source, cost, parent);
                    if (!noNegativeCycle) {
                        System.out.println("Negative cycle detected. Cannot complete Bellman-Ford for all pairs.");
                        return; // Exit the method early
                    }
                    for (int dest = 0; dest < V; dest++) {
                        allPairsCost[source][dest] = cost[dest];
                        allPairsParent[source][dest] = parent[dest];
                    }
                }
                break;
            case 3:
                // Floyd-Warshall
                Algorithms.FloydWarshall(allPairsCost, allPairsParent);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        // Sub-menu for all pairs operations
        while (true) {
            System.out.println("\nAll Pairs Sub-Menu:");
            System.out.println("1. Get cost from node i to j");
            System.out.println("2. Get path from node i to j");
            System.out.println("3. Exit to main menu");
            int subChoice = scanner.nextInt();
    
            if (subChoice == 3) break;
    
            System.out.println("Enter source node:");
            int u = scanner.nextInt();
            System.out.println("Enter destination node:");
            int v = scanner.nextInt();
    
            switch (subChoice) {
                case 1:
                    if (allPairsCost[u][v] == Integer.MAX_VALUE)
                        System.out.println("No path exists.");
                    else
                        System.out.println("Cost from " + u + " to " + v + " = " + allPairsCost[u][v]);
                    break;
                case 2:
                    printPath(u, v, allPairsParent[u]);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void detectNegativeCycle(Scanner scanner) {
        System.out.println("Choose algorithm:");
        System.out.println("1. Bellman-Ford");
        System.out.println("2. Floyd-Warshall");
        int algoChoice = scanner.nextInt();

        boolean hasNegativeCycle = false;

        switch (algoChoice) {
            case 1:
                int[] cost = new int[V];
                int[] parent = new int[V];
                hasNegativeCycle = !Algorithms.BellmanFord(0, cost, parent);
                break;
            case 2:
                // Implement Floyd-Warshall with cycle check
                // System.out.println("Floyd-Warshall negative cycle check not implemented yet.");
                int[][] costPairs = new int[V][V];
                int[][] parentPairs = new int[V][V];
                hasNegativeCycle = !Algorithms.FloydWarshall(costPairs, parentPairs);
                
                
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        if (hasNegativeCycle)
            System.out.println("Negative weight cycle exists!");
        else
            System.out.println("No negative weight cycle detected.");
    }

    private static void printPath(int source, int target, int[] parent) {
        if (source == target) {
            System.out.print(source + " ");
            return;
        }
        if (parent[target] == -1) {
            System.out.print("No path exists.");
            return;
        }
        printPath(source, parent[target], parent);
        System.out.print(target + " ");
    }
}
