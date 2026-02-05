package mapinterface.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LinkedHashMapImplementation {
    public static void main(String[] args) {
        Map<Integer, Integer> map1 = new LinkedHashMap<>();
        
        // subclass of hashMap
        // has a double linked list to preserve the order of insertion
        // due to which it takes more time and space compared to it's parent HashMap
        
        // overridden constructor of linkedHashMap
        // we can set initial capacity of bucket array, load factor and accessOrder(?)
        // by default insertionOrder is set as true and accessOrder is false
        // only one of them can remain true so if we set accessOrder as true, insertionOrder will be false
        // but what is this? 
        
        // insertionOrder -> preserves the order of insertion
        // accessOrder -> preserves the order of access
        // meaning our double linked list will now store the element in the order they are accessed;
        // on accessing elements they will be placed at the end of the linked list
        Map<Integer, Integer> map2 = new LinkedHashMap<>(11, 0.3f, true);

        map1.put(1, 2);
        map1.put(2, 2);
        map1.put(3, 2);
        map1.put(4, 2);
        map1.put(5, 1);

        map2.put(1, 2);
        map2.put(2, 2);
        map2.put(3, 2);
        map2.put(4, 2);
        map2.put(5, 2);
        
        System.out.println("On insertion: ");
        String map1String = map1.keySet().stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
        
        System.out.println(map1String);
        
        String map2String = map2.keySet().stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
        System.out.println(map2String);

        System.out.println("\nOn accessing");
        map1.get(3);
        map1.get(4);
        map2.get(3);
        map2.get(4);

        map1String = map1.keySet()
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));

        map2String = map2.keySet()
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));

        System.out.println(map1String);
        System.out.println(map2String);

        // if you want to convert a hashmap into a linked hash map just pass it into constructor
    }
}

class LRU extends LinkedHashMap<Integer, Integer> {
    int capacity = 10;
    int size = 0;

    // this method is automatically invoked in LinkedHashMap before making an entry,
    // and we can define our custom logic here in order to control the behavior for removing
    // the eldest entry
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size > capacity; 
    }
}