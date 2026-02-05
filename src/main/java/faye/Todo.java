package faye;
public class Todo extends Task {
    public Todo(String todoTask) {
        super(todoTask);
    }

    public Todo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + task;
    }
}
