package ir.maktab58.softwareBank.models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Taban Soleymani
 */
public class IsLateTest {
    @BeforeAll
    public static void init() {
        System.out.println("In IsLateTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In IsLateTest after...");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each ...");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each ...");
    }

    @Mock
    Borrow borrow;

    static Stream<Arguments> generateBorrowObject() {
        return Stream.of(
                Arguments.of(new Disc("ubuntu"), new Date(12, 7, 98), new Date(3, 8, 98), true),
                Arguments.of(new Disc("office"), new Date(30, 7, 98), new Date(3, 8, 98), false),
                Arguments.of(new Disc("matlab"), new Date(11, 8, 95), new Date(1, 9, 95), true),
                Arguments.of(new Disc("matlab"), new Date(31, 2, 95), new Date(5, 3, 95), false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateBorrowObject")
    public void givenSomeBorrowObjects_WhenIsLateCalls_ThenIsLateOrNotWillBeDetermined(Disc disc, Date dateOfBorrowing, Date dateOfDelivery, boolean expectedIsLate) {
        borrow = new Borrow(disc, dateOfBorrowing);
        assertEquals(expectedIsLate, borrow.isLate(dateOfDelivery));
    }
}
