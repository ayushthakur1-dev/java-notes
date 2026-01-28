
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    // stream for primitive data types
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5};
        IntStream stream1 = Arrays.stream(numbers);

        Stream<Integer> stream2 = Arrays.stream(numbers).boxed(); 
        // boxed? -> box the primitve data type into their corresponding wrapper class

        List<Integer> list = IntStream.range(1, 11).
            boxed().
            collect(Collectors.toList());

        // to get random primitive values
        List<Double> randomDoubles = new Random().doubles(5).boxed().toList();
        System.out.println(randomDoubles);
    }
}
