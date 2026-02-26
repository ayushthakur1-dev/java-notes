package app.model;

public class AdminUser extends User {
    public AdminUser(String name) {
        super(name);

        if(!name.endsWith("_admin")) {
            throw new IllegalArgumentException("admin name should end with \"_admin\"");
        }

        System.out.println("Admin Created: " + this);
    }
}
