package ir.maktab58.softwareBank.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    @BeforeAll
    public static void init() {
        System.out.println("In ShowAdminMenuTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In ShowAdminMenuTest after...");
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
    SoftwareBankSys softwareBankSys = new SoftwareBankSys();

    static Stream<Arguments> generateInputLines() {
        return Stream.of(
                Arguments.of("2 500%s1 1 88 Taban ubuntu%s9 1 88 Taban ubuntu%s1%s2%s4%s3%s")
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
