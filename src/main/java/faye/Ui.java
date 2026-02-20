package faye;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Handles user interface interactions for the Faye application.
 *
 * <p>This class manages input/output operations, displaying messages and
 * reading user commands from the console.</p>
 */
public class Ui {

    private static final String WELCOME_MESSAGE = " Hi, I'm Hal.";
    private static final String BYE_MESSAGE = " Daisy, Daisy,\n"
            + "Give me your answer do.\n"
            + "I'm half crazy,\n"
            + "All for the love of you. \n"
            + "Goodbye.";

    private Scanner scanner;

    /**
     * Initializes the UI with a scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showBye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line (currently empty).
     */
    public void showLine() {
    }

    /**
     * Displays all tasks in the task list with their indices.
     *
     * @param tasks List of tasks to display.
     */
    public void showTasks(ArrayList<Task> tasks) {
        IntStream.range(0, tasks.size())
                .forEach(i -> System.out.println((i + 1) + ". " + tasks.get(i)));
    }

    /**
     * Displays a message confirming a task was added.
     *
     * @param task The task that was added.
     * @param size The new total number of tasks.
     */
    public void showAdd(Task task, int size) {
        showTaskUpdate(" Added:", task, size);
    }

    /**
     * Displays a message confirming a task was deleted.
     *
     * @param task The task that was deleted.
     * @param size The new total number of tasks.
     */
    public void showDelete(Task task, int size) {
        showTaskUpdate(" Aight. Removed:", task, size);
    }

    /**
     * Displays a task update message with the given action and remaining count.
     *
     * @param actionMessage The action description (e.g. " Added:" or " Aight. Removed:").
     * @param task The task involved.
     * @param size The new total number of tasks.
     */
    private void showTaskUpdate(String actionMessage, Task task, int size) {
        System.out.println(actionMessage);
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks.");
    }

    /**
     * Displays a message confirming a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMark(Task task) {
        System.out.println(" Marked!");
        System.out.println("   " + task);
    }

    /**
     * Displays a message confirming a task was unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Returns the welcome message (for GUI use).
     *
     * @return The welcome message string.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns the formatted bye message (for GUI use).
     *
     * @return The bye message string.
     */
    public String getByeMessage() {
        return BYE_MESSAGE;
    }

    /**
     * Returns the formatted task list as a string (for GUI use).
     *
     * @param tasks List of tasks to format.
     * @return Formatted task list string.
     */
    public String getTasksMessage(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns the add confirmation message (for GUI use).
     *
     * @param task The task that was added.
     * @param size The new total number of tasks.
     * @return Formatted add message string.
     */
    public String getAddMessage(Task task, int size) {
        return " Added:\n   " + task + "\n Now you have " + size + " tasks.";
    }

    /**
     * Returns the delete confirmation message (for GUI use).
     *
     * @param task The task that was deleted.
     * @param size The new total number of tasks.
     * @return Formatted delete message string.
     */
    public String getDeleteMessage(Task task, int size) {
        return " Aight. Removed:\n   " + task + "\n Now you have " + size
                + " tasks.";
    }

    /**
     * Returns the mark confirmation message (for GUI use).
     *
     * @param task The task that was marked.
     * @return Formatted mark message string.
     */
    public String getMarkMessage(Task task) {
        return " Marked!\n   " + task;
    }

    /**
     * Returns the unmark confirmation message (for GUI use).
     *
     * @param task The task that was unmarked.
     * @return Formatted unmark message string.
     */
    public String getUnmarkMessage(Task task) {
        return " OK, I've marked this task as not done yet:\n   " + task;
    }
}
