import java.util.EnumMap;
import java.util.Map;

public class EnumMapImplementation {
    // if all keys in a Map are values from a single enum, it is recommended to use enum map
    // Why? -> because it has the advantage of knowing all possible keys in advance and then it will
    // be more efficient than any other map(?)
    // as resizing will not be needed, it will know in advance how many keys are there
    // + there is no need to compute hash value of keys or to handle collision as enums are already mapped
    // by their ordinal(index of internal array in Enum) and because of this map entry will alsobe ordered 
    // by their ordinal

    // hence it is faster and efficient than regular hashmap in such cases, but it is not thread safe
    public static void main(String[] args) {
        Map<Day, Integer> map = new EnumMap<>(Day.class);

        map.put(Day.Friday, 1);
        map.put(Day.Monday, 2);

        // order of insertion -> friday, monday
        // order inside map -> monday, friday, becuase ordinal of monday comes before friday in it's respective enum
        System.out.println(map);
    }
}

enum Day {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
}