package faye;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interface interactions for the Faye application.
 *
 * <p>This class manages input/output operations, displaying messages and
 * reading user commands from the console.</p>
 */
public class Ui {

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
        System.out.println(" Yo Wassup my G! Yo friend Faye right here");
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showBye() {
        System.out.println(" Im outta here. Peace!");
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
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message confirming a task was added.
     *
     * @param task The task that was added.
     * @param size The new total number of tasks.
     */
    public void showAdd(Task task, int size) {
        System.out.println(" Added:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks.");
    }

    /**
     * Displays a message confirming a task was deleted.
     *
     * @param task The task that was deleted.
     * @param size The new total number of tasks.
     */
    public void showDelete(Task task, int size) {
        System.out.println(" Aight. Removed:");
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
}
