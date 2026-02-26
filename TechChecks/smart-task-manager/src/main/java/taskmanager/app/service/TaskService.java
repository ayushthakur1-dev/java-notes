package app.service;

import app.enums.Priority;
import app.enums.TaskStatus;
import app.exception.InvalidOperationException;
import app.exception.TaskNotFoundException;
import app.model.AdminUser;
import app.model.Task;
import app.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import app.repository.RepositoryInterface;
import app.strategy.TaskSortStrategy;

@Service
public class TaskService {
    private final RepositoryInterface<Task> REPOSITORY;
    private UserService userService;

    private TaskService(@Qualifier("taskRepository") RepositoryInterface<Task> repository, UserService userService){
        this.REPOSITORY = repository;
        this.userService = userService;
    }

    public Task createTask(String title, Priority priority) {
        Task task = new Task(title, priority);
        REPOSITORY.add(task);
        System.out.println("Task Created: " + task);
        return task;
    }

    public void assignTask(long adminID, long taskID, long userID) {
        User admin = userService.findUser(adminID);
        if(!(admin instanceof AdminUser)) {
            throw new InvalidOperationException("only admin can assign task");
        }

        User user = userService.findUser(userID);
        Task task = REPOSITORY.findById(taskID)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));

        task.setAssignedTo(user);
        System.out.println("Task: " + task
                + "\nAssigned To: " + user
                + "\nAssigned By: " + admin
        );
    }

    public void viewAllTasks() {
        REPOSITORY.findALL().forEach(System.out::println);
    }

    public void viewTaskByUser(long userId) {
        User user = userService.findUser(userId);
        REPOSITORY.findALL().stream()
                .filter(x -> user.equals(x.getAssignedTo()))
                .forEach(System.out::println);

    }

    public void viewTaskByStatus(TaskStatus status) {
        REPOSITORY.findALL().stream()
                .filter(x -> x.getStatus() == status)
                .forEach(System.out::println);
    }

    public void viewTaskByPriority(Priority priority) {
        REPOSITORY.findALL().stream()
                .filter(x -> x.getPriority() == priority)
                .forEach(System.out::println);
    }

    public Task deleteTask(long adminId, long taskId) {
        User admin = userService.findUser(adminId);
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

    public Task updateTaskTitle(long userId, long taskId, String title) {
        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        User user = userService.findUser(userId);
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

    public Task updateTaskPriority(long userId, long taskId, Priority priority) {
        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        User user = userService.findUser(userId);
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

    public Task updateTaskStatus(long userId, long taskId, TaskStatus status) {
        Task task = REPOSITORY.findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        User user = userService.findUser(userId);
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

    public void getSortedTasks(TaskSortStrategy strategy) {
        strategy.sort(
                    REPOSITORY.findALL()
                )
                .forEach(System.out::println);
    }
}
