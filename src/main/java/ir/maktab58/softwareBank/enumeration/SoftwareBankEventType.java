package ir.maktab58.softwareBank.enumeration;

/**
 * @author Taban Soleymani
 */
public enum SoftwareBankEventType {
    BORROW("borrow"),
    DELIVER("deliver"),
    NOT_SET("not-set");

    private final String eventType;

    SoftwareBankEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
