import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CollectorDemo {
    public static void main(String[] args) {
        // collectors is a utility class that provides a set of methods to create common collections 

        List<String> names = Arrays.asList("Alice", "Bob", "John", "Ayush", "Thakur", "Om", "Sameer", "Vaibhav");

        // 1. we already know about collecting data through collectors in form of a collection 
        // using function like toList(), toSet(), ...

        // we can convert one collection to any other type of collection using stream
        names.stream().collect(Collectors.toCollection(()-> new ArrayDeque<>()));

        // 2. joining strings: concatenates stream elements into a single string
        String str = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(str);

        // 3. summarizing data: generates statistical summary: (count, sum, average, max, min)
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        IntSummaryStatistics stats = list.stream().collect(Collectors.summarizingInt((x) -> x));
        System.out.println(stats.getAverage());
        System.out.println(stats.getMax());
        System.out.println(stats.getSum());

        // instead of object we can also make these stats individually
        Double avg = list.stream().collect(Collectors.averagingInt(x -> x));
        Long count = list.stream().collect(Collectors.counting());
        int sum = list.stream().collect(Collectors.summingInt(x -> x));

        // 4. grouping elements:
        // by default it returns Map<K, List<V>>
        Map<Integer, Set<String>> map = names
            .stream()
            .collect(Collectors.groupingBy(
                String::length, // it will set the criteria on which data will be grouped
                TreeMap::new, // you can also decide the type of map you want
                Collectors.toCollection(()-> new HashSet<String>()) // you can also use downstream collector that will perform operation on that group
                // here i have just changed List to Set but any other operations can also be performed here that is valid for collect();
            ));

        // 5. Partitioning elements:
        // partitions elements into two groups(true and false) based on a predicate
        // downstream collector can also be added
        Map<Boolean, List<String>> map1 = names.stream().collect(Collectors.partitioningBy((x) -> x.length() > 3));

        // 6. Mapping and Collectings:
        // Applies a mapping function before collecting 
        List<String> result = names.stream()
            .collect(
                Collectors.mapping(
                    String::toLowerCase, // mapping function
                    Collectors.toCollection(()-> new ArrayList<String>()) // downstream collector
                )
            );

        System.out.println(result);

        // toMap(): for converting something into a map 
        List<String> fruits = Arrays.asList("apple", "banana", "Apple", "Orange", "BAnana", "OranGe");
        Map<String, Integer> fruitMap = fruits.stream().
            collect(
                Collectors.toMap(
                    k -> k.toLowerCase(), // key mapper
                    v -> 1, // value mapper
                    (x, y)-> x + y) // merge function 
                    // tells what to do with values of both duplicate keys after merging them
                );
            
        System.out.println(fruitMap);
    }
}