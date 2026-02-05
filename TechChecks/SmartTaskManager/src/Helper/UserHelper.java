package Helper;

import enums.UserType;
import service.UserService;

import java.util.Scanner;

public class UserHelper {
    public static void createUser(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter username: ");
        String name = sc.nextLine();

        System.out.print("Enter user type (ADMIN/REGULAR): ");
        String inputType = sc.nextLine().trim().toUpperCase();

        try {
            UserType type = UserType.valueOf(inputType);
            UserService.createUser(name, type);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
