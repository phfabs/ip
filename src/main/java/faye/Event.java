package faye;

/**
 * A task representing an event with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new event task (initially not done).
     *
     * @param description Description of the event.
     * @param from Start time or start descriptor.
     * @param to End time or end descriptor.
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null : "Event 'from' must not be null";
        assert to != null : "Event 'to' must not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event task with an explicit completion status.
     *
     * @param description Description of the event.
     * @param isDone Whether the event is completed.
     * @param from Start time or start descriptor.
     * @param to End time or end descriptor.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        assert from != null : "Event 'from' must not be null";
        assert to != null : "Event 'to' must not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a human-readable representation including the event time range.
     *
     * @return String form prefixed with {@code [E]} and including
     *         {@code (from: ... to: ...)}.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to
                + ")";
    }

    /**
     * Returns a storage-friendly representation of this event.
     *
     * @return Encoded storage string for this event.
     */
    @Override
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + task + " | " + from + " | " + to;
    }
}
