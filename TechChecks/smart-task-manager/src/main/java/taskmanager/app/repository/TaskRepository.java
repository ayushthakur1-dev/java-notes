package app.repository;

import app.exception.TaskNotFoundException;
import app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository implements RepositoryInterface<Task> {
    private final List<Task> tasks = new ArrayList<>();

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
