package faye;

/**
 * A simple task with only a description.
 */
public class Todo extends Task {
    /**
     * Creates a new todo task (initially not done).
     *
     * @param todoTask Description of the todo.
     */
    public Todo(String todoTask) {
        super(todoTask);
    }

    /**
     * Creates a todo task with an explicit completion status.
     *
     * @param task Description of the todo.
     * @param isDone Whether the todo is completed.
     */
    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Returns a human-readable representation with todo type marker.
     *
     * @return String form prefixed with {@code [T]}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a storage-friendly representation of this todo.
     *
     * @return Encoded storage string for this todo.
     */
    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + task;
    }
}
