package faye;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addAndRemoveTasks() {
        TaskList taskList = new TaskList();

        Todo todo = new Todo("Write tests");
        taskList.add(todo);
        assertEquals(1, taskList.size(), "TaskList should have 1 task after adding");

        Task removed = taskList.remove(0);
        assertEquals(todo, removed, "Removed task should be the same as added task");
        assertEquals(0, taskList.size(), "TaskList should be empty after removing task");
    }

    @Test
    public void markTaskInTaskList() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Write tests");
        taskList.add(todo);

        taskList.mark(0);
        assertTrue(taskList.get(0).isDone(), "Task should be marked as done");
    }
}
