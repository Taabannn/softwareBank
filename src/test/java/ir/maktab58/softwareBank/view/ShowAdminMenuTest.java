package ir.maktab58.softwareBank.view;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

/**
 * @author Taban Soleymani
 */
public class ShowAdminMenuTest {
    @Mock
    SoftwareBankSys softwareBankSys = new SoftwareBankSys();

    static Stream<Arguments> generateInputLines() {
        return Stream.of(
                Arguments.of("2 500%s1 1 88 Taban ubuntu%s9 1 88 Taban ubuntu%s1%s2%s4%s3%s")
                        /*%s1 1 88 Taban ubuntu%s9 1 88 Taban ubuntu%s1%s2%s4%s3*/
        );
    }

    @ParameterizedTest
    @MethodSource("generateInputLines")
    public void givenCorrectUserNameAndPassword_WhenLoginCalls_ThenAdminMenuWillOpen(String inputLines) {
        String userInput = String.format(inputLines, System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        softwareBankSys.showAdminMenu();
    }
}
