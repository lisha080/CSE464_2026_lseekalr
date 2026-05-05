package CSE464ProjectP1;

import java.util.*;

public class BFSSearch extends GraphSearchTemplate implements SearchStrategy {

    @Override
    protected Path executeSearch(String src, String dst, List<String[]> edges) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(Arrays.asList(src));
        visited.add(src);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String last = path.get(path.size() - 1);
            System.out.println("visiting " + new Path(path));

            if (last.equals(dst)) return new Path(path);

            for (String[] edge : edges) {
                if (edge[0].equals(last) && !visited.contains(edge[1])) {
                    visited.add(edge[1]);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(edge[1]);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }
}