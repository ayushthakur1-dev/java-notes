package app.util;

public class IdGenerator {
    private static long userCount = 0;
    private static long taskCount = 0;

    private IdGenerator() {

    }

    public static long generateUserId() {
        userCount++;
        return userCount;
    }

    public static long generateTaskId() {
        taskCount++;
        return taskCount;
    }
}
