package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Taban Soleymani
 */
public class CalculateFinesTest {
    @BeforeAll
    public static void init() {
        System.out.println("In CalculateFinesTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In CalculateFinesTest after...");
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
    BankService bankService;

    static Stream<Arguments> generateMembers() {
        return Stream.of(
                Arguments.of(5, 200,
                        Arrays.asList(new Person("Taban", 12), new Person("Ali", 0), new Person("Fatemeh", 4)), new long[]{2400, 0, 800}),
                Arguments.of(8, 500,
                        Arrays.asList(new Person("mj", 6), new Person("Ali", 0), new Person("hassan", 0)), new long[]{3000, 0, 0})
        );
    }

    @ParameterizedTest
    @MethodSource("generateMembers")
    public void givenMembersWithNumberOfLateDays_WhenCalculateFinesCalls_ThenFineWillBeCalculated(int numOfEvents, long penalty, List<Person> members, long[] fines) {
        bankService = new BankService(numOfEvents, penalty);
        bankService.setMembers(members);
        bankService.calculateFines();
        members.forEach(member -> assertEquals(fines[members.indexOf(member)], member.getFine()));
    }
}
