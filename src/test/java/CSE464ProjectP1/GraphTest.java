package CSE464ProjectP1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphTest {

    @Test
    void testParseGraph() throws Exception {

        Graph graph = new Graph();
        graph.parseGraph("input.dot");
        String output = graph.toString();
        assertTrue(output.contains("a -> b"));
        assertTrue(output.contains("b -> c"));
        assertTrue(output.contains("c -> d"));
    }

    @Test
    void testAddNode() {

        Graph graph = new Graph();
        graph.addNode("a");
        String output = graph.toString();
        assertTrue(output.contains("a"));
    }

    @Test
    void testAddNodes() {

        Graph graph = new Graph();
        graph.addNodes(new String[]{"x","y","z"});
        String output = graph.toString();
        assertTrue(output.contains("x"));
        assertTrue(output.contains("y"));
        assertTrue(output.contains("z"));
    }

    @Test
    void testAddEdge() {

        Graph graph = new Graph();
        graph.addEdge("x","y");
        String output = graph.toString();
        assertTrue(output.contains("x -> y"));
    }

    @Test
    void testOutputGraphics() throws Exception {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.outputGraphics("test_output.png", "png");

        // Verify the PNG file was created and is non-empty
        java.io.File file = new java.io.File("test_output.png");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testOutputDOTGraph() throws Exception {

        Graph graph = new Graph();
        graph.addEdge("a","b");
        graph.outputDOTGraph("test_output.txt");
        String actual = Files.readString(Paths.get("test_output.txt"));
        String expected =
                "digraph {\n" +
                        "a -> b;\n" +
                        "}";

        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testRemoveNode() {
        Graph g = new Graph();
        g.addEdge("a", "b");
        g.removeNode("a");

        String out = g.toString();
        assertFalse(out.contains("a -> "));
        assertFalse(out.contains("-> a"));
        assertFalse(Arrays.asList(out.split("\n")).contains("a"));
    }

    @Test
    void testRemoveNodes() {
        Graph g = new Graph();
        g.addEdge("a", "b");
        g.addEdge("b", "c");

        g.removeNodes(new String[]{"a", "b"});

        String out = g.toString();
        List<String> lines = Arrays.asList(out.split("\n"));
        assertFalse(lines.contains("a"));
        assertFalse(lines.contains("b"));
        assertFalse(out.contains("a -> "));
        assertFalse(out.contains("b -> "));
        assertFalse(out.contains("-> a"));
        assertFalse(out.contains("-> b"));
    }

    @Test
    void testRemoveEdge() {
        Graph g = new Graph();
        g.addEdge("x", "y");
        g.removeEdge("x","y");

        String out = g.toString();
        assertFalse(out.contains("x -> y"));
    }

    @Test
    void testRemoveNodeException() {
        Graph g = new Graph();
        assertThrows(IllegalArgumentException.class, () -> g.removeNode("z"));
    }

    @Test
    void testRemoveEdgeException() {
        Graph g = new Graph();
        g.addEdge("a","b");
        assertThrows(IllegalArgumentException.class, () -> g.removeEdge("b","c"));
    }

    //addressing comment 1

    @Test
        void testBFSSearch() throws Exception {
        Graph g = new Graph();
        g.parseGraph("input.dot");
        Path p = g.graphSearch("a", "h", Graph.Algorithm.BFS);
        assertNotNull(p);
        assertTrue(p.toString().startsWith("a"));
        assertTrue(p.toString().endsWith("h"));
    }

    @Test
    void testDFSSearch() throws Exception {
        Graph g = new Graph();
        g.parseGraph("input.dot");
        Path p = g.graphSearch("a", "h", Graph.Algorithm.DFS);
        assertNotNull(p);
        assertTrue(p.toString().startsWith("a"));
        assertTrue(p.toString().endsWith("h"));
    }

    @Test
    void testRandomWalkSearch() throws Exception {
        Graph g = new Graph();
        g.parseGraph("input.dot");
        boolean foundPath = false;
        for (int i = 0; i < 20; i++) {
            Path p = g.graphSearch("a", "c", Graph.Algorithm.RANDOM);
            if (p != null) {
                foundPath = true;
                break;
            }
        }
        assertTrue(foundPath);
    }

}