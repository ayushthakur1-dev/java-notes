package queueinterface;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetImplementation {
    // why it is needed? -> only ConcurrentSkipListSet is thread safe,
    // but it stores the elements in sorted order and it is weakly consistent(?)

    // weakly consistent:
    // suppose we are traversing a ConcurrentSkipListSet and added an element into it
    // now it is not certain that it will reflect that change or not 

    // but in case of CopyOnWriteArraySet it is not the case as it will only reflect changes
    // when read operation is over, but there will be overhead on write operations

    // so in case where we are doing write intensive operations it is recommended to use ConcurrentSkipListSet
    // and when we have more iteration operations than we need stability, hence CopyOnWriteArraySet should be used

    // it is similar to CopyOnWriteArrayList, major difference it that it does not store 
    // duplicate elements
    public static void main(String[] args) {
        Set<Integer> set = new CopyOnWriteArraySet<>();
        Set<Integer> set2 = new ConcurrentSkipListSet<>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        System.out.println("Concurrent Read Write Operations on CopyOnWriteArraySet: ");
        for(Integer num : set) {
            System.out.println(num);
            set.add(5);
        }
        System.out.println("5 was not added in orignal list during read operation");
        System.out.println(set);

        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(4);
        System.out.println("Concurrent Read Write Operations on ConcurrentSkipListSet: ");
        for(Integer num : set2) {
            System.out.println(num);
            set2.add(5);
        }
        System.out.println("5 may be reflected during read operation");
        System.out.println(set2);
    }
}
