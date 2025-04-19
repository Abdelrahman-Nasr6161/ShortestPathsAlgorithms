import org.junit.Test;
import static org.junit.Assert.assertEquals;
import demo.Classes.Algorithms;
public class Tests {
    static int INF = Integer.MAX_VALUE;
    @Test
public void testBellmanFordSimple() {
    int INF = Integer.MAX_VALUE;
    int[][] adjMatrix = {
        {0, 5, INF, 10},
        {INF, 0, 3, INF},
        {INF, INF, 0, 1},
        {INF, INF, INF, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];
    Algorithms.setAdjMatrix(adjMatrix);
    boolean result = Algorithms.BellmanFord(0, cost, parent);

    assertEquals(true, result);
    assertEquals(0, cost[0]);
    assertEquals(5, cost[1]);
    assertEquals(8, cost[2]);
    assertEquals(9, cost[3]);
}
@Test
public void testBellmanFordNegativeWeights() {
    int INF = Integer.MAX_VALUE;
    int[][] adjMatrix = {
        {0, 4, 5, INF},
        {INF, 0, -2, 6},
        {INF, INF, 0, 3},
        {INF, INF, INF, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];
    Algorithms.setAdjMatrix(adjMatrix);
    boolean result = Algorithms.BellmanFord(0, cost, parent);

    assertEquals(true, result);
    assertEquals(0, cost[0]);
    assertEquals(4, cost[1]);
    assertEquals(2, cost[2]);
    assertEquals(5, cost[3]);
}
@Test
public void testBellmanFordDisconnected() {
    int INF = Integer.MAX_VALUE;
    int[][] adjMatrix = {
        {0, 6, INF, INF},
        {INF, 0, INF, INF},
        {INF, INF, 0, 2},
        {INF, INF, INF, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];
    Algorithms.setAdjMatrix(adjMatrix);
    boolean result = Algorithms.BellmanFord(0, cost, parent);

    assertEquals(true, result);
    assertEquals(0, cost[0]);
    assertEquals(6, cost[1]);
    assertEquals(Integer.MAX_VALUE, cost[2]);
    assertEquals(Integer.MAX_VALUE, cost[3]);
}
@Test
public void testBellmanFordSingleNode() {
    int INF = Integer.MAX_VALUE;
    int[][] adjMatrix = {
        {0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];
    Algorithms.setAdjMatrix(adjMatrix);
    boolean result = Algorithms.BellmanFord(0, cost, parent);

    assertEquals(true, result);
    assertEquals(0, cost[0]);
}
@Test
public void testBellmanFordNegativeCycle() {
    int INF = Integer.MAX_VALUE;
    int[][] adjMatrix = {
        {0, 1, INF},
        {INF, 0, -1},
        {-1, INF, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];
    Algorithms.setAdjMatrix(adjMatrix);
    boolean result = Algorithms.BellmanFord(0, cost, parent);

    assertEquals(false, result); // because negative cycle exists
}
}
