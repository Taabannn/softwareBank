package ir.maktab58.softwareBank.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class ValidateDateTest {
    @ParameterizedTest
    @CsvSource({"1400, 1, 31", "1401, 2, 31", "1402, 3, 31", "1399, 4, 31",
                "1398, 5, 31", "1397, 6, 31", "1400, 7, 30", "1401, 8, 30",
                "1402, 9, 30", "1399, 10, 30", "1398, 11, 30", "1397, 12, 29",
                "1400, 1, 1", "1401, 2, 1", "1402, 3, 1", "1399, 4, 1",
                "1398, 5, 1", "1397, 6, 1", "1400, 7, 1", "1401, 8, 1",
                "1402, 9, 1", "1399, 10, 1", "1398, 11, 1", "1397, 12, 1"})
    void givenValidDate_WhenValidateDateCalls_ThenTrueReturns(int year, int month, int day) {
        boolean result = Date.validateDate(year, month, day);
        assertTrue(result);
    }

}
