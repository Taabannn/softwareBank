package ir.maktab58.softwareBank.models.eventsfactory;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;

/**
 * @author Taban Soleymani
 */
public class DeliveryEvent extends SoftwareBankEvent {
    public DeliveryEvent(Person member, Date dateOfEvent, Disc software) {
        super(member, dateOfEvent, software);
    }
}
