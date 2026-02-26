package app.model;

import app.util.IdGenerator;
import app.util.ValidationUtil;

public abstract class User {
    protected final long userId;
    protected String username;

    protected User(String name) {
        ValidationUtil.validateUsername(name);

        this.userId = IdGenerator.generateUserId();
        this.username = name;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        ValidationUtil.validateUsername(username);
        this.username = username;
    }

    public boolean canUpdateTask(Task task) {
        if(!(this instanceof AdminUser) && !(this.equals(task.getAssignedTo()))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName()
                .concat("{id=")
                .concat(String.valueOf(this.userId))
                .concat(", name=")
                .concat(this.getUsername())
                .concat("}");
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        else if(!(obj instanceof User)) return false;

        User other = (User)obj;
        return this.getUserId() == other.getUserId();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.userId);
    }
}
