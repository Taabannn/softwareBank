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

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class BorrowTest {
    @BeforeAll
    public static void init() {
        System.out.println("In BorrowTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In BorrowTest after...");
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
    Person person;

    static Stream<Arguments> generatePersonObject() {
        return Stream.of(
                Arguments.of("Taban", new Disc("ubuntu"), new Date(12, 3, 90)),
                Arguments.of("Sara", new Disc("office"), new Date(5, 8, 88)),
                Arguments.of("Maryam", new Disc("comsol"), new Date(7, 9, 98))
        );
    }

    @ParameterizedTest
    @MethodSource("generatePersonObject")
    public void givenSomePersonalityInformation_WhenBorrowCalls_ThenABorrowedItemWillBeAdded(String name, Disc disc, Date dateOfBorrowing) {
        person = new Person(name);
        person.borrow(disc, dateOfBorrowing);
        assertTrue(person.getListOfBorrowed().contains(new Borrow(disc, dateOfBorrowing)));
    }
}
