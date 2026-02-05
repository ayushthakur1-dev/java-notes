package Helper;

import enums.Priority;
import enums.TaskStatus;
import exception.InvalidOperationException;
import exception.TaskNotFoundException;
import exception.UserNotFoundException;
import service.TaskService;
import strategy.SortByPriority;
import strategy.SortByStatus;
import strategy.TaskSortStrategy;

import java.util.Scanner;

public class TaskHelper {
    public static void createTask(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Task Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Task Priority (LOW, MEDIUM, HIGH): ");
        String inputPriority = sc.nextLine();

        try {
            Priority priority = Priority.valueOf(
                    inputPriority.trim().toUpperCase()
            );
            TaskService.createTask(title, priority);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void assignTaskToUser(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter admin id: ");
        long adminId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        System.out.print("Enter user id: ");
        long userId = sc.nextLong();

        try {
            TaskService.assignTask(adminId, taskId, userId);
        } catch(UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewTasks(Scanner sc) {
        MenuHelper.printViewMenu();
        int viewInput = sc.nextInt();

        switch (viewInput) {
            case 1 -> TaskService.viewAllTasks();
            case 2 -> viewTasksByUser(sc);
            case 3 -> viewTasksByStatus(sc);
            case 4 -> viewTasksByPriority(sc);
            default -> MenuHelper.printInvalidMessage();
        }
    }

    private static void viewTasksByUser(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter user id: ");
        long userId = sc.nextInt();
        try {
            TaskService.viewTaskByUser(userId);
        } catch(UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewTasksByStatus(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter status (NEW, IN_PROGRESS, COMPLETED): ");
        String inputStatus = sc.nextLine();

        try {
            TaskStatus status = TaskStatus.valueOf(
                    inputStatus.trim().toUpperCase()
            );
            TaskService.viewTaskByStatus(status);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewTasksByPriority(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Priority (LOW, MEDIUM, HIGH): ");
        String inputPriority = sc.nextLine();

        try {
            Priority priority = Priority.valueOf(
                    inputPriority.trim().toUpperCase()
            );
            TaskService.viewTaskByPriority(priority);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTask(Scanner sc) {
        MenuHelper.printUpdateMenu();
        int input = sc.nextInt();

        switch (input) {
            case 1 -> updateTaskTitle(sc);
            case 2 -> updateTaskPriority(sc);
            case 3 -> updateTaskStatus(sc);
            default -> MenuHelper.printInvalidMessage();
        }
    }

    private static void updateTaskTitle(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter user id: ");
        long userId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        sc.nextLine();

        System.out.print("Enter title: ");
        String title = sc.nextLine();
        try {
            TaskService.updateTaskTitle(userId, taskId, title);
        } catch (UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateTaskPriority(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter user id: ");
        long userId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        sc.nextLine();

        System.out.print("Enter priority(LOW, MEDIUM, HIGH): ");
        String inputPriority = sc.nextLine();

        try {
            Priority priority = Priority.valueOf(
                    inputPriority.trim().toUpperCase()
            );
            TaskService.updateTaskPriority(userId, taskId, priority);
        } catch (UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateTaskStatus(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter user id: ");
        long userId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        sc.nextLine();

        System.out.print("Enter status(NEW, IN_PROGRESS, COMPLETED): ");
        String inputStatus = sc.nextLine();

        try {
            TaskStatus status = TaskStatus.valueOf(
                    inputStatus.trim().toUpperCase()
            );
            TaskService.updateTaskStatus(userId, taskId, status);
        } catch (UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter admin id: ");
        long adminId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        try {
            TaskService.deleteTask(adminId, taskId);
        } catch(UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sortTasks(Scanner sc) {
        MenuHelper.printSortMenu();
        int input = sc.nextInt();
        TaskSortStrategy strategy;

        switch (input) {
            case 1:
                strategy = new SortByPriority();
                TaskService.getSortedTasks(strategy);
                break;

            case 2:
                strategy = new SortByStatus();
                TaskService.getSortedTasks(strategy);
                break;

            default:
                System.out.println("Invalid Request");
        }

    }
}
