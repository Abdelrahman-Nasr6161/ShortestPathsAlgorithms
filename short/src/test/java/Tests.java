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

@Test
public void testDijkstraSimple() {
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
    Algorithms.Dijkstra(0, cost, parent);

    assertEquals(0, cost[0]);
    assertEquals(5, cost[1]);
    assertEquals(8, cost[2]);
    assertEquals(9, cost[3]);
}

@Test
public void testDijkstraBalancedGraph() {
    int INF = Integer.MAX_VALUE;
    int[][] adjMatrix = {
        {0, 10,  5, INF, INF},
        {INF, 0,  2, INF, INF},
        {INF, 3,  0,  2, INF},
        {INF, INF, INF, 0,  1},
        {INF, INF, INF, INF, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];

    Algorithms.setAdjMatrix(adjMatrix);
    Algorithms.Dijkstra(0, cost, parent);

    assertEquals(0, cost[0]);
    assertEquals(8, cost[1]);
    assertEquals(5, cost[2]);
    assertEquals(7, cost[3]);
    assertEquals(8, cost[4]);
}

@Test
public void testDijkstraDenseGraph() {
    int[][] adjMatrix = {
        {0, 2, 5, 1},
        {2, 0, 3, 4},
        {5, 3, 0, 1},
        {1, 4, 1, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];

    Algorithms.setAdjMatrix(adjMatrix);
    Algorithms.Dijkstra(0, cost, parent);

    assertEquals(0, cost[0]);
    assertEquals(2, cost[1]);
    assertEquals(2, cost[2]);
    assertEquals(1, cost[3]);
}

@Test
public void testDijkstraDisconnectedGraph() {
    int[][] adjMatrix = {
        {0, 6, INF, INF},
        {INF, 0, INF, INF},
        {INF, INF, 0, 2},
        {INF, INF, INF, 0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];
    Algorithms.setAdjMatrix(adjMatrix);
    Algorithms.Dijkstra(0, cost, parent);
    
    assertEquals(0, cost[0]);
    assertEquals(6, cost[1]);
    assertEquals(Integer.MAX_VALUE, cost[2]);
    assertEquals(Integer.MAX_VALUE, cost[3]);
}

@Test
public void testDijkstraEmptyGraph() {
    int[][] adjMatrix = {
        {0}
    };

    int[] cost = new int[adjMatrix.length];
    int[] parent = new int[adjMatrix.length];

    Algorithms.setAdjMatrix(adjMatrix);
    Algorithms.Dijkstra(0, cost, parent);
    
    assertEquals(0, cost[0]);
}

    ////// Floyd-Warshall //////

    @Test
    public void testFloydWarshallSimple() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = {
                { 0, 5, INF, 10 },
                { INF, 0, 3, INF },
                { INF, INF, 0, 1 },
                { INF, INF, INF, 0 }
        };

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(true, result);
        assertEquals(0, cost[0][0]);
        assertEquals(5, cost[0][1]);
        assertEquals(8, cost[0][2]);
        assertEquals(9, cost[0][3]);
    }

    @Test
    public void testFloydWarshallSimple2() {

        int[][] adjMatrix = {
                { 0, 3, INF, 7 },
                { 8, 0, 2, INF },
                { 5, INF, 0, 1 },
                { 2, INF, INF, 0 }
        };

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(true, result);
        assertEquals(0, cost[0][0]);
        assertEquals(3, cost[0][1]);
        assertEquals(5, cost[0][2]);
        assertEquals(6, cost[0][3]);
        assertEquals(7, cost[3][2]);

        assertEquals(1, parent[0][2]);
    }

    @Test
    public void testFloydWarshallNegativeWeights() {

        int[][] adjMatrix = {
                { 0, 4, 5, INF },
                { INF, 0, -2, 6 },
                { INF, INF, 0, 3 },
                { INF, INF, INF, 0 }
        };

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(true, result);
        assertEquals(0, cost[0][0]);
        assertEquals(4, cost[0][1]);
        assertEquals(2, cost[0][2]);
        assertEquals(5, cost[0][3]);
    }

    @Test
    public void testFloydWarshallNegativeWeights2() {
        // Sheet 3 problem 2

        int[][] adjMatrix = {
                { 0, 3, 8, INF, -4 },
                { INF, 0, INF, 1, 7 },
                { INF, 4, 0, -5, INF },
                { 2, INF, INF, 0, INF },
                { INF, INF, INF, 6, 0 }
        };

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(true, result);
        assertEquals(0, cost[0][0]);
        assertEquals(-4, cost[0][4]);
        assertEquals(11, cost[1][2]);
        assertEquals(-7, cost[2][4]);
        assertEquals(6, cost[4][3]);

    }

    @Test
    public void testFloydWarshallDisconnected() {

        int[][] adjMatrix = {
                { 0, 6, INF, INF },
                { INF, 0, INF, INF },
                { INF, INF, 0, 2 },
                { INF, INF, INF, 0 }
        };

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(true, result);
        assertEquals(0, cost[0][0]);
        assertEquals(6, cost[0][1]);
        assertEquals(Integer.MAX_VALUE, cost[0][2]);
        assertEquals(Integer.MAX_VALUE, cost[0][3]);
    }

    @Test
    public void testFloydWarshallSingleNode() {

        int[][] adjMatrix = {
                { 0 }
        };

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(true, result);
        assertEquals(0, cost[0][0]);
    }

    @Test
    public void testFloydWarshallNegativeCycle() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = {
                { 0, 1, INF },
                { INF, 0, -1 },
                { -1, INF, 0 }
        };
        // 0->1 1
        // 1->2 -1
        // 2->0 -1

        int[][] cost = new int[adjMatrix.length][adjMatrix.length];
        int[][] parent = new int[adjMatrix.length][adjMatrix.length];

        Algorithms.setAdjMatrix(adjMatrix);
        boolean result = Algorithms.FloydWarshall(cost, parent);

        assertEquals(false, result); // because negative cycle exists
    }

}
