package test.service;

import enums.UserType;
import model.User;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class UserServiceTest {

    @ParameterizedTest
    @CsvSource({
            "ayush, REGULAR,class model.RegularUser",
            "ayush_admin, ADMIN, class model.AdminUser"
    })
    void test_valid_user_creation(String name, UserType type, String expected) {
        User regularUser = UserService.createUser(name, type);
        assertEquals(expected , regularUser.getClass().toString());
    }

    @ParameterizedTest
    @CsvSource(value={
            "NULL, REGULAR, username cannot be empty",
            "'', REGULAR, username cannot be empty",
            "ayush, ADMIN, admin name should end with \"_admin\"",
            "a y u s h , REGULAR, invalid username"
    },
    nullValues = "NULL"
    )
    void test_invalid_user_creation(String name, UserType type, String expected) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UserService.createUser(name, type)
        );

        assertEquals(expected, exception.getMessage());
    }
}