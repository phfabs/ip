package faye;
/**
 * Represents a single task in the task list.
 *
 * <p>A task has a description and a completion status. Specific task types
 * (e.g. Todo, Deadline, Event) extend this base class.</p>
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Creates a new task that is initially not done.
     *
     * @param task Description of the task.
     */
    public Task (String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Creates a task with an explicit completion status.
     *
     * <p>Used when loading tasks from storage.</p>
     *
     * @param task Description of the task.
     * @param isDone Whether the task is completed.
     */
    public Task(String task, boolean isDone) { // constructor 2 for loading file
        this.task = task;
        this.isDone = isDone;
    } 


    /**
     * Returns the description text for this task.
     *
     * @return Task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns whether this task is marked done.
     *
     * @return True if done; false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
 
    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a human-readable representation of the task.
     *
     * @return String form including completion marker.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }

    /**
     * Returns a storage-friendly representation of the task.
     *
     * <p>Subclasses typically override this to include their specific fields.</p>
     *
     * @return Encoded storage string for this task.
     */
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + task;
    }

}
