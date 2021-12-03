package ir.maktab58.softwareBank.models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class ValidateDateTest {
    @BeforeAll
    public static void init() {
        System.out.println("In ValidateDateTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In ValidateDateTest after...");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each ...");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each ...");
    }

    static Stream<Arguments> generateValidDate() {
        return Stream.of(
                Arguments.of(1398, 12, 29),
                Arguments.of(1397, 6, 31),
                Arguments.of(1398, 3, 31),
                Arguments.of(1378, 6, 23),
                Arguments.of(1402, 7, 30),
                Arguments.of(1403, 9, 1),
                Arguments.of(1400, 8, 27),
                Arguments.of(1400, 11, 30),
                Arguments.of(1378, 2, 31),
                Arguments.of(1378, 4, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("generateValidDate")
    void givenValidDate_WhenValidateDateCalls_ThenTrueReturns(int year, int month, int day) {
        assertTrue(Date.validateDate(year, month, day));
    }

    static Stream<Arguments> generateInValidMonth() {
        return Stream.of(
                Arguments.of(1398, 13, 11),
                Arguments.of(1397, 14, 15),
                Arguments.of(1398, 0, 1),
                Arguments.of(1378, 23, 23)
        );
    }

    @ParameterizedTest
    @MethodSource("generateInValidMonth")
    void givenInValidMonth_WhenValidateDateCalls_ThenExceptionThrows(int year, int month, int day) {
        assertThrows(IllegalArgumentException.class, () -> Date.validateDate(year, month, day), "Wrong value for month, it must be from 1 to 12");
    }

    static Stream<Arguments> generateInValidDay() {
        return Stream.of(
                Arguments.of(1398, 12, 30, "Wrong value for day. it is supposed that 12th month just has 29 days"),
                Arguments.of(1399, 1, 32, "Wrong value for day, it must be from 1 to 31"),
                Arguments.of(1399, 9, 31, "Wrong value for day. in second half of year, months have less than 31 days"),
                Arguments.of(1398, 7, 31, "Wrong value for day. in second half of year, months have less than 31 days")
        );
    }

    @ParameterizedTest
    @MethodSource("generateInValidDay")
    void givenInValidDay_WhenValidateDateCalls_ThenExceptionThrows(int year, int month, int day, String message) {
        assertThrows(IllegalArgumentException.class, () -> Date.validateDate(year, month, day), message);
    }
}
