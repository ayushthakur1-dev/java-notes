import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LazyEvaluation {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ayush", "Om", "Sameer", "Vaibhav");
        Stream<String> filterredNames = names.stream().filter(x -> {
            System.out.println("Filterring...");
            return x.length() > 3;
        });

        System.out.println("Before using terminal operations: ");
        System.out.println("After using terminal operations: ");
        // List<String> result = filterredNames.collect(Collectors.toList());
        filterredNames.count();
        // System.out.println(result);

        // intermediate operations are only ivoked after the terminal operation
    }
}
