package ir.maktab58.softwareBank.view;

import ir.maktab58.softwareBank.service.BankService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Taban Soleymani
 */
public class ShowBorrowedDiscsTest {
    @Mock
    SoftwareBankSys softwareBankSys = new SoftwareBankSys();

    static Stream<Arguments> generateEvents() {
        return Stream.of(
                Arguments.of(5, 200,
                        Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"},
                                new String[]{"5","3", "99", "Taban", "AutoCad"},
                                new String[]{"28","2", "99", "Ali", "pspice"},
                                new String[]{"31","2", "99", "Ali", "pspice"},
                                new String[]{"4","2", "99", "Taban", "ADS"})),
                Arguments.of(5, 400,
                        Arrays.asList(new String[]{"1","1", "82", "mj", "office"},
                                new String[]{"9","1", "82", "mj", "office"},
                                new String[]{"13","2", "88", "mj", "ubuntu"},
                                new String[]{"20","2", "88", "ali", "gparted"},
                                new String[]{"25","2", "88", "mj", "ubuntu"},
                                new String[]{"20","12", "90", "hassan", "ubuntu"},
                                new String[]{"27","12", "90", "ali", "pes"},
                                new String[]{"1","1", "91", "ali", "pes"}))
        );
    }

    @ParameterizedTest
    @MethodSource("generateEvents")
    public void givenSomeEvents_WhenShowBorrowedDiscsCalls_ThenBorrowedDiscsWillBeShown(int numOfEvents, long penalty, List<String[]> bankEvents) {
        softwareBankSys.setBankService(new BankService(numOfEvents, penalty));
        softwareBankSys.getBankService().extractEvents(bankEvents);
        softwareBankSys.showBorrowedDiscs();
    }
}
