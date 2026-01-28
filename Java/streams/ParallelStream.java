
import java.util.List;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
        // type of stream enabling parallel process of elements
        // allowing multiple threads to process parts of stream simultaneously 
        // this can significantly imporove performance for large data sets
        // because workload is distributed accross multiple threads

        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000).toList();

        long startTime = System.currentTimeMillis();
        List<Long> result1 = list.stream().map(ParallelStream::factorial).toList();
        long endTime = System.currentTimeMillis();

        System.out.println("With sequential stream: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        List<Long> result2 = list.parallelStream().map(ParallelStream::factorial).toList();
        endTime = System.currentTimeMillis();

        System.out.println("With parallel stream: " + (endTime - startTime) + "ms");

        // Parallel stream are more efficient for CPU-intensive or large datasets where tasks are independent 
        // the may add overhead for simple taks or small datasets

        // we can also change parallel stream into sequential stream using .sequential()
    }

    public static long factorial(int x) {
        int i = 2;
        long result = 1;
        while(i <= x) {
            result *= i;
            i++;
        }

        return result;
    }
}