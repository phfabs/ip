public class Task {
    private String task;
    private boolean isDone;

    public Task (String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isDone() {
        return this.isDone;
    }
 
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }

}
