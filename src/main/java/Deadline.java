public class Deadline extends Task{
    protected String by;

    public Deadline(String deadlineTask, String by) {
        super(deadlineTask);
        this.by = by;
    }

    public Deadline(String task, boolean isDone, String by) {
        super(task, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + task + " | " + by;
    }
}
