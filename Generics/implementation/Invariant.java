package implementation;

import java.util.ArrayList;
import java.util.List;

public class Invariant {
    // unlike covariant and contravariant that are decided at runtime
    // invariant is default generic that is decided at compile time

    public static void invariantDemo() {
        // List<Animal> animalList = new ArrayList<Dog>(); -> this is not allowed because it is already decided
        // at compile time that object is of Animal type

        // so covariant and contravariant lets us decide at compile time if we want to take any type of specific reference
        // and in addition it also restricts other references
    }
}
