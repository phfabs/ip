package faye;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TaskList} search and state updates.
 */
public class TaskListTest {

    @Test
    public void find_keywordMatchesDescription_returnsOnlyMatchingTasks() {
        TaskList list = new TaskList();
        list.add(new Todo("read book"));
        list.add(new Todo("buy milk"));
        list.add(new Todo("return book"));

        ArrayList<Task> found = list.find("book");

        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(t -> t.getTask().equals("read book")));
        assertTrue(found.stream().anyMatch(t -> t.getTask().equals("return book")));
    }

    @Test
    public void mark_validIndex_taskBecomesDone() {
        TaskList list = new TaskList();
        list.add(new Todo("submit ip"));

        list.mark(0);

        assertTrue(list.get(0).isDone());
    }
}
