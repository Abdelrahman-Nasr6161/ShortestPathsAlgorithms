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
    // static boolean negativeWeight = false;

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String graphPath = "short/graph.txt";
        String input;

        System.out.println("Enter the graph input file path (Default: short/graph.txt): \n");
        input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            graphPath = input;
        }

        loadGraph(graphPath);

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

    private static void loadGraph(String graphPath) {
        try {
            File file = new File(graphPath);
            Scanner fileScanner = new Scanner(file);

            V = fileScanner.nextInt();
            E = fileScanner.nextInt();
            adjMatrix = new int[V][V];

            // Initilaize adjMatrix with "INF", indicating no such edge exists
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    adjMatrix[i][j] = INF;
                }
                adjMatrix[i][i] = 0;
            }

            for (int i = 0; i < E; i++) {
                int u = fileScanner.nextInt();
                int v = fileScanner.nextInt();
                int w = fileScanner.nextInt();
                adjMatrix[u][v] = w;
                System.out.println(u + " " + v + " " + w);
                // if(w < 0) {
                // negativeWeight = true;
                // }
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
                Algorithms.Dijkstra(source, singleSourceCost, singleSourceParent);
                break;
            case 2:
                success = Algorithms.BellmanFord(source, singleSourceCost, singleSourceParent);
                break;
            case 3:
                allPairsCost = new int[V][V];
                allPairsParent = new int[V][V];
                success = Algorithms.FloydWarshall(allPairsCost, allPairsParent);
                for (int i = 0; i < V; i++) {
                    singleSourceCost[i] = allPairsCost[source][i];
                    singleSourceParent[i] = allPairsParent[source][i];
                }
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

            if (subChoice == 3)
                break;

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
                    printPath(source, target, singleSourceParent, allPairsCost);
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
                boolean noNegativeCycle = Algorithms.FloydWarshall(allPairsCost, allPairsParent);
                if (!noNegativeCycle) {
                    System.out.println("Negative cycle detected. Cannot complete Floyd-Warshall for all pairs.");
                    return; // Exit the method early
                }
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

            if (subChoice == 3)
                break;

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
                    printPath(u, v, allPairsParent[u], allPairsCost);
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
                // System.out.println("Floyd-Warshall negative cycle check not implemented
                // yet.");
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

    private static void printPath(int source, int target, int[] parent, int[][] costMatrix) {
        if (costMatrix[source][target] == Integer.MAX_VALUE) {
            System.out.println("No path exists.");
            return;
        }

        boolean[] visited = new boolean[parent.length];
        int current = target;
        while (current != -1) {
            if (visited[current]) {
                System.out.println("Cycle detected in the path.");
                return;
            }
            visited[current] = true;
            current = parent[current];
        }

        if (source == target) {
            System.out.print(source + " ");
            return;
        }
        if (parent[target] == -1) {
            System.out.println("No path exists.");
            return;
        }
        printPath(source, parent[target], parent, costMatrix);
        System.out.print(target + " ");
    }
}
