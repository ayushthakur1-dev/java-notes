package app;

import app.helper.MenuHelper;
import app.helper.TaskHelper;
import app.helper.UserHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Profile("!test")
public class MainApp implements CommandLineRunner {

    private MenuHelper menuHelper;
    private UserHelper userHelper;
    private TaskHelper taskHelper;

    public MainApp(MenuHelper menuHelper, UserHelper userHelper, TaskHelper taskHelper) {
        this.menuHelper = menuHelper;
        this.userHelper = userHelper;
        this.taskHelper = taskHelper;
    }

    public void run(String... args) {
        Scanner sc = new Scanner(System.in);

        int input;
        do {
            menuHelper.printMenu();
            input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1 -> userHelper.createUser(sc);
                case 2 -> taskHelper.createTask(sc);
                case 3 -> taskHelper.assignTaskToUser(sc);
                case 4 -> taskHelper.viewTasks(sc);
                case 5 -> taskHelper.updateTask(sc);
                case 6 -> taskHelper.deleteTask(sc);
                case 7 -> taskHelper.sortTasks(sc);
                case 8 -> saveTasks();
                default -> menuHelper.printInvalidMessage();
            }

            System.out.println("\n------------------------------\n");
        } while (input != 8);

        sc.close();
    }

    private static void saveTasks() {
        System.out.println("Saving & Exiting...");
    }
}
