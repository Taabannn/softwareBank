package ir.maktab58.softwareBank.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Taban Soleymani
 */
public class InitializeNumOfEventsAndPenaltyTest {
    @Mock
    SoftwareBankSys softwareBankSys = new SoftwareBankSys();

    static Stream<Arguments> generateNumOfEventsAndPenalty() {
        return Stream.of(
                Arguments.of("2 500%s", new long[] {2, 500}),
                Arguments.of("4 1000%s", new long[] {4, 1000}),
                Arguments.of("6 700%s", new long[] {6, 700})
        );
    }

    @ParameterizedTest
    @MethodSource("generateNumOfEventsAndPenalty")
    public void givenSomeNumbersAsNumOfEventsAndPenalty_WhenInitializeNumOfEventsAndPenalty_ThenTheseParamsApplied(String inputLine, long[] bankServiceParams) {
        String userInput = String.format(inputLine, System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        Scanner scanner = new Scanner(System.in);
        softwareBankSys.initializeNumOfEventsAndPenalty(scanner);
        Assertions.assertEquals(bankServiceParams[0], softwareBankSys.getBankService().getNumOfEvents());
        Assertions.assertEquals(bankServiceParams[1], softwareBankSys.getBankService().getPenalty());
    }
}
