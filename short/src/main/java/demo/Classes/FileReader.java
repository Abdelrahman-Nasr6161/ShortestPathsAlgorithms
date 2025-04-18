package demo.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static Graph readGraph(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        Graph graph = new Graph(V);
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.addEdge(u, v, w);
        }
        scanner.close();
        return graph;
    }
    
}
