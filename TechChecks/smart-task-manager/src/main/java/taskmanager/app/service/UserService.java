package app.service;

import app.enums.UserType;
import app.exception.UserNotFoundException;
import app.model.AdminUser;
import app.model.RegularUser;
import app.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import app.repository.RepositoryInterface;

@Service
public class UserService {
    private final RepositoryInterface<User> REPOSITORY;

    public UserService(@Qualifier("userRepository") RepositoryInterface<User> repository) {
        this.REPOSITORY = repository;
    }

    public User createUser(String name, UserType type) {
        User user = switch (type) {
            case ADMIN -> new AdminUser(name);
            case REGULAR -> new RegularUser(name);
        };

        REPOSITORY.add(user);
        return user;
    }

    public User findUser(long userId) {
        return REPOSITORY
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
