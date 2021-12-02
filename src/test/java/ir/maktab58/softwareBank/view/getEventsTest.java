package ir.maktab58.softwareBank.view;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import ir.maktab58.softwareBank.models.eventsfactory.BorrowEvent;
import ir.maktab58.softwareBank.models.eventsfactory.DeliveryEvent;
import ir.maktab58.softwareBank.models.eventsfactory.SoftwareBankEvent;
import ir.maktab58.softwareBank.service.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taban Soleymani
 */
public class getEventsTest {
    @Mock
    SoftwareBankSys softwareBankSys = new SoftwareBankSys();

    static Stream<Arguments> generateEvents() {
        return Stream.of(
                Arguments.of(8, 500, String.format("1 1 82 mj office%s9 1 82 mj office%s13 2 88 mj ubuntu%s20 2 88 ali gparted%s25 2 88 mj ubuntu%s20 12 90 hassan ubuntu%s27 12 90 ali pes%s1 1 91 ali pes%s", System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator()),
                        Arrays.asList(new BorrowEvent(new Person("mj"), new Date(1, 1, 1382), new Disc("office")),
                                new DeliveryEvent(new Person("mj"), new Date(9, 1, 1382), new Disc("office")),
                                new BorrowEvent(new Person("mj"), new Date(13, 2, 1388), new Disc("ubuntu")),
                                new BorrowEvent(new Person("ali"), new Date(20, 2, 1388), new Disc("gparted")),
                                new DeliveryEvent(new Person("mj"), new Date(25, 2, 1388), new Disc("ubuntu")),
                                new BorrowEvent(new Person("hassan"), new Date(20, 12, 1390), new Disc("ubuntu")),
                                new BorrowEvent(new Person("ali"), new Date(27, 12, 1390), new Disc("pes")),
                                new DeliveryEvent(new Person("ali"), new Date(1, 1, 1391), new Disc("pes"))
                        )),
                Arguments.of(5, 400, String.format("31 1 99 Taban ADS%s5 3 99 Taban AutoCad%s28 2 99 Ali pspice%s31 2 99 Ali pspice%s4 2 99 Taban ADS%s", System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator()),
                        Arrays.asList(new BorrowEvent(new Person("Taban"), new Date(31, 1, 1399), new Disc("ADS")),
                                new BorrowEvent(new Person("Taban"), new Date(5, 3, 1399), new Disc("AutoCad")),
                                new BorrowEvent(new Person("Ali"), new Date(28, 2, 1399), new Disc("pspice")),
                                new DeliveryEvent(new Person("Ali"), new Date(31, 2, 1399), new Disc("pspice")),
                                new DeliveryEvent(new Person("Taban"), new Date(4, 2, 1399), new Disc("ADS"))
                        ))
        );
    }

    @ParameterizedTest
    @MethodSource("generateEvents")
    public void givenSomeEvents_WhenGetEventsCalls_ThenTheseEventsAreAddedA(int numOfEvents, long penalty, String events, List<SoftwareBankEvent> bankEvents) {
        ByteArrayInputStream bais = new ByteArrayInputStream(events.getBytes());
        System.setIn(bais);
        Scanner scanner = new Scanner(System.in);
        softwareBankSys.setBankService(new BankService(numOfEvents, penalty));
        softwareBankSys.getEvents(numOfEvents, scanner);
        assertEquals(bankEvents, softwareBankSys.getBankService().getEvents());
    }
}
