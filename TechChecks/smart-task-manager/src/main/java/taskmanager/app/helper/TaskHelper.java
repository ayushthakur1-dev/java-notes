package app.helper;

import app.enums.Priority;
import app.enums.TaskStatus;
import app.exception.InvalidOperationException;
import app.exception.TaskNotFoundException;
import app.exception.UserNotFoundException;
import org.springframework.stereotype.Component;
import app.service.TaskService;
import app.strategy.SortByPriority;
import app.strategy.SortByStatus;
import app.strategy.TaskSortStrategy;

import java.util.Scanner;

@Component
public class TaskHelper {
    private TaskService taskService;
    private MenuHelper menuHelper;

    public TaskHelper(TaskService taskService, MenuHelper menuHelper) {
        this.taskService = taskService;
        this.menuHelper = menuHelper;
    }

    public void createTask(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Task Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Task Priority (LOW, MEDIUM, HIGH): ");
        String inputPriority = sc.nextLine();

        try {
            Priority priority = Priority.valueOf(
                    inputPriority.trim().toUpperCase()
            );
            taskService.createTask(title, priority);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void assignTaskToUser(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter admin id: ");
        long adminId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        System.out.print("Enter user id: ");
        long userId = sc.nextLong();

        try {
            taskService.assignTask(adminId, taskId, userId);
        } catch(UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewTasks(Scanner sc) {
        menuHelper.printViewMenu();
        int viewInput = sc.nextInt();

        switch (viewInput) {
            case 1 -> taskService.viewAllTasks();
            case 2 -> viewTasksByUser(sc);
            case 3 -> viewTasksByStatus(sc);
            case 4 -> viewTasksByPriority(sc);
            default -> menuHelper.printInvalidMessage();
        }
    }

    private void viewTasksByUser(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter user id: ");
        long userId = sc.nextInt();
        try {
            taskService.viewTaskByUser(userId);
        } catch(UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewTasksByStatus(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter status (NEW, IN_PROGRESS, COMPLETED): ");
        String inputStatus = sc.nextLine();

        try {
            TaskStatus status = TaskStatus.valueOf(
                    inputStatus.trim().toUpperCase()
            );
            taskService.viewTaskByStatus(status);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewTasksByPriority(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter Priority (LOW, MEDIUM, HIGH): ");
        String inputPriority = sc.nextLine();

        try {
            Priority priority = Priority.valueOf(
                    inputPriority.trim().toUpperCase()
            );
            taskService.viewTaskByPriority(priority);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(Scanner sc) {
        menuHelper.printUpdateMenu();
        int input = sc.nextInt();

        switch (input) {
            case 1 -> updateTaskTitle(sc);
            case 2 -> updateTaskPriority(sc);
            case 3 -> updateTaskStatus(sc);
            default -> menuHelper.printInvalidMessage();
        }
    }

    private void updateTaskTitle(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter user id: ");
        long userId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        sc.nextLine();

        System.out.print("Enter title: ");
        String title = sc.nextLine();
        try {
            taskService.updateTaskTitle(userId, taskId, title);
        } catch (UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTaskPriority(Scanner sc) {
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
            taskService.updateTaskPriority(userId, taskId, priority);
        } catch (UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTaskStatus(Scanner sc) {
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
            taskService.updateTaskStatus(userId, taskId, status);
        } catch (UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter admin id: ");
        long adminId = sc.nextLong();

        System.out.print("Enter task id: ");
        long taskId = sc.nextLong();

        try {
            taskService.deleteTask(adminId, taskId);
        } catch(UserNotFoundException | TaskNotFoundException | InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortTasks(Scanner sc) {
        menuHelper.printSortMenu();
        int input = sc.nextInt();
        TaskSortStrategy strategy;

        switch (input) {
            case 1:
                strategy = new SortByPriority();
                taskService.getSortedTasks(strategy);
                break;

            case 2:
                strategy = new SortByStatus();
                taskService.getSortedTasks(strategy);
                break;

            default:
                System.out.println("Invalid Request");
        }

    }
}
