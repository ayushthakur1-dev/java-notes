import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class FunctionalInterface {
    public static void main(String[] args) {
//        Supplier -> take no argument provides a value -> get();
//        Consumer -> consumes one argument returns nothing -> accept();
//        BiConsumer -> consumes two argument returns nothing -> accept();
//        Predicate -> takes one argument returns boolean -> test();
//        BiPredicate -> takes two argument returns boolean -> test();
//        Function -> take a argument returns a value -> apply();
//        UnaryOperator -> take a argument returns value of same type -> apply();
//        BiFunction -> takes two argument and returns a value -> apply();
//        BinaryOpearator -> takes two argument of same type and returns a value of same type -> apply();

        Supplier<User> s = () -> new User();
        User user = s.get();
        user.show();

        Consumer<Integer> c1 = (i) -> System.out.println(i);
        c1.accept(10);

        BiConsumer<String, String> c2 = (s1, s2) -> System.out.println(s1 + " " + s2);
        c2.accept("Ayush", "Thakur");

        Predicate<Integer> p1 = (i) -> i % 2 == 0;
        System.out.println(p1.test(10));

        BiPredicate<Integer, String> p2 = (i, str) -> Integer.valueOf(str) == i;
        System.out.println(p2.test(10, "11"));

        Function<Integer, Integer> f1 = (i) -> i * 2;
        System.out.println(f1.apply(2));

        UnaryOperator<Integer> f2 = (i) -> i * 10;
        System.out.println(f2.apply(12));

        BiFunction<Integer, Integer, Long> f3 = (i1, i2) -> (long)i1 * i2;
        System.out.println(f3.apply(10, 100));

        BinaryOperator<Integer> f4 = (i1, i2) -> i1 + i2;
        System.out.println(f4.apply(12, 23));
    }
}

class User {
    public void show() {
        System.out.println("This is user");
    }
}