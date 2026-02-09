package faye;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main application class for the Faye task management system.
 *
 * <p>This class orchestrates the interaction between the UI, storage, and task list
 * components to provide a command-line task management interface.</p>
 */
public class Faye {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes a new Faye application instance.
     *
     * <p>Sets up the UI, storage, and loads existing tasks from the data file.</p>
     */
    public Faye() {
        ui = new Ui();
        storage = new Storage("./data/faye.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main application loop.
     *
     * <p>Processes user commands until the "bye" command is received.
     * Handles all command types including list, mark, unmark, delete, todo,
     * deadline, and event.</p>
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            String command = Parser.getCommand(input);

            try {
                switch (command) {
                    case "bye":
                        ui.showBye();
                        return;

                    case "list":
                        ui.showLine();
                        ui.showTasks(tasks.getTasks());
                        ui.showLine();
                        break;

                    case "mark": {
                        int index = Parser.getIndex(input);
                        tasks.mark(index);
                        storage.save(tasks.getTasks());
                        ui.showMark(tasks.get(index));
                        break;
                    }

                    case "unmark": {
                        int index = Parser.getIndex(input);
                        tasks.unmark(index);
                        storage.save(tasks.getTasks());
                        ui.showUnmark(tasks.get(index));
                        break;
                    }

                    case "delete": {
                        int index = Parser.getIndex(input);
                        Task removed = tasks.remove(index);
                        storage.save(tasks.getTasks());
                        ui.showDelete(removed, tasks.size());
                        break;
                    }

                    case "todo": {
                        String desc = Parser.getDescription(input);
                        if (desc.isEmpty()) {
                            throw new EmptyTaskInputException(
                                    "Todo cannot be empty.");
                        }
                        Task t = new Todo(desc);
                        tasks.add(t);
                        storage.save(tasks.getTasks());
                        ui.showAdd(t, tasks.size());
                        break;
                    }

                    case "deadline": {
                        String[] parts = Parser.splitDeadline(input);
                        String desc = parts[0].trim();
                        LocalDateTime by = LocalDateTime.parse(parts[1].trim(),
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HHmm"));
                        Task t = new Deadline(desc, by);
                        tasks.add(t);
                        storage.save(tasks.getTasks());
                        ui.showAdd(t, tasks.size());
                        break;
                    }

                    case "event": {
                        String[] parts = Parser.splitEvent(input);
                        Task t = new Event(parts[0].trim(), parts[1].trim(),
                                parts[2].trim());
                        tasks.add(t);
                        storage.save(tasks.getTasks());
                        ui.showAdd(t, tasks.size());
                        break;
                    }

                    default:
                        ui.showError("Unknown command.");
                        break;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }

            ui.showLine();
        }
    }

    /**
     * Entry point for the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Faye().run();
    }
}
