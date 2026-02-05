package model;

import enums.Priority;
import enums.TaskStatus;
import util.IdGenerator;
import util.ValidationUtil;

import java.io.Serializable;

public class Task implements Serializable {
    private final long taskId;
    private String taskTitle;
    private Priority priority;
    private TaskStatus status;
    private User assignedTo;

    public Task(String taskTitle, Priority priority) {
        ValidationUtil.validateTaskTitle(taskTitle);

        this.taskId = IdGenerator.generateTaskId();
        this.taskTitle = taskTitle;
        this.priority = priority;
        this.status = TaskStatus.NEW;
        this.assignedTo = null;
    }

    public long getTaskId() {
        return this.taskId;
    }

    public String getTaskTitle() {
        return this.taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        ValidationUtil.validateTaskTitle(taskTitle);
        this.taskTitle = taskTitle;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(User user) {
        this.assignedTo = user;
    }

    @Override
    public String toString() {
        return "Task{id="
                .concat(String.valueOf(this.getTaskId()))
                .concat(", title=")
                .concat(String.valueOf(this.getTaskTitle()))
                .concat(", priority=")
                .concat(String.valueOf(this.getPriority()))
                .concat(", status=")
                .concat(String.valueOf(this.getStatus()))
                .concat(", assignedTo=")
                .concat(String.valueOf(this.getAssignedTo()))
                .concat("}");
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        else if(!(obj instanceof Task)) return false;

        Task other = (Task)obj;
        return this.taskId == other.taskId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.taskId);
    }
}
