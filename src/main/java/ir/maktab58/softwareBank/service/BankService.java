package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.Borrow;
import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import ir.maktab58.softwareBank.models.eventsfactory.SBEventFactory;
import ir.maktab58.softwareBank.models.eventsfactory.SoftwareBankEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class BankService {
    private final @Getter long penalty;
    private final @Getter int numOfEvents;
    private @Getter List<SoftwareBankEvent> events = new ArrayList<>();
    private @Getter List<Person> members = new ArrayList<>();
    private @Getter List<Disc> discs = new ArrayList<>();

    public BankService(int numOfEvents, long penalty) {
        this.numOfEvents = numOfEvents;
        this.penalty = penalty;
    }

    public void extractEvents(List<String[]> eventsStr) {
        for (String[] event : eventsStr) {
            int day = Integer.parseInt(event[0]);
            int month = Integer.parseInt(event[1]);
            int year = 1300 + Integer.parseInt(event[2]);
            Date dateOfEvent = new Date(day, month, year);
            Person member = new Person(event[3]);
            Disc disc = new Disc(event[4]);
            distinctEvent(dateOfEvent, member, disc);
        }
    }

    private void distinctEvent(Date dateOfEvent, Person member, Disc disc) {
        if (!members.contains(member) && !discs.contains(disc)) {
            addEventMemberAndDiscNotExisted(dateOfEvent, member, disc);
            return;
        }
        if (members.contains(member) && !discs.contains(disc)) {
            addEventWhenDiscNotExisted(dateOfEvent, member, disc);
            return;
        }
        if (!members.contains(member) && discs.contains(disc)) {
            addEventWhenMemberNotExisted(dateOfEvent, member, disc);
            return;
        }
        if (members.contains(member) && discs.contains(disc)) {
            addEventWhenMemberAndDiscExist(dateOfEvent, member, disc);
        }
    }

    private void addEventWhenMemberAndDiscExist(Date dateOfEvent, Person member, Disc disc) {
        int discIndex = discs.indexOf(disc);
        if (discs.get(discIndex).isBorrowed()) {
            events.add(SBEventFactory.getEvent("deliver", member, dateOfEvent, disc));
            int index = members.indexOf(member);
            members.get(index).deliver(disc, dateOfEvent);
            discs.get(discIndex).setBorrowed(false);
        } else {
            events.add(SBEventFactory.getEvent("borrow", member, dateOfEvent, disc));
            int index = members.indexOf(member);
            members.get(index).borrow(disc, dateOfEvent);
        }
    }

    private void addEventWhenMemberNotExisted(Date dateOfEvent, Person member, Disc disc) {
        events.add(SBEventFactory.getEvent("borrow", member, dateOfEvent, disc));
        members.add(member);
        member.borrow(disc, dateOfEvent);
        int index = discs.indexOf(disc);
        discs.get(index).setBorrowed(true);
    }

    private void addEventWhenDiscNotExisted(Date dateOfEvent, Person member, Disc disc) {
        events.add(SBEventFactory.getEvent("borrow", member, dateOfEvent, disc));
        int index = members.indexOf(member);
        members.get(index).borrow(disc, dateOfEvent);
        discs.add(disc);
    }

    private void addEventMemberAndDiscNotExisted(Date dateOfEvent, Person member, Disc disc) {
        events.add(SBEventFactory.getEvent("borrow", member, dateOfEvent, disc));
        members.add(member);
        member.borrow(disc, dateOfEvent);
        discs.add(disc);
    }

    public void calculateFines() {
        members.forEach(member -> member.calculateFine(penalty));
    }
}