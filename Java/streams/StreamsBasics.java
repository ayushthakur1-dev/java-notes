
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsBasics {
    // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
    // JAVA8 main features:
    // introduced streams for minimal code

    // stream? 
    // prcoess data in a functional and declarative manner, 
    // simplify data processing
    // embrace functional programming
    // improve readability and maintainability 
    // enable easy parallelism without complex multi threading

    // How to use stream?
    // Source, indermediate operations , terminal operations

    public static void main(String[] args) {
        
        // creating streams: 
        // from collections: just use .stream()
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        Stream<Integer> numbersStream = numbers.stream();

        // from arrays:
        String[] programmingLanguages = {"C++", "Java", "Python"};
        Stream<String> programmingLanguagesStream = Arrays.stream(programmingLanguages);

        // using Stream.of()
        Stream<String> stream1 = Stream.of("a", "b", "c");
        
        // infinite stream:
        // method 1:
        Stream<Integer> generate = Stream.generate(() -> 1); // stream with infinite 1s

        // method 2: you can iterate the stream and while iterating you are filling it
        // seed: from where to start iteration
        // function: what is returned using that seed as argument
        Stream<Integer> generate2 = Stream.iterate(0, (x) -> x + 1);

        // to limit the size of infinite stream:
        Stream<Integer> generateLimited = Stream.generate(() -> 1).limit(100);

        // .chars(): there is no method for processing individual characters of String in a stream
        // why? because this method exist in String itself that returns a stream

        String check = "Hello World";
        long numberOfLs = check.chars().filter((x) -> x == 'l').count();
        System.out.println("Number of Ls in " + check + ": " + numberOfLs);
    }
}