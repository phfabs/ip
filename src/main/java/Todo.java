public class Todo extends Task {
    public Todo(String todoTask) {
        super(todoTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
