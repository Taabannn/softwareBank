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
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class LoginTest {
    @BeforeAll
    public static void init() {
        System.out.println("In LoginTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In LoginTest after...");
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

    static Stream<Arguments> generateCorrectUserAndPass() {
        return Stream.of(
                Arguments.of("admin%sadmin")
        );
    }

    @ParameterizedTest
    @MethodSource("generateCorrectUserAndPass")
    public void givenCorrectUserNameAndPassword_WhenLoginCalls_ThenAdminMenuWillOpen(String userAndPass) {
        String userInput = String.format(userAndPass, System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        assertThrows(NoSuchElementException.class, () -> softwareBankSys.login());
    }

    static Stream<Arguments> generateInCorrectUserAndPass() {
        return Stream.of(
                Arguments.of("admin%sAdmin"),
                Arguments.of("Admin%sadmin"),
                Arguments.of("Admin%sAdmin"),
                Arguments.of("Taban%sadmin"),
                Arguments.of("Taban%s61378")
        );
    }

    @ParameterizedTest
    @MethodSource("generateInCorrectUserAndPass")
    public void givenInCorrectUserNameAndPassword_WhenLoginCalls_ThenAdminMenuWillOpen(String userAndPass) {
        String userInput = String.format(userAndPass, System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
    }
}
