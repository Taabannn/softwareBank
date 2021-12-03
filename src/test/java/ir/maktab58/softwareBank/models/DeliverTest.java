package ir.maktab58.softwareBank.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Taban Soleymani
 */
public class DeliverTest {
    @Mock
    Person person;

    static Stream<Arguments> generatePersonObject() {
        return Stream.of(
                Arguments.of("Taban", Arrays.asList(new Borrow(new Disc("ubuntu"), new Date(1, 3, 90)), new Borrow(new Disc("office"), new Date(14, 7, 90))), new Disc("ubuntu"), new Date(12, 3, 90), 4),
                Arguments.of("Sara", Arrays.asList(new Borrow(new Disc("ubuntu"), new Date(1, 3, 90)), new Borrow(new Disc("office"), new Date(1, 8, 88))), new Disc("office"), new Date(5, 8, 88), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("generatePersonObject")
    public void givenSomePersonalityInformation_WhenDeliverCalls_ThenLateDaysIsCalculated(String name, List<Borrow> borrowList, Disc disc, Date dateOfDelivery, int numOfLateDays) {
        person = new Person(name);
        borrowList.forEach(borrowed -> person.borrow(borrowed.getDisc(), borrowed.getBorrowingDate()));
        person.deliver(disc, dateOfDelivery);
        assertEquals(numOfLateDays, person.getLateDays());
    }
}
