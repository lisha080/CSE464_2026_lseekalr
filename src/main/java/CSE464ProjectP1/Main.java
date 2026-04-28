package CSE464ProjectP1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {

        // Feature 1 - Parse graph
        Graph graph = new Graph();
        graph.parseGraph("input.dot");
        System.out.println("=== Feature 1: Parse Graph ===");
        System.out.println(graph);

        // Feature 2 - Add nodes
        graph.addNode("x");
        graph.addNodes(new String[]{"y","z"});
        System.out.println("=== Feature 2: Add Nodes ===");
        System.out.println(graph);

        // Feature 3 - Add edges
        graph.addEdge("x","a");
        graph.addEdge("a","b");
        System.out.println("=== Feature 3: Add Edges ===");
        System.out.println(graph);

        // Feature 4 - Output DOT and PNG
        graph.outputDOTGraph("output.dot");
        graph.outputGraphics("output.png","png");
        System.out.println("=== Feature 4: Output DOT and PNG ===");
        System.out.println(graph);

        // Feature 5 - Remove node
        System.out.println("=== Feature 5: Remove Node ===");
        graph.removeNode("x");
        System.out.println("After removing node 'x':");
        System.out.println(graph);

        // Feature 6 - Remove nodes
        System.out.println("=== Feature 6: Remove Nodes ===");
        graph.removeNodes(new String[]{"y","z"});
        System.out.println("After removing nodes 'y' and 'z':");
        System.out.println(graph);

        // Feature 7 - Remove edge
        System.out.println("=== Feature 7: Remove Edge ===");
        graph.removeEdge("a","b");
        System.out.println("After removing edge a->b:");
        System.out.println(graph);

        // Feature 8 - BFS search
        System.out.println("=== Feature 8: BFS Graph Search ===");
        Graph graph2 = new Graph();
        graph2.parseGraph("input.dot");
        Path bfsPath = graph2.graphSearch("a", "h", Graph.Algorithm.BFS);
        System.out.println("BFS path from a to h: " + bfsPath);
        System.out.println();

        // Feature 9 - DFS search
        System.out.println("=== Feature 9: DFS Graph Search ===");
        Path dfsPath = graph2.graphSearch("a", "h", Graph.Algorithm.DFS);
        System.out.println("DFS path from a to h: " + dfsPath);
        System.out.println();

        // Feature 10 - Random Walk Search
        System.out.println("=== Feature 10: Random Walk Search ===");
        Graph graph3 = new Graph();
        graph3.parseGraph("input.dot");

        for (int i = 0; i < 5; i++) {
            System.out.println("Random Walk #" + (i + 1));
            Path randomPath = graph3.graphSearch("a", "c", Graph.Algorithm.RANDOM);
            if (randomPath != null) {
                System.out.println("Success: " + randomPath);
            } else {
                System.out.println("Failed: no path found");
            }
            System.out.println();
        }
    }
}