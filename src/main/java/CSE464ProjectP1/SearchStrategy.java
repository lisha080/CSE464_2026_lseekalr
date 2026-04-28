package CSE464ProjectP1;

import java.util.List;
import java.util.Set;

public interface SearchStrategy {
    Path search(String src, String dst, Set<String> nodes, List<String[]> edges);
}