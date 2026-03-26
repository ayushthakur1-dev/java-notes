import java.util.Optional;

public class OptionalImplementation {
    public static void main(String[] args) {
        // before optional java code was full of try-catch block in order to handle NPE
        // empty -> creates a no value optional
        Optional<String> op1 = Optional.empty();
        System.out.println(op1);

        // of -> creates an optional with non-null value
        Optional<String> op2 = Optional.of("Ayush");
        System.out.println(op2);

        // ofNullable -> safe version of 'of' can handle null values also
        Optional<String> op3 = Optional.ofNullable(null);
        System.out.println(op3);
        Optional<String> op4 = Optional.ofNullable("Thakur");
        System.out.println(op4);

        // get -> extracts value, if absent throws exception(NoSuchElementException)
        System.out.println(op4.get());

        // isPresent -> checks if value is present
        System.out.println(op3.isPresent());

        // isEmpty -> checks if optional is empty
        System.out.println(op3.isEmpty());

        // ifPresentOrElse
        op3.ifPresentOrElse(
                val -> System.out.println(val),
                () -> System.out.println("Empty")
        );

        op4.ifPresentOrElse(
                val -> System.out.println(val),
                () -> System.out.println("Empty")
        );

        // stream
        // filter
        // map
        // flatMap

        // or -> alternative operation
        Optional<String> op5 = op3.or(() -> Optional.of("Empty"));

        // fallback methods
        // orElse -> but loads the default value even if the value is present
        System.out.println(op4.orElse("Empty"));

        // orElseGet -> load default value only if value is not present
        System.out.println(op4.orElseGet(() -> "Empty"));

        // orElseThrow() -> throws NoSuchElementException
        System.out.println(op4.orElseThrow());

        // orElseThrow(() -> new Exception(message))
        System.out.println(op4.orElseThrow(() -> new IllegalStateException()));

        // hashCode
        // toString
        // equals
    }
}