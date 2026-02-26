package app.helper;

import app.enums.UserType;
import app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserHelper {
    private UserService userService;

    public UserHelper(UserService userService) {
        this.userService = userService;
    }

    public void createUser(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter username: ");
        String name = sc.nextLine();

        System.out.print("Enter user type (ADMIN/REGULAR): ");
        String inputType = sc.nextLine().trim().toUpperCase();

        try {
            UserType type = UserType.valueOf(inputType);
            userService.createUser(name, type);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
