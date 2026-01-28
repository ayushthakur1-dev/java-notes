
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UnmodifiableSetImplementation {
    // to make unmodifable sets
    public static void main(String[] args) {
        Set<Integer> set = Set.of(1,2,3,4,5,6,7); // you can directly make unmodifable set
        // and unlike Map.of() it can take n number of elements not just 10

        // or you can make an existing set as unmodifiable
        Set<Integer> set2 = new HashSet<>();
        set.add(1);
        set.add(4);
        set.add(3);
        set.add(2);
        Set<Integer> set3 = Collections.unmodifiableSet(set2);
    }
}
