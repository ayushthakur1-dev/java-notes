package app.helper;

import org.springframework.stereotype.Component;

@Component
public class MenuHelper {
    public void printMenu() {
        System.out.print(
                "1. Create User\n"
                        + "2. Create Task\n"
                        + "3. Assign Task to User\n"
                        + "4. View Tasks\n"
                        + "5. Update Task\n"
                        + "6. Delete Task\n"
                        + "7. Sort / Filter Tasks\n"
                        + "8. Save & Exit\n"
                        + "\nEnter input: "
        );
    }

    public void printViewMenu() {
        System.out.println(
                "View Options:\n"
                        + "1: View All Tasks\n"
                        + "2: View Tasks By User\n"
                        + "3: View Tasks By Status\n"
                        + "4: View Tasks By Priority\n"
                        + "Enter input: "
        );
    }

    public void printUpdateMenu() {
        System.out.print(
                "Choose field to update: \n"
                        + "1. Title\n"
                        + "2. Priority\n"
                        + "3. Status\n"
                        + "Enter input: "
        );
    }

    public void printSortMenu() {
        System.out.print(
                "Choose sorting strategy: \n"
                        + "1. Priority\n"
                        + "2. Status\n"
                        + "Enter input: "
        );
    }

    public void printInvalidMessage() {
        System.out.println("Invalid Request");
    }
}