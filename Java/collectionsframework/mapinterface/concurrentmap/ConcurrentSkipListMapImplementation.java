
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapImplementation {
    // sorted and thread safe
    // data is stored in SkipList(?) instead of normal array

    // skip list is a probabilistic data structure that allows for efficient search, insertion
    // and deletion operations
    // it is similar to a sorted linked list but with multiple layers(?) that skip over portions of the
    // list to provide faster access to elements

    // Multiple Layer EXAMPLE: 
    // lets say we have a list 1 2 3 4 5 6 7 8 9
    // they all will remain in sorted order
    // now there will be multiple layers like:
    // layer 1: 1 5 9
    // layer 2: 1 3 5 7 9
    // layer 3: 1 2 3 4 5 6 7 8 9
    // now lets say we have to retrive 7, first it will check at layer 1, 1 5 9, now we know that
    // 7 is greater than 5 and less than 9 so after going on second layer it will start search from 5
    // this will shorten the time to retrive an element

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
        
    }
}
