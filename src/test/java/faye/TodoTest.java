package faye;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void markAndUnmarkTodo() {
        Todo todo = new Todo("Write tests");

        assertFalse(todo.isDone(), "Todo should not be marked initially");

        todo.mark();
        assertTrue(todo.isDone(), "Todo should be marked as done after mark()");

        todo.unmark();
        assertFalse(todo.isDone(), "Todo should not be done after unmark()");
    }

    @Test
    public void todoToStringTest() {
        Todo todo = new Todo("Write tests");
        // Check string representation
        assertEquals("[T][ ] Write tests", todo.toString());

        todo.mark();
        assertEquals("[T][X] Write tests", todo.toString());
    }
}