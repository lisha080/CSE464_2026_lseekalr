package CSE464ProjectP1;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

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
        assertFalse(out.contains("a"));
    }

    @Test
    void testRemoveNodes() {
        Graph g = new Graph();
        g.addEdge("a", "b");
        g.addEdge("b", "c");

        g.removeNodes(new String[]{"a","b"});

        String out = g.toString();
        assertFalse(out.contains("a"));
        assertFalse(out.contains("b"));
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

}