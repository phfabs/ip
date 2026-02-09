package faye;

/**
 * Exception thrown when a task command is provided without a description.
 */
public class EmptyTaskInputException extends Exception {
    /**
     * Creates a new EmptyTaskInputException with the specified message.
     *
     * @param message Error message describing the exception.
     */
    public EmptyTaskInputException(String message) {
        super(message);
    }
}
