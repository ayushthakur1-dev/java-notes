package app.model;

public class RegularUser extends User {
    public RegularUser(String name) {
        super(name);
        System.out.println("User Created: " + this);
    }
}
