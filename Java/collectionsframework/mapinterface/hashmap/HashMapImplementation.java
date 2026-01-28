
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class HashMapImplementation {
    // key features:
    // unordered
    // allow null values and keys, but there can be only one null key as keys are unique
    // not thread safe requires external synchronization if used in multi threaded context
    // offers constant time performance O(1) for basic operations like get and put, assuming the hash
    // function disperses elements properly.

    // internal structure of hashmap:
    // basic components:

    // key: identifier used to retrieve the value

    // value: data associated with the key

    // bucket: a place where key value pairs are stored. Think of bucket as cells in a list(array)

    // hashfunction: converts a key into an index(bucket location) for storage
    // a hasfunction is an algorithm that takes an input(or key) and returns a fixed size string
    // of bytes typically a numerical value. 
    // the output is known as hashcode, hashvalue, or simply hash. 
    // The primary purpose of a hash function is to map data of arbitary size to data of fixed size
    // it is deterministic that is same input will always produce same output
    // regardless of the input size the hash code has a consistent size (32-bit, 64-bit etc)
    // the hash function computes the hash quickly

    // how data is stored in a HashMap
    // Step1: hashing the key
    // key passed through a hash function to generate a unique hash code(an integer number)
    // this value helps determine where the key value pair is stored in the array called bucket array   

    // step2: calculating the index
    // index = hashCode & arraySize

    // step3: storing in the bucket
    // key value pair stored at calculated index, each bucket can hold multiple pairs, this is called
    // collision handling

    // why collision handling is needed? -> becuase deterministic value produced by a hash function is finite 
    // that is two different keys can have same hash value
    // so all those keys having same hash value will be stored at same bucket cell in form of
    // linked list or balanced binary search tree known as red-black tree(after java8)
    // when a single bucket exceeds it's threashold capacity of 8(default) it will be converted from linked list to red black tree
    // in order to avoid sequential search, this process of convertion is known as treefication

    // default size of internal array is 16(default), when the number of elements grows and exceeds a certain load factor
    // (default is 0.75), hashmap automatically resized the array to hold more data, this process is called rehashing
    // eg. default size if 16 so when (16*0.75=)12 elements are inserted, the hashmap will resize

    // during rehashing the array size is doubled, all existing enteries are rehashed (that is position is recalculated)
    // and placed into new array, this ensures the HashMap continues to perform effeciently even as more data is added

    // time complextiy: 
    // provides constant time for basic opreations like get, put, ... assuming no collision
    // if there is collision, the performance will degrade to O(n) where n is number of elements in bucket
    // but after java 8 if there are too many elements in a single bucket hashmap swithces to balanced tree reducing
    // the time complexity O(n);

    // how data is retrived from HashMap:
    // hash the key, calculate the index, search in bucket

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>(20, 0.5f);

        // put(key, value) -> to make a key value entry
        // putIfAbsent(key, value) -> only makes an entry if key is not present in map

        // get(key) -> to get value mapped to corresponding key
        // getOrDefault(key, defaultValue) -> if key is present return value else return a default value

        // containsKey(key) -> to check if key is present or not

        // containsValue(value) -> to check if a value is present or not

        // entrySet() -> return a set of all key-value pairt

        // keySet() -> reutrn a set of all the keys

        // values() -> return a list(not a collection list) of all the values

        // remove(key) -> remove entry having that key
        // remove(key, value) -> remove only if key-value pair exist 

        // it is non iterable: so can we iterate it like this
        for(int key : map.keySet()) {
            System.out.println(map.get(key));
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue());
            System.out.println(entry.getKey());
        }

        // pair<int, int> a = {1, 2};
        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(1, 2);
    }

    // while storing an object into a collection that uses hashfunction, by default the hashCode is 
    // generated from memory address of that object, therefore all the object wil have different hashCode,
    // similarly equals() will check the memory address by default  

    // but this behaviour can be customized by overriding hashCode() and equals() function
    @Override
    public int hashCode() {
        return super.hashCode();
        // return Objects.hashCode(values... on which you want your hashcode to deppend)
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        // return Objects.equals(param1, param2) && Objects.equals(param1, param2) && ... 
        // all the fields you want to check for equality
    }
}