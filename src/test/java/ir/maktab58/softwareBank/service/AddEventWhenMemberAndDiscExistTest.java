package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import ir.maktab58.softwareBank.models.eventsfactory.BorrowEvent;
import ir.maktab58.softwareBank.models.eventsfactory.DeliveryEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Taban Soleymani
 */
public class AddEventWhenMemberAndDiscExistTest {
    @BeforeAll
    public static void init() {
        System.out.println("In AddEventWhenMemberAndDiscExistTest init...");
    }

    @AfterAll
    public static void after() {
        System.out.println("In AddEventWhenMemberAndDiscExistTest after...");
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
    BankService bankService = new BankService(7, 600);

    static Stream<Arguments> generateExistedMembersAndExistedDiscsForDelivery() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("hassan"), new Disc("pspice"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateExistedMembersAndExistedDiscsForDelivery")
    public void givenExistedMemberAndExistedDisc_WhenAddEventForExistedMemberAndDiscCalls_ThenDeliveryEventIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        bankService.addEventWhenMemberAndDiscExist(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new DeliveryEvent(member, dateOfEvent, disc)));
    }

    static Stream<Arguments> generateExistedMembersAndExistedDiscsForBorrowing() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        discs.forEach(disc -> disc.setBorrowed(false));
        return Stream.of(
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("hassan"), discs.get(0))
        );
    }

    @ParameterizedTest
    @MethodSource("generateExistedMembersAndExistedDiscsForBorrowing")
    public void givenExistedMemberAndExistedDisc_WhenAddEventForExistedMemberAndDiscCalls_ThenBorrowEventIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        bankService.addEventWhenMemberAndDiscExist(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
    }
}
