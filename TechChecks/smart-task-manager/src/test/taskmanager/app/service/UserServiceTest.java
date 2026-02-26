package app.service;

import app.enums.UserType;
import app.model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class UserServiceTest {
    @Autowired
    private UserService userService;

    @ParameterizedTest
    @CsvSource({
            "ayush, REGULAR,class app.model.RegularUser",
            "ayush_admin, ADMIN, class app.model.AdminUser"
    })
    void test_valid_user_creation(String name, UserType type, String expected) {
        User regularUser = userService.createUser(name, type);
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
                () -> userService.createUser(name, type)
        );

        assertEquals(expected, exception.getMessage());
    }
}