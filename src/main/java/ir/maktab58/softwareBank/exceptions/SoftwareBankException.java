package ir.maktab58.softwareBank.exceptions;

/**
 * @author Taban Soleymani
 */
public class SoftwareBankException extends RuntimeException {
    int errorCode;

    public SoftwareBankException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
