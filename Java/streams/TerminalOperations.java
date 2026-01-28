import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,2,3,4,5,6);

        // 1. collect(): termination operation that accepts a collector as an argument
        // collector: accumulates the input elements into specified collection
        arr.stream().skip(2).collect(Collectors.toList());
        // or we can directly chain collector methods in new version of java
        arr.stream().skip(2).toList();

        // 2. count(): count of ele ments present inside the steram
        System.out.println(arr.stream().filter((x) -> x % 2 == 0).count());

        // 3. forEach(): takes a consumer as argument
        // forEachOrdered(): if you are using parallel stream then order of regular forEach will not 
        // be sequential, if you want oredered operations in that case forEachOrdered() should be used
        arr.stream().skip(2).forEach(System.out::println);

        // 4. reduce(): combines elements to produce a single result, takes binaryOperator as argument
        System.out.println(arr.stream().reduce((x, y) -> x + y).get());

        // 5. anyMatch(): return true if any of the element matches the predicate
        // allMatch(): returns true if all of the elements matches the predicate
        // noneMatch(): returns true if none of the elements matches the predicate
        arr.stream().anyMatch((x) -> x % 2 == 0);
        arr.stream().allMatch((x) -> x >= 0);
        arr.stream().noneMatch((x) -> x < 0);

        // 6. findFirst(): returns first element of stream
        // findAny(): returns random element of stream but if the stream is sequential it will always behave as findFirst()

        // to find the random element from your list you can skip first random iterations
        int r = ThreadLocalRandom.current().nextInt(100);
        System.out.println(Stream.iterate(1, (x) -> x + 1).limit(100).skip(r).findFirst());

        // anyMatch(), allMatch(), noneMatch(), findFirst(), findAny() -> all of them are short cuircuit methods
        // meaning they will stop processing rest of the data when they find something

        // 7. min/max: min and max operation will behave according to the comparator given to them
        System.out.println(Stream.of(2,33,22,55,4).max(Comparator.naturalOrder()));
        System.out.println(Stream.of(2,33,22,55,4).min(Comparator.naturalOrder()));

        // stateful: operations that are aware of other elements. sorted, distinct comes under this category 
        // stateless: process only one element at a time. Operations like map, find, match are stateless

        // IMPORTANT: stream cannot be resued once a terminal operation is called 
        // EXAMPLE: 
        Stream<Integer> check = Stream.of(1,5,2,6,4,6);
        check = check.peek(System.out::print);
        // once terminal operation has been called stream will close itself
        check.toList(); 
        // check.max(Comparator.naturalOrder()); // this will give exception

        // NOTE: if there is a small operations whose result JVM already knows, it will not invoke the 
        // method at all eg. if instead of .toList(), .count() method was called here it is possible JVM already
        // has the result-> meaning treminal operation will not be called, and invoking of intermediate operaion will
        // never be triggered
    }
}
