import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class StreamPractice {
    // only final and effective final variables can be accessed within lambda expression
    // and anonymous classes because local variables live on stack and lambdas may run anywhere else
    // by any other thread so it captures the value + if we change the value of variable after the use in lambda
    // there is ambiguity what should it use not latest value or captured value and java avoid this ambiguity by
    // using only final or effective final variables

    // but if it captures the value then why can't i change the value afterwards as it has nothing to do with the variable
    // this is because JVM allows to capture value of final or effectively final variables in order to avoid ambiguity and
    // confusin

    // how lambdas work?
    // before lambdas, anonymous classes were introduced, but the problem with them is that
    // extra space is assigned to them, an additional .class file is also created
    // in order to optimise this invokedynamic was introduced in Java7 before introducing lambdas in Java8
    // now compiler will not create a seperate class instead it creates a seperate private synthetic method
    // and bytecode uses invokedynamic , when jvm sees it, a boostrap method is called that builds a function object
    // using method reference and target functional interface, and once it is created JVM caches it and next time there
    // will be no boostrap call

    // other JVM instructions:
    // invokevirtual -> normal methods
    // invokestatic -> static methods
    // invokeinterface -> interface methods
    // invokedynamic -> dynamiclinkage

    public static void main(String[] args) {
        String str = "Ayush Thakur";
        String result = IntStream.range(0, str.length())
                .mapToObj(i -> str.charAt(str.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(result);

        System.out.println(
                str.chars()
                        .mapToObj(s -> String.valueOf((char)s))
                        .reduce("", (a, b) -> b + a)
        );

        List<String> list = new ArrayList<>(List.of("Ayush", "Thakur", "Vaibhav"));
        System.out.println(
                list.stream()
                        .map(s -> new StringBuilder(s).reverse().toString())
                        .toList()
        );

        System.out.println(
                list.stream()
                        .map(
                                s -> s.chars()
                                        .mapToObj(x -> String.valueOf((char)x))
                                        .reduce("", (a, b) -> b + a)
                        )
                        .toList()
        );
    }
}