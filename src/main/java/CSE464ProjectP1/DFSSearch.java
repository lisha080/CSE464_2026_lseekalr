package CSE464ProjectP1;

import java.util.*;

public class DFSSearch extends GraphSearchTemplate {

    @Override
    protected Path executeSearch(String src, String dst, List<String[]> edges) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        
        if (dfsRecursive(src, dst, visited, path, edges)) return new Path(path);
        return null;
    }

    private boolean dfsRecursive(String current, String dst, Set<String> visited, List<String> path, List<String[]> edges) {
        visited.add(current);
        path.add(current);

        if (current.equals(dst)) return true;

        for (String[] edge : edges) {
            if (edge[0].equals(current) && !visited.contains(edge[1])) {
                if (dfsRecursive(edge[1], dst, visited, path, edges)) return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}