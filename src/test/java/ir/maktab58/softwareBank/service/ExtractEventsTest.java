package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import ir.maktab58.softwareBank.models.eventsfactory.BorrowEvent;
import ir.maktab58.softwareBank.models.eventsfactory.DeliveryEvent;
import ir.maktab58.softwareBank.models.eventsfactory.SoftwareBankEvent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class ExtractEventsTest {
    @Mock
    BankService bankService;

    static Stream<Arguments> generateValidData() {
        return Stream.of(
                Arguments.of(5, 200,
                        Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"},
                                      new String[]{"5","3", "99", "Taban", "AutoCad"},
                                      new String[]{"28","2", "99", "Ali", "pspice"},
                                      new String[]{"31","2", "99", "Ali", "pspice"},
                                      new String[]{"4","2", "99", "Taban", "ADS"}),
                        Arrays.asList(new BorrowEvent(new Person("Taban"), new Date(31, 1, 1399), new Disc("ADS")),
                                      new BorrowEvent(new Person("Taban"), new Date(5, 3, 1399), new Disc("AutoCad")),
                                      new BorrowEvent(new Person("Ali"), new Date(28, 2, 1399), new Disc("pspice")),
                                      new DeliveryEvent(new Person("Ali"), new Date(31, 2, 1399), new Disc("pspice")),
                                      new DeliveryEvent(new Person("Taban"), new Date(4, 2, 1399), new Disc("ADS"))
                                )),
                Arguments.of(8, 500,
                        Arrays.asList(new String[]{"1","1", "82", "mj", "office"},
                                      new String[]{"9","1", "82", "mj", "office"},
                                      new String[]{"13","2", "88", "mj", "ubuntu"},
                                      new String[]{"20","2", "88", "ali", "gparted"},
                                      new String[]{"25","2", "88", "mj", "ubuntu"},
                                      new String[]{"20","12", "90", "hassan", "ubuntu"},
                                      new String[]{"27","12", "90", "ali", "pes"},
                                      new String[]{"1","1", "91", "ali", "pes"}),
                        Arrays.asList(new BorrowEvent(new Person("mj"), new Date(1, 1, 1382), new Disc("office")),
                                      new DeliveryEvent(new Person("mj"), new Date(9, 1, 1382), new Disc("office")),
                                      new BorrowEvent(new Person("mj"), new Date(13, 2, 1388), new Disc("ubuntu")),
                                      new BorrowEvent(new Person("ali"), new Date(20, 2, 1388), new Disc("gparted")),
                                      new DeliveryEvent(new Person("mj"), new Date(25, 2, 1388), new Disc("ubuntu")),
                                      new BorrowEvent(new Person("hassan"), new Date(20, 12, 1390), new Disc("ubuntu")),
                                      new BorrowEvent(new Person("ali"), new Date(27, 12, 1390), new Disc("pes")),
                                      new DeliveryEvent(new Person("ali"), new Date(1, 1, 1391), new Disc("pes"))
                        ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateValidData")
    public void givenValidElements_WhenExtractEventCalls_ThenArrayListOfEventsCreate(int numOfEvents, long penalty, List<String[]> events, List<SoftwareBankEvent> expectedEvents) {
        bankService = new BankService(numOfEvents, penalty);
        bankService.extractEvents(events);
        assertEquals(expectedEvents, bankService.getEvents());
    }

    static Stream<Arguments> generateDataOfInvalidDate() {
        return Stream.of(
                Arguments.of(2, 500, Arrays.asList(new String[]{"34","1", "99", "Taban", "ADS"}, new String[]{"5","3", "99", "Taban", "AutoCad"})),
                Arguments.of(3, 1000, Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"}, new String[]{"5","14", "99", "Taban", "AutoCad"}, new String[]{"28","2", "99", "Ali", "pspice"})),
                Arguments.of(5, 1500, Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"}, new String[]{"5","3", "99", "Taban", "AutoCad"}, new String[]{"30","12", "99", "Ali", "pspice"}, new String[]{"31","2", "99", "Ali", "pspice"}, new String[]{"4","2", "99", "Taban", "ADS"})),
                Arguments.of(5, 200, Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"}, new String[]{"5","3", "99", "Taban", "AutoCad"}, new String[]{"28","2", "99", "Ali", "pspice"}, new String[]{"31","6", "99", "Ali", "pspice"}, new String[]{"4","2", "9000", "Taban", "ADS"}))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataOfInvalidDate")
    public void givenInvalidDate_WhenExtractEventCalls_ThenIllegalArgumentExThrow(int numOfEvents, long penalty, List<String[]> events) {
        bankService = new BankService(numOfEvents, penalty);
        assertThrows(IllegalArgumentException.class, () -> bankService.extractEvents(events));
    }

    static Stream<Arguments> generateDataOfInvalidNumbers() {
        return Stream.of(
                Arguments.of(2, 500, Arrays.asList(new String[]{"s","1", "99", "Taban", "ADS"}, new String[]{"5","3", "99", "Taban", "AutoCad"})),
                Arguments.of(3, 1000, Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"}, new String[]{"5","d", "99", "Taban", "AutoCad"}, new String[]{"28","2", "99", "Ali", "pspice"})),
                Arguments.of(5, 1500, Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"}, new String[]{"5","3", "99", "Taban", "AutoCad"}, new String[]{"d","12", "99", "Ali", "pspice"}, new String[]{"31","2", "99", "Ali", "pspice"}, new String[]{"4","2", "99", "Taban", "ADS"})),
                Arguments.of(5, 200, Arrays.asList(new String[]{"31","1", "99", "Taban", "ADS"}, new String[]{"5","3", "99", "Taban", "AutoCad"}, new String[]{"28","2", "99", "Ali", "pspice"}, new String[]{"31","6", "99", "Ali", "pspice"}, new String[]{"4","2", "y", "Taban", "ADS"}))
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataOfInvalidNumbers")
    public void givenInvalidNumber_WhenExtractEventCalls_ThenNumberFormatExceptionThrows(int numOfEvents, long penalty, List<String[]> events) {
        bankService = new BankService(numOfEvents, penalty);
        assertThrows(NumberFormatException.class, () -> bankService.extractEvents(events));
    }

}
