import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        // Predicate -> (returns a boolean) , .test()
        // BiPredicae -> returns boolean and accepts two arguments
        Predicate<Integer> check1 = x -> (x % 2) == 0;
        Predicate<Integer> check2 = x -> x < 100;
        Predicate<Integer> check = check1.and(check2); // we can combine methods with conditions like negate(reverse condition), and, or
        System.out.println(check.test(10));
        
        // Function -> work for you <Argument type, Return type>: .apply()
        // BiFunction -> accepts two argument
        // UnaryOperator -> accepts one argument but can be used to replace Function when type of argument and return value is same
        // BinaryOperator -> accepts two argument but can be used to replace BiFunction when type of both arguments and return value is same
        // so instead of writing Function<Integer, Integer> we can write UnaryOperator<Integer>
        // and instead of writing BiFunction<Integer, Integer, Integer> we can write BinaryOperator<Integer>
        Function<Integer, Integer> doubleIt = x -> x * 2;
        
        // Consumer -> takes an value but will not return anything: .accept()
        // BiConsumer -> takes two arguments

        // Supplier -> returns a value and takes nothing: get()
        // There is no BiSuplier because only one thing is returned and no arguments are taken

        // combined example:
        Predicate<Integer> predicate = x -> (x & 1) == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer = (x) -> System.out.println(x);
        Supplier<Integer> supplier = () -> 180;

        if(predicate.test(supplier.get())) {
            consumer.accept(function.apply(supplier.get()));
        }
    }
}