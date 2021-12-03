package ir.maktab58.softwareBank.models.eventsfactory;

import ir.maktab58.softwareBank.exceptions.IllegalEventSateEx;
import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
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
public class SBEventFactoryTest {
    @BeforeAll
    public static void init() {
        System.out.println("In SBEventFactoryTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In SBEventFactoryTest after...");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each ...");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each ...");
    }

    static Stream<Arguments> generateBorrowEvent() {
        return Stream.of(
                Arguments.of("borrow", new Person("Taban"), new Date(5, 2, 98), new Disc("ubuntu")),
                Arguments.of("borrow", new Person("Zahra"), new Date(11, 8, 99), new Disc("ubuntu"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateBorrowEvent")
    public void givenBorrowEventInfo_WhenGetEventCalls_ThenAnInstanceHasBeenCreated(String type, Person person, Date dateOfEvent, Disc disc) {
        SoftwareBankEvent softwareBankEvent = SBEventFactory.getEvent(type, person, dateOfEvent, disc);
        assertTrue(softwareBankEvent instanceof BorrowEvent);
    }

    static Stream<Arguments> generateDeliveryEvent() {
        return Stream.of(
                Arguments.of("deliver", new Person("Taban"), new Date(5, 2, 98), new Disc("ubuntu")),
                Arguments.of("deliver", new Person("Zahra"), new Date(11, 8, 99), new Disc("ubuntu"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDeliveryEvent")
    public void givenDeliverEventInfo_WhenGetEventCalls_ThenAnInstanceHasBeenCreated(String type, Person person, Date dateOfEvent, Disc disc) {
        SoftwareBankEvent softwareBankEvent = SBEventFactory.getEvent(type, person, dateOfEvent, disc);
        assertTrue(softwareBankEvent instanceof DeliveryEvent);
    }

    static Stream<Arguments> generateNotSetEvent() {
        return Stream.of(
                Arguments.of("not-set", new Person("Taban"), new Date(5, 2, 98), new Disc("ubuntu")),
                Arguments.of("invalid", new Person("Zahra"), new Date(11, 8, 99), new Disc("ubuntu"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateNotSetEvent")
    public void givenUnknownEventInfo_WhenGetEventCalls_ThenAnExceptionThrows(String type, Person person, Date dateOfEvent, Disc disc) {
        assertThrows(IllegalEventSateEx.class, () -> SBEventFactory.getEvent(type, person, dateOfEvent, disc), "Invalid event. Just borrowEvent and deliveryEvent are acceptable.");
    }
}
