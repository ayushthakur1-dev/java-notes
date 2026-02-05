package repository;

import exception.TaskNotFoundException;
import model.Task;

import java.util.*;

public class TaskRepository implements Repository<Task> {
    private final List<Task> tasks = new ArrayList<>();

    private TaskRepository() {
    }

    private static class TaskRepositorySingletonHeader {
        private static final TaskRepository INSTANCE = new TaskRepository();
    }

    public static TaskRepository getInstance() {
        return TaskRepositorySingletonHeader.INSTANCE;
    }

    @Override
    public Task add(Task item) {
        tasks.add(item);
        return item;
    }

    @Override
    public Task remove(Task item) {
        if(!(tasks.remove(item))) {
            throw new TaskNotFoundException("task not found");
        }

        return item;
    }

    @Override
    public Optional<Task> findById(long id) {
        return tasks.stream()
                .filter(x -> x.getTaskId() == id)
                .findFirst();
    }

    @Override
    public List<Task> findALL() {
        return tasks.stream().toList();
    }
}
