package faye;

/**
 * Parses raw user input into structured command data.
 *
 * <p>This class contains static utility methods for extracting the command word,
 * index, and task descriptions from a full input line.</p>
 */
public class Parser {
    private static final String SPACE_DELIMITER = " ";
    private static final String DEADLINE_SPLITTER = "/by";
    private static final String EVENT_SPLITTER = "/from|/to";

    /**
     * Extracts the first word of the input as the command.
     *
     * @param input Full user input line.
     * @return Command token (first word).
     */
    public static String getCommand(String input) {
        return input.split(SPACE_DELIMITER)[0];
    }

    /**
     * Extracts a 0-based task index from commands like {@code mark 2}.
     *
     * @param input Full user input line.
     * @return 0-based index of the task referenced by the command.
     * @throws NumberFormatException if the index token is not a number.
     * @throws ArrayIndexOutOfBoundsException if the input does not contain
     *         an index token.
     */
    public static int getIndex(String input) {
        return Integer.parseInt(input.split(SPACE_DELIMITER)[1]) - 1;
    }

    /**
     * Returns the text after the first space as the task description.
     *
     * @param input Full user input line.
     * @return Description text, or empty string if no description is present.
     */
    public static String getDescription(String input) {
        int firstSpace = input.indexOf(SPACE_DELIMITER);
        if (firstSpace == -1) return "";
        return input.substring(firstSpace + 1).trim();
    }

    /**
     * Splits a deadline command description using {@code /by}.
     *
     * @param input Full user input line.
     * @return Array of parts split by {@code /by}.
     */
    public static String[] splitDeadline(String input) {
        return getDescription(input).split(DEADLINE_SPLITTER);
    }

    /**
     * Splits an event command description using {@code /from} and {@code /to}.
     *
     * @param input Full user input line.
     * @return Array of parts split by {@code /from} or {@code /to}.
     */
    public static String[] splitEvent(String input) {
        return getDescription(input).split(EVENT_SPLITTER);
    }
}
