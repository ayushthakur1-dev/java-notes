package mapinterface.hashtable;

import java.util.Hashtable;

public class HashTableImplementation {
    // was present before collection framework in jdk 1.2
    // legacy class -> replaced by concurrent hash map
    // synchronized
    // null value and key is not allowed at all 
    // slower than hashmap due to synchronization overhead
    // only linked list in case of collision
    public static void main(String[] args) {
        Hashtable<Integer, Integer> map = new Hashtable<>();
    }
}