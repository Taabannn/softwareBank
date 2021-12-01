package ir.maktab58.softwareBank.exceptions;

/**
 * @author Taban Soleymani
 */
public class IllegalEventSateEx extends SoftwareBankException {
    public IllegalEventSateEx(String message, int errorCode) {
        super(message, errorCode);
    }
}
