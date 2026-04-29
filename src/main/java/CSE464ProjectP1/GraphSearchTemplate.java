package CSE464ProjectP1;

import java.util.*;

public abstract class GraphSearchTemplate {

    // Template method 
    public Path search(String src, String dst, Set<String> nodes, List<String[]> edges) {
        if (!nodes.contains(src) || !nodes.contains(dst)) return null;
        return executeSearch(src, dst, edges);
    }

    // Abstract method 
    protected abstract Path executeSearch(String src, String dst, List<String[]> edges);
}