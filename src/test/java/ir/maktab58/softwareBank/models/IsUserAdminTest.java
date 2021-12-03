package ir.maktab58.softwareBank.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Taban Soleymani
 */
public class IsUserAdminTest {
    @Mock
    Admin admin = new Admin();

    static Stream<Arguments> generateUserInfo() {
        return Stream.of(
                Arguments.of("Taban", "61378", false),
                Arguments.of("admin", "admin", true),
                Arguments.of("Admin", "admin", false),
                Arguments.of("admin", "Admin", false),
                Arguments.of("Admin", "Admin", false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateUserInfo")
    public void givenSomeUserInfo_WhenIsUserAdmin_ThenIsUserAdminWillBeCleared(String username, String password, boolean expectedIsAdmin) {
        assertEquals(expectedIsAdmin, admin.isUserAdmin(username, password));
    }
}
