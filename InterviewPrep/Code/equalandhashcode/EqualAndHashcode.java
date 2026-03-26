import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

class EqualAndHashcode {
    public static void main(String[] args) {
        Map<User, Integer> map = new HashMap<>();
        User user = new User(1, "Ayush", 21);
        User user2 = new User(2, "Thakur", 21);

        map.put(user, 1);
        map.put(user2, 2);

        System.out.println(map.get(user)); // name is mutable and included in hashcode

        user.setName("Thakur");

        // so it will casue issue while finding the element using key
        System.out.println(map.get(user));

        // but while iteration it may be present
        for (User u : map.keySet()) {
            System.out.println(map.get(u));
        }
    }
}

class User {
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof User)) return false;
        if(!super.equals(o)) return false;

        User user = (User)o;
        return user.getId() == getId() && user.getAge() == getAge() && Objects.equals(user.getName(), getName());
    }

    @Override
    public int hashCode() {
        // only immutable fields should be included in hashcode
        return Objects.hash(super.hashCode(), getId(), getName());
    }

}

