package implementation;

import java.util.ArrayList;
import java.util.List;

public class Covariant {
    public static List<Cat> catList = new ArrayList<>();
    public static List<Puppy> puppyList = new ArrayList<>();
    public static List<? extends Dog> list = puppyList;
    // public static List<? extends Dog> list2 = catList; -> not allowed

    public static void convariantDemo() {
        list.add(null); // only write operation that is allowed
        // list.add(new Dog()); -> not allowed because it can contain any type
        // list.add(new Puppy()); -> also not allowed

        Dog d = list.get(0); // -> allowed because every class would be a subclass of dog
        Animal a = list.get(0); // also allowed
        Object o = list.get(0); // also allowed
        // Puppy p = list.get(0); -> not allowed because dog can have multiple subclasses

        // why? -> because it can contain any subclass of Dog, but only one of them that too
        // is decided at runtime
        // So I don't know which subclass is stored that is why I can't add anything except null,
        // but I can get anything using Dog's reference
    }
}

