package util;

public class ValidationUtil {
    private static final String USERNAME_REGEX = "^[a-zA-Z][a-zA-Z0-9_]{2,19}$";
    private static final String TASKNAME_REGEX =
            "^(?=.{3,100}$)[A-Za-z0-9](?:[A-Za-z0-9 _.-]*[A-Za-z0-9])$";

    private ValidationUtil() {
    }

    public static void validateUsername(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("username cannot be empty");
        }
        else if(!name.matches(USERNAME_REGEX)) {
            throw new IllegalArgumentException("invalid username");
        }
    }

    public static void validateTaskTitle(String taskTitle) {
        if(taskTitle == null || taskTitle.isBlank()) {
            throw new IllegalArgumentException("task name cannot be empty");
        }
        else if(!taskTitle.matches(TASKNAME_REGEX)) {
            throw new IllegalArgumentException("invalid task name");
        }
    }
}