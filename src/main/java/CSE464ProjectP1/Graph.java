package CSE464ProjectP1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private Set<String> nodes = new LinkedHashSet<>();
    private List<String[]> edges = new ArrayList<>();

    //feature 1
    public void parseGraph(String filepath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        for (String line : lines) {
            line = line.trim();

            if (line.contains("->")) {
                line = line.replace(";", "");
                String[] parts = line.split("->");

                String src = parts[0].trim();
                String dst = parts[1].trim();

                nodes.add(src);
                nodes.add(dst);
                edges.add(new String[]{src, dst});
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        //refactor 3: making variable names more understandable

        int nodeCount = nodes.size();
        int edgeCount = edges.size();

        sb.append("Number of nodes: ").append(nodeCount).append("\n");
        sb.append("Node labels:\n");
        for (String node : nodes) {
            sb.append(node).append("\n");
        }

        sb.append("Number of edges: ").append(edgeCount).append("\n");
        sb.append("Edges:\n");
        for (String[] edge : edges) {
            sb.append(edge[0]).append(" -> ").append(edge[1]).append("\n");
        }

        return sb.toString();
    }

    //feature 2
    public void addNode(String label) {
        nodes.add(label);
    }
    public void addNodes(String[] labels) {
        for (String label : labels)
            nodes.add(label);
    }

    //feature 3

    //refactor 2: add method edgeExists so the method addEdge is more clearly defined
    private boolean edgeExists(String srcLabel, String dstLabel) {
        for (String[] edge : edges) {
            if (edge[0].equals(srcLabel) && edge[1].equals(dstLabel))
                return true;
        }
        return false;
    }

    public void addEdge(String srcLabel, String dstLabel) {
        nodes.add(srcLabel);
        nodes.add(dstLabel);

        if (!edgeExists(srcLabel, dstLabel))
            edges.add(new String[]{srcLabel, dstLabel});
    }

    //feature 4
    public void outputDOTGraph(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        for (String[] edge : edges)
            sb.append(edge[0]).append(" -> ").append(edge[1]).append(";\n");
        sb.append("}");

        Files.write(Paths.get(path), sb.toString().getBytes());
    }

    public void outputGraphics(String path, String format) throws Exception {
        outputDOTGraph("temp.dot");
        ProcessBuilder pb = new ProcessBuilder(
                "dot",
                "-T" + format,
                "temp.dot",
                "-o",
                path
        );
        pb.start().waitFor();
    }

    //feature 5 fixed
    //refactor 4 Extract Method edge removal method made separate 

    private void removeEdgesContaining(String label) {
        Iterator<String[]> it = edges.iterator();
        while (it.hasNext()) {
            String[] edge = it.next();
            if (edge[0].equals(label) || edge[1].equals(label)) {
                it.remove();
            }
        }
    }

    public void removeNode(String label) {
        if (!nodes.contains(label)) {
            throw new IllegalArgumentException();
        }
        nodes.remove(label);
        removeEdgesContaining(label);
    }

    //feature 6
    //refactor 5: Extract method to get the node checking functionality separate from the node remove method

    private void checkIfNodesExist(String[] labels) {
        for (String label : labels) {
            if (!nodes.contains(label)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public void removeNodes(String[] labels) {
        checkIfNodesExist(labels);
        for (String label : labels) {
            nodes.remove(label);
        }

        Set<String> removeSet = new HashSet<>(Arrays.asList(labels));

        Iterator<String[]> it = edges.iterator();
        while (it.hasNext()) {
            String[] edge = it.next();
            if (removeSet.contains(edge[0]) || removeSet.contains(edge[1])) {
                it.remove();
            }
        }
    }

    //feature 7
    public void removeEdge(String srcLabel, String dstLabel) {
        boolean removed = edges.removeIf(edge ->
                edge[0].equals(srcLabel) && edge[1].equals(dstLabel)
        );
        if (!removed) {
            throw new IllegalArgumentException();
        }
    }

    public enum Algorithm {
        BFS,
        DFS
    }

    public Path graphSearch(String src, String dst, Algorithm algo) {
        GraphSearchTemplate searcher;
            if (algo == Algorithm.BFS) {
                searcher = new BFSSearch();
            } else {
                searcher = new DFSSearch();
            }
        return searcher.search(src, dst, nodes, edges);
    }

}