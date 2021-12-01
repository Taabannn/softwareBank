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
public class AddEventWhenDiscNotExistedTest {
    @Mock
    BankService bankService = new BankService(4, 500);

    static Stream<Arguments> generateMembersAndDiscs() {
        List<Person> members = new ArrayList<>();
        members.add(new Person("mj"));
        members.add(new Person("Ali"));
        members.add(new Person("hassan"));
        List<Disc> discs = new ArrayList<>();
        discs.add(new Disc("matlab"));
        discs.add(new Disc("pspice"));
        return Stream.of(
                Arguments.of(members, discs, new Date(11, 10, 99), new Person("Ali"), new Disc("ubuntu"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateMembersAndDiscs")
    public void givenNotExistedDisc_WhenAddEventWhileDiscNotExistedCalls_ThenOnlyDiscIsAdded(List<Person> members, List<Disc> discs, Date dateOfEvent, Person member, Disc disc) {
        bankService.setMembers(members);
        bankService.setDiscs(discs);
        int memberIndex = bankService.getMembers().indexOf(member);
        bankService.addEventWhenDiscNotExisted(dateOfEvent, member, disc);
        assertTrue(bankService.getEvents().contains(new BorrowEvent(member, dateOfEvent, disc)));
        assertEquals(memberIndex ,bankService.getMembers().indexOf(member));
        assertTrue(bankService.getDiscs().contains(disc));
    }
}
