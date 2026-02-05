package app;

import Helper.MenuHelper;
import Helper.TaskHelper;
import Helper.UserHelper;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input;
        do {
            MenuHelper.printMenu();
            input = sc.nextInt();
            System.out.println();

            switch (input) {
                case 1 -> UserHelper.createUser(sc);
                case 2 -> TaskHelper.createTask(sc);
                case 3 -> TaskHelper.assignTaskToUser(sc);
                case 4 -> TaskHelper.viewTasks(sc);
                case 5 -> TaskHelper.updateTask(sc);
                case 6 -> TaskHelper.deleteTask(sc);
                case 7 -> TaskHelper.sortTasks(sc);
                case 8 -> saveTasks();
                default -> MenuHelper.printInvalidMessage();
            }

            System.out.println("\n------------------------------\n");
        } while (input != 8);

        sc.close();
    }

    private static void saveTasks() {
        System.out.println("Saving & Exiting...");
    }
}
