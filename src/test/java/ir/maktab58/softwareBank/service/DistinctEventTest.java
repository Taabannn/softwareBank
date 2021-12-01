package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import ir.maktab58.softwareBank.models.eventsfactory.BorrowEvent;
import ir.maktab58.softwareBank.models.eventsfactory.DeliveryEvent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Taban Soleymani
 */
public class DistinctEventTest {
    @Mock
    BankService bankService = new BankService(6, 1000);

    static Stream<Arguments> generateNotExistedMembersAndDiscs() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("Taban"), new Disc("ubuntu")),
                Arguments.of(members, discs, new Date(16, 5, 97), new Person("Maryam"), new Disc("CLine")),
                Arguments.of(members, discs, new Date(17, 8, 98), new Person("parham"), new Disc("SQLite"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateNotExistedMembersAndDiscs")
    public void givenNotExistedMemberAndDisc_WhenDistinctEventCalls_ThenMemberAndDiscAreAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        bankService.distinctEvent(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
        assertTrue(bankService.getMembers().contains(member));
        assertTrue(bankService.getDiscs().contains(disc));
    }

    static Stream<Arguments> generateExistedMembersAndNotExistedDiscs() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("hassan"), new Disc("ubuntu")),
                Arguments.of(members, discs, new Date(11, 5, 86), new Person("Ali"), new Disc("comsol")),
                Arguments.of(members, discs, new Date(1, 3, 91), new Person("mj"), new Disc("Intellij"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateExistedMembersAndNotExistedDiscs")
    public void givenExistedMemberAndNotExistedDisc_WhenDistinctEventCalls_ThenOnlyDiscIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        int memberIndex = bankService.getMembers().indexOf(member);
        bankService.distinctEvent(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
        assertEquals(memberIndex, bankService.getMembers().indexOf(member));
        assertTrue(bankService.getDiscs().contains(disc));
    }

    static Stream<Arguments> generateNotExistedMembersAndExistedDiscs() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("Taban"), new Disc("matlab")),
                Arguments.of(members, discs, new Date(23, 6, 95), new Person("Maryam"), new Disc("pspice"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateNotExistedMembersAndExistedDiscs")
    public void givenNotExistedMemberAndExistedDisc_WhenDistinctEventCalls_ThenOnlyMemberIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        int discIndex = bankService.getDiscs().indexOf(disc);
        bankService.distinctEvent(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
        assertEquals(discIndex, bankService.getDiscs().indexOf(disc));
        assertTrue(bankService.getMembers().contains(member));
    }

    static Stream<Arguments> generateExistedMembersAndExistedDiscs() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("hassan"), new Disc("matlab")),
                Arguments.of(members, discs, new Date(23, 6, 95), new Person("Ali"), new Disc("pspice"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateExistedMembersAndExistedDiscs")
    public void givenExistedMemberAndExistedDisc_WhenDistinctEventCalls_ThenDeliveryEventIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        bankService.distinctEvent(dateOfEvent, member, disc);
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
                Arguments.of(members, discs, new Date(12, 12, 99), new Person("hassan"), new Disc("matlab")),
                Arguments.of(members, discs, new Date(23, 6, 95), new Person("Ali"), new Disc("pspice"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateExistedMembersAndExistedDiscsForBorrowing")
    public void givenExistedMemberAndExistedDisc_WhenDistinctEventCalls_ThenBorrowEventIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        bankService.distinctEvent(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
    }
}
