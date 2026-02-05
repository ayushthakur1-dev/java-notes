package service;

import enums.UserType;
import exception.UserNotFoundException;
import model.AdminUser;
import model.RegularUser;
import model.User;
import repository.UserRepository;

public class UserService {
    private static final UserRepository REPOSITORY = UserRepository.getInstance();

    private UserService() {
    }

    public static User createUser(String name, UserType type) {
        User user = switch (type) {
            case ADMIN -> new AdminUser(name);
            case REGULAR -> new RegularUser(name);
        };

        REPOSITORY.add(user);
        return user;
    }

    public static User findUser(long userId) {
        return REPOSITORY
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
