package faye;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String deadlineTask, LocalDateTime by) {
        super(deadlineTask);
        this.by = by;
    }

    public Deadline(String task, boolean isDone, LocalDateTime by) {
        super(task, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        String formatted = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + formatted + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getTask() + " | " + by;
    }
    
}
