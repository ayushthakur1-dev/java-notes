
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations {
    // intermediate operations convert one form of stream into another
    // they are lazy, meaning they don't execute until a terminal operation is invoked
    public static void main(String[] args) {
        List<String> arr = Arrays.asList("Ayush", "Vaibhav", "Anurag", "Vipul", "Ayush", "Vipul", "Ajay");

        // 1. filter(): filters the data
        // no filterring at this point, it will be done when terminal operation is invoked
        Stream<String> filterredStream = arr.stream().filter(x -> x.startsWith("A"));

        // 2. map(): perform function on data
        arr.stream().map(String::toUpperCase); 

        // 3. sorted(): stream will be sorted in natural ordering or according to the comparator(if given)
        Stream<String> sortedInNaturalOrder = arr.stream().sorted(); 
        Stream<String> sortedWithComparator = arr.stream().sorted((a, b) -> a.length() - b.length());

        // 4. distinct(): remove the duplicate values
        arr.stream().filter(x -> x.startsWith("A")); // may contain duplicate values
        arr.stream().filter(x -> x.startsWith("A")).distinct();

        // 5. limit(): limit the size of stream
        arr.stream().filter(x -> x.startsWith("A")).distinct().limit(2);

        // 6. skip(): skip the given iterations
        arr.stream().filter(x -> x.startsWith("A")).distinct().skip(1).limit(2);

        // 7. peek(): do similar work as forEach, the major difference is that, peek is an intermediate oepration 
        // and hence follow lazy evaluation 
        arr.stream().peek(System.out::print);

        // 8. flatMap():
        // handles the stream of collections, arryas where each element is itslef a collection
        // Flatten nested structures (eg. lists within lists) so that they can be processed as a single sequence of element
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(1,2,3,4),
            Arrays.asList(5,6,7,8),
            Arrays.asList(9,10,11,12),
            Arrays.asList(13,14,15,16)
        );

        System.out.println(matrix);
        List<Integer> result = matrix.stream().flatMap(x -> x.stream()).toList();
        System.out.println(result);
    }
}
