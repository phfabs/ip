import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String deadlineTask, LocalDate by) {
        super(deadlineTask);
        this.by = by;
    }

    public Deadline(String task, boolean isDone, LocalDate by) {
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
