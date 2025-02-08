package walle.exceptions;
/**
 * Exception for Storage class when data is corrupted.
 */
public class CorruptedDataException extends Exception {
    public CorruptedDataException(String message) {
        super("Corrupted task data: " + message);
    }
}