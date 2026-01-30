package mapinterface.sortedmap;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapImplementation {
    // data is stored in red black tree instead of traditional array
    // implements navigable map(?) interface that extends sorted map(?) that extends map interface
    
    // navigable map -> provides more powerful navigation options such as finding the closest 
    // matching key or retrieving the map in reverse order

    // sorted map -> guarantees that the entries are sorted based on the keys,
    // either in natural ordering or by specific comparator
    public static void main(String[] args) {
        // you can also provide custom comparator inside constructor

        // if you want to use navigation operations use navigable map interface
        NavigableMap<Integer, Integer> map = new TreeMap<>((a, b)->b-a);

        // navigable map provides multiple navigation operations such as:
        // ceilingKey() -> returns least greater than given key, if no such key exist it will
        // simply return the greatest key or null if map is empty
        // lowerKey() -> returns greatest key less then given key

        // if you want to use sorting operations use sorted map interface
        SortedMap<Integer, Integer> map2 = new TreeMap<>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(5, 0);
        map.put(0, 0);

        System.out.println(map);
    }
}