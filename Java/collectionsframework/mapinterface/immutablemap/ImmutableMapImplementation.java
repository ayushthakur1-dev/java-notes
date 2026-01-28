import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMapImplementation {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);

        Map<Integer, Integer> immutableMap = Collections.unmodifiableMap(map);
        // but there is one issue, we are creating a copy or view of other map
        // so if someone changes the orignal copy it will not match the copied one
        // hence things will be inconsistent

        // to solve this Map.of() was introduced in java9
        Map<Integer, Integer> orgMap = Map.of(1, 1, 2, 1, 3, 1);
        // BUT, it is only limited to 10 key value pairs

        // if you want more enteries there is another method for it:
        // Map.ofEnteries()
        Map<Integer, Integer> unlimitedEnteries = Map.ofEntries(
            Map.entry(1, 1),
            Map.entry(2, 1),
            Map.entry(3, 1),
            Map.entry(4, 1)
        );
    }
}