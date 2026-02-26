package setinterface;

import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetImplementation {
    // uses a red black tree to store the element in sorted manner
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        NavigableSet<Integer> set2 = new TreeSet<>(); // to use navigable methods
        SortedSet<Integer> set3 = new TreeSet<>(); // to use sorted interface methods
    }
}