package app.repository;

import app.exception.UserNotFoundException;
import app.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository implements RepositoryInterface<User> {
    private final List<User> users = new ArrayList<>();

    private UserRepository() {
    }

    private static class UserRepositorySingletonHeader {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return UserRepositorySingletonHeader.INSTANCE;
    }

    @Override
    public User add(User item) {
        users.add(item);
        return item;
    }

    @Override
    public User remove(User item) {
        if(!(users.remove(item))) {
            throw new UserNotFoundException("user not found");
        }
        return item;
    }

    @Override
    public Optional<User> findById(long id) {
        return users.stream()
                .filter(x -> x.getUserId() == id)
                .findFirst();
    }

    @Override
    public List<User> findALL() {
        return users.stream().toList();
    }
}
