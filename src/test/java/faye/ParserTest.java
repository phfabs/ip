package faye;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Parser} index and description extraction.
 */
public class ParserTest {

    @Test
    public void getIndex_oneBasedInput_returnsZeroBasedIndex() {
        assertEquals(0, Parser.getIndex("mark 1"));
        assertEquals(4, Parser.getIndex("delete 5"));
    }

    @Test
    public void getIndex_nonNumericToken_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Parser.getIndex("mark abc"));
    }
}
