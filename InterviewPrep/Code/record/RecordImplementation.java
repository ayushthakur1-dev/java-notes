import java.util.List;
import java.util.ArrayList;

public class RecordImplementation {
    public static void main(String[] args) {
        User user = new User(
                1, "Ayush Thakur", 21, new ArrayList<>(List.of("Java", "C++", "Python"))
        );

        List<String> list = user.courses();
        System.out.println(user.courses());
        list.add("C#");
        System.out.println(user.courses());
    }
}

record User(int id, String name, int age, List<String> courses) {
    // methods can be overriden
    public List<String> courses() {
        return new ArrayList<>(courses);
    }

    // i can also make constructor but then it will not make all args constructor and we
    // have to create it explicitly + we have to initialize all the values and fields are final
    // and values can only be assigned to them at the time of creation
//    public User(String name, int age, List<String> courses) {
//        this.id = 0;
//        this.name = name;
//        this.age = age;
//        this.courses = courses;
//    }
}

// similar to
//final class User {
//    private final int id;
//    private final String name;
//    private final int age;
//    private final List<String> courses;
//
//    public User(int id, String name, int age, List<String> courses) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.courses = courses;
//    }
//
//    public int id() {
//        return id;
//    }
//
//    public String name() {
//        return name;
//    }
//
//    public int age() {
//        return age;
//    }
//
//    public List<String> courses() {
//        return courses;
//    }
//}