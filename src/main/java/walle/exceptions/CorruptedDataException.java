package walle.exceptions;
/**
 * Exception for Storage class when data is corrupted.
 */
public class CorruptedDataException extends Exception {
    /**
     * Constructor for CorruptedDataException.
     * @param message The message to be displayed.
     */
    public CorruptedDataException(String message) {
        super("Corrupted task data: " + message);
    }
}
