package demo.Classes;

public class Node implements Comparable<Node> {
    int vertex, cost;

    public Node(int vertex, int cost){
        this.vertex = vertex;
        this.cost = cost;
    }

    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}
