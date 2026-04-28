package CSE464ProjectP1;

import java.util.*;

public class RandomWalkSearch extends GraphSearchTemplate implements SearchStrategy {

    private static final int MAX_STEPS = 1000;

    @Override
    protected Path executeSearch(String src, String dst, List<String[]> edges) {
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        path.add(src);
        visited.add(src);
        System.out.println("visiting " + new Path(path));

        Random random = new Random();
        int steps = 0;

        while (steps < MAX_STEPS) {
            String current = path.get(path.size() - 1);

            if (current.equals(dst)) return new Path(path);

            List<String> neighbors = new ArrayList<>();
            for (String[] edge : edges) {
                if (edge[0].equals(current) && !visited.contains(edge[1])) {
                    neighbors.add(edge[1]);
                }
            }

            if (neighbors.isEmpty()) return null;

            String next = neighbors.get(random.nextInt(neighbors.size()));
            path.add(next);
            visited.add(next);
            System.out.println("visiting " + new Path(path));
            steps++;
        }

        return null;
    }
}