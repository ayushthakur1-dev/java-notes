package app.strategy;

import app.model.Task;

import java.util.Comparator;
import java.util.List;

public class SortByStatus implements TaskSortStrategy{
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .toList();
    }
}
