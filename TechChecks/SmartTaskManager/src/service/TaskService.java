package service;

import enums.Priority;
import enums.TaskStatus;
import exception.InvalidOperationException;
import exception.TaskNotFoundException;
import model.AdminUser;
import model.Task;
import model.User;
import repository.TaskRepository;
import strategy.TaskSortStrategy;

public class TaskService {
    private static final TaskRepository REPOSITORY = TaskRepository.getInstance();

    private TaskService(){
    }

    public static Task createTask(String title, Priority priority) {
        Task task = new Task(title, priority);
        REPOSITORY.add(task);
        System.out.println("Task Created: " + task);
        return task;
    }

    public static void assignTask(long adminID, long taskID, long userID) {
        User admin = UserService.findUser(adminID);
        if(!(admin instanceof AdminUser)) {
            throw new InvalidOperationException("only admin can assign task");
        }

        User user = UserService.findUser(userID);
        Task task = REPOSITORY.findById(taskID)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));

        task.setAssignedTo(user);
        System.out.println("Task: " + task
                + "\nAssigned To: " + user
                + "\nAssigned By: " + admin
        );
    }

    public static void viewAllTasks() {
        REPOSITORY.findALL().forEach(System.out::println);
    }

    public static void viewTaskByUser(long userId) {
        User user = UserService.findUser(userId);
        REPOSITORY.findALL().stream()
                .filter(x -> user.equals(x.getAssignedTo()))
                .forEach(System.out::println);

    }

    public static void viewTaskByStatus(TaskStatus status) {
        REPOSITORY.findALL().stream()
                .filter(x -> x.getStatus() == status)
                .forEach(System.out::println);
    }

    public static void viewTaskByPriority(Priority priority) {
        REPOSITORY.findALL().stream()
                .filter(x -> x.getPriority() == priority)
                .forEach(System.out::println);
    }

    public static Task deleteTask(long adminId, long taskId) {
        User admin = UserService.findUser(adminId);
        if(!(admin instanceof AdminUser)) {
            throw new InvalidOperationException("only admin can delete a task");
        }

        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        REPOSITORY.remove(task);
        System.out.println("Task: " + task
                + "\n Deleted By: " + admin
        );

        return task;
    }

    public static Task updateTaskTitle(long userId, long taskId, String title) {
        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        User user = UserService.findUser(userId);
        if(user.canUpdateTask(task)) {
            throw new InvalidOperationException("only admin or assigned user can update task");
        }

        task.setTaskTitle(title);
        System.out.println("Task: " + task
                + "\nUpdated Title: " + title
                + "\nUpdated By: " + user
        );
        return task;
    }

    public static Task updateTaskPriority(long userId, long taskId, Priority priority) {
        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        User user = UserService.findUser(userId);
        if(user.canUpdateTask(task)) {
            throw new InvalidOperationException("only admin or assigned user can update task");
        }

        task.setPriority(priority);
        System.out.println("Task: " + task
                + "\nUpdated Priority: " + priority
                + "\nUpdated By: " + user
        );
        return task;
    }

    public static Task updateTaskStatus(long userId, long taskId, TaskStatus status) {
        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        User user = UserService.findUser(userId);
        if(user.canUpdateTask(task)) {
            throw new InvalidOperationException("only admin or assigned user can update task");
        }

        task.setStatus(status);
        System.out.println("Task: " + task
                + "\nUpdated Status : " + status
                + "\nUpdated By: " + user
        );
        return task;
    }

    public static void getSortedTasks(TaskSortStrategy strategy) {
        strategy.sort(
                    REPOSITORY.findALL()
                )
                .forEach(System.out::println);
    }
}
