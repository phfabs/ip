package faye;
public class Task {
    protected String task;
    protected boolean isDone;

    public Task (String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) { // constructor 2 for loading file
        this.task = task;
        this.isDone = isDone;
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

    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + task;
    }

}
