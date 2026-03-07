package CSE464ProjectP1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {
    private Set<String> nodes = new LinkedHashSet<>();
    private List<String[]> edges = new ArrayList<>();

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

        sb.append("Number of nodes: ").append(nodes.size()).append("\n");
        sb.append("Node labels:\n");
        for (String node : nodes) {
            sb.append(node).append("\n");
        }

        sb.append("Number of edges: ").append(edges.size()).append("\n");
        sb.append("Edges:\n");
        for (String[] edge : edges) {
            sb.append(edge[0]).append(" -> ").append(edge[1]).append("\n");
        }

        return sb.toString();
    }
}