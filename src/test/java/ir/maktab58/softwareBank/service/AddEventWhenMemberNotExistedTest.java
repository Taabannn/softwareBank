package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import ir.maktab58.softwareBank.models.eventsfactory.BorrowEvent;
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
public class AddEventWhenMemberNotExistedTest {
    @Mock
    BankService bankService = new BankService(8, 900);

    static Stream<Arguments> generateMembersAndDiscs() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(8, 1, 86), new Person("Taban"), new Disc("matlab"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateMembersAndDiscs")
    public void givenNotExistedMember_WhenAddEventWhileMemberNotExistedCalls_ThenOnlyMemberIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        int discIndex = bankService.getDiscs().indexOf(disc);
        bankService.addEventWhenMemberNotExisted(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
        assertEquals(discIndex ,bankService.getDiscs().indexOf(disc));
        assertTrue(bankService.getMembers().contains(member));
    }
}
