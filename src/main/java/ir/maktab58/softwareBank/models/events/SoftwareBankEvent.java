package ir.maktab58.softwareBank.models.events;

import ir.maktab58.softwareBank.enumeration.SoftwareBankEventType;
import ir.maktab58.softwareBank.models.Date;

/**
 * @author Taban Soleymani
 */
public abstract class SoftwareBankEvent {
    private SoftwareBankEventType eventType;
    private String memberName;
    private Date dateOfEvent;
    private String softwareName;
}
