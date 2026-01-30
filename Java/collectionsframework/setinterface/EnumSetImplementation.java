package queueinterface;

import java.util.EnumSet;
import java.util.Set;



public class EnumSetImplementation {
    // if you want to store enums as keys it would be more efficient to use 
    // enum set as similar to enum hashmap, but enumSet is abstract so we cannot instantiate
    // it like enumMap, so we have to create it using abstract methods like .of(), .allOf()
    public static void main(String[] args) {
        Set<Day> set = EnumSet.allOf(Day.class);
    }
}

enum Day {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
}
