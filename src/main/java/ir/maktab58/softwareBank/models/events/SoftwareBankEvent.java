package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.enumeration.SoftwareBankEventType;
import ir.maktab58.softwareBank.models.Date;

/**
 * @author Taban Soleymani
 */
public class SoftwareBankEvent {
    private SoftwareBankEventType eventType;
    private String memberName;
    private Date dateOfEvent;
    private String softwareName;
}
