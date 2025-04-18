package demo.Classes;

public class AlgorithmFactory  {
    public static Algorithm getAlgorithm(String algorithmName) {
        if (algorithmName.equals("BellmanFord")) {
            return new BellmanFord();
        }
        return null;
    }   
}
