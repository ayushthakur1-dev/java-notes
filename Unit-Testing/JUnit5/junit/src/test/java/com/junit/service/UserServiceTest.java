package com.junit.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    public static UserService service;
    @BeforeAll // to perform something before starting test
    // eg. we are using an object in our test so we have to initialize it first
    // Note: it should be static
    static void setUp() {
        service = new UserService();
    }

    @BeforeEach // to perform some action before each test
    // eg. we have to empty an object before each test case
    void eachSetUp() {

    }

    // similarly we have @AfterAll and @AfterEach(eg usage: closing resource after use)

    @Disabled // to disable a test while running whole class
    @Test // declaring the type of test
    public void testAdd() {
        // checks if expected output is equal to actual output
        assertEquals(4, 2 + 2);

        String name = "Ayush";
        // checks that output should not be null
        assertNotNull(name);

        // checks if result is false
        assertFalse(5 < 2);
    }

    // to give arguments with expected output we use Csv
    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 2, 4",
            "5, 5, 11"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    // to give arguments without expected output ValueSource is recommended
    @ParameterizedTest
    @ValueSource(strings = {
            "ayush",
            "thakur",
            "",
    })
    // similarly ValueSource for other data types, ints, chars, doubles, etc.
    // can be given, or even custom ArgumentSource can be made
    public void testForNames(String name) {
        // we can also put message for the failed test
        assertNotNull(name, "failed for " + name);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 10, 20"
    })
    public void testForAdd(int expected, int a, int b) {
        assertEquals(expected, UserService.add(a, b));
    }
}
