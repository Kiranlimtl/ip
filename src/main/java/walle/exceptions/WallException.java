package walle.exceptions;

/**
 * Exception thrown when a WallE is not valid.
 */
public class WallException extends Exception {
    /**
     * Constructor for WallException.
     * @param message The message to be displayed.
     */
    public WallException(String message) {
        super(message);
    }
}

