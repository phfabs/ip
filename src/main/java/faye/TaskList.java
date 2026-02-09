package faye;

import java.util.ArrayList;

/**
 * Manages a list of {@link Task} objects.
 *
 * <p>This class provides basic list operations used by the application such as
 * add/remove, mark/unmark, and retrieving tasks.</p>
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list backed by an existing list.
     *
     * <p>Used when loading tasks from storage.</p>
     *
     * @param tasks Existing tasks to use as the internal list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index 0-based index.
     * @return Removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index 0-based index.
     * @return Task at the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return Task count.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * <p>Callers should be careful when mutating the returned list directly.</p>
     *
     * @return Backing {@link ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index 0-based index.
     */
    public void mark(int index) {
        tasks.get(index).mark();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index 0-based index.
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }
    

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getTask().contains(keyword)) {
            matches.add(task);
            }
        }

        return matches;
    }
}
