package faye;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that must be completed by a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new deadline task (initially not done).
     *
     * @param deadlineTask Description of the deadline task.
     * @param by Due date/time.
     */
    public Deadline(String deadlineTask, LocalDateTime by) {
        super(deadlineTask);
        this.by = by;
    }

    /**
     * Creates a deadline task with an explicit completion status.
     *
     * @param task Description of the deadline task.
     * @param isDone Whether the task is completed.
     * @param by Due date/time.
     */
    public Deadline(String task, boolean isDone, LocalDateTime by) {
        super(task, isDone);
        this.by = by;
    }

    /**
     * Returns a human-readable representation including the due date/time.
     *
     * @return String form prefixed with {@code [D]} and including
     *         {@code (by: ...)}.
     */
    @Override
    public String toString() {
        String formatted = by.format(DateTimeFormatter.ofPattern(
                "MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + formatted + ")";
    }

    /**
     * Returns a storage-friendly representation of this deadline.
     *
     * @return Encoded storage string for this deadline.
     */
    @Override
    public String toStorageString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getTask() + " | " + by;
    }
    
}
