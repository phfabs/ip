package faye;

/**
 * Exception thrown when an invalid task number is provided in a command.
 */
public class InvalidTaskNumberException extends Exception {
    /**
     * Creates a new InvalidTaskNumberException with the specified message.
     *
     * @param message Error message describing the exception.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
