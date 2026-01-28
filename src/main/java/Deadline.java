public class Deadline extends Task{
    protected String by;

    public Deadline(String deadlineTask, String by) {
        super(deadlineTask);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
