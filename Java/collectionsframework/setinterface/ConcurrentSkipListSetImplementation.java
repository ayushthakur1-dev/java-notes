package setinterface;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;



public class ConcurrentSkipListSetImplementation {
    public static void main(String[] args) {
        // one way of making set synchronized
        // problem with this method: it blocks other threads on both read and write operations
        // hence performance overhead is high
        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

        // to solve this ConcurrentSkipListSet was introduced
        // it has built in thread safety 
        Set<Integer> set2 = new ConcurrentSkipListSet<>();
    }
}
