package demo;

import demo.Classes.Graph;
import demo.Classes.Algorithm;
import demo.Classes.BellmanFord;
import demo.Classes.FileReader;
import demo.Classes.AlgorithmFactory;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = FileReader.readGraph("graph.txt");
            System.out.println("Please pick an algorithm: ");
            System.out.println("1. Dijkstra's algorithm");
            System.out.println("2. Bellman-Ford algorithm");
            System.out.println("3. Floyd-Warshall algorithm");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            String algorithmName;
            switch (choice) {
                case 1:
                    algorithmName = "Dijkstra";
                    break;
                case 2:
                    algorithmName = "BellmanFord";
                    break;
                case 3:
                    algorithmName = "FloydWarshall";
                    break;
                default:
                    System.out.println("Invalid choice");
                    scanner.close();
                    return;
            }
            int source = -1;
            if (choice!=3)
            {
                System.out.println("Please pick a source vertex: ");
                source = scanner.nextInt();
            }
            scanner.close();
            Algorithm algorithm = AlgorithmFactory.getAlgorithm(algorithmName);
            algorithm.run(graph, source);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }   
    }
}