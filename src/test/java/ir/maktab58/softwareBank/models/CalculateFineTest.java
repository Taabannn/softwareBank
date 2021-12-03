package ir.maktab58.softwareBank.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class CalculateFineTest {
    @Mock
    Person person;

    static Stream<Arguments> generatePersonObject() {
        return Stream.of(
                Arguments.of("Taban", 12, 500, 6000),
                Arguments.of("Sara", 0, 300, 0),
                Arguments.of("Maryam", 1, 500, 500)
        );
    }

    @ParameterizedTest
    @MethodSource("generatePersonObject")
    public void givenSomePersonalityInformation_WhenCalculateFineCalls_ThenFineIsCalculated(String name, int numOfLateDays, long finePerDay, long expectedFine) {
        person = new Person(name, numOfLateDays);
        person.calculateFine(finePerDay);
        assertEquals(expectedFine, person.getFine());
    }
}
