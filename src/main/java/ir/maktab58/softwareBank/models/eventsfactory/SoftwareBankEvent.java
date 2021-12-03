package ir.maktab58.softwareBank.models.eventsfactory;

import ir.maktab58.softwareBank.models.Date;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.models.Person;
import lombok.*;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class SoftwareBankEvent {
    private @Getter @Setter Person member;
    private @Getter @Setter Date dateOfEvent;
    private @Getter @Setter Disc software;
}
