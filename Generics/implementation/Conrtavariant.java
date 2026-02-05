package implementation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Conrtavariant {
    public static List<Animal> animalList = new ArrayList<>();
    public static List<Object> objectList  = new ArrayList<>();
    public static List<Dog> dogList = new ArrayList<>();

    public static List<? super Dog> list = animalList;
    public static List<? super Dog> list2 = objectList;
    public static List<? super Dog> lis3 = dogList;

    public static void contravariantDemo() {
        // '? super Dog' means: list can contain any class that is Dog, or it's superclass

        // but once that type is specified it can contain only its subtypes
        objectList.add(new Object());
        objectList.add(new Animal());
        objectList.add(new Dog());
        objectList.add(new Puppy());
        objectList.add(new Cat());

        animalList.add(new Animal());
        animalList.add(new Dog());
        animalList.add(new Cat());
        animalList.add(new Puppy());

        dogList.add(new Dog());
        dogList.add(new Puppy());

        // we can only get through Object
        // Animal a = list.get(0); -> not allowed
        Object o = list.get(0); // only read allowed -> because it is the only safe read
    }
}
