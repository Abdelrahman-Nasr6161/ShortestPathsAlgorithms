package demo.Classes;

public class AlgorithmFactory  {
    public static Algorithm getAlgorithm(String algorithmName) {
        if (algorithmName.equals("BellmanFord")) {
            return new BellmanFord();
        }
        else if (algorithmName.equals("FloydWarshall")) {
            return new FloydWarshall();
        }
        return null;
    }   
}
