package ir.maktab58.softwareBank.models.eventsfactory;

import ir.maktab58.softwareBank.enumeration.SoftwareBankEventType;
import ir.maktab58.softwareBank.exceptions.IllegalEventSateEx;
import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;

/**
 * @author Taban Soleymani
 */
public class SBEventFactory {
    public static SoftwareBankEvent getEvent(String type, Person member, Date dateOfEvent, Disc software) {
        SoftwareBankEvent softwareBankEvent;
        if (type.equals(SoftwareBankEventType.BORROW.getEventType()))
            softwareBankEvent = new BorrowEvent(member, dateOfEvent, software);
        else if (type.equals(SoftwareBankEventType.DELIVER.getEventType()))
            softwareBankEvent = new DeliveryEvent(member, dateOfEvent, software);
        else
            throw new IllegalEventSateEx("Invalid event. Just borrowEvent and deliveryEvent are acceptable.", 400);
        return softwareBankEvent;
    }
}
