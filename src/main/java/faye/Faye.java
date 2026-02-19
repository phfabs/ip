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
    private static final String DATA_FILE_PATH = "./data/faye.txt";
    private static final String DEADLINE_DATE_FORMAT = "yyyy-MM-dd HHmm";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_ALIAS = "alias";

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
        storage = new Storage(DATA_FILE_PATH);
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
                if (handleCommand(command, input)) {
                    return;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }

            ui.showLine();
        }
    }

    /**
     * Handles a single command. Returns true if the application should exit.
     *
     * @param command The command token.
     * @param input The full user input.
     * @return True if the application should exit (bye command); false otherwise.
     * @throws Exception If command execution fails (e.g. invalid input).
     */
    private boolean handleCommand(String command, String input) throws Exception {
        switch (command) {
        case COMMAND_BYE:
            ui.showBye();
            return true;

        case COMMAND_LIST:
            handleList();
            return false;

        case COMMAND_MARK:
            handleMark(input);
            return false;

        case COMMAND_UNMARK:
            handleUnmark(input);
            return false;

        case COMMAND_DELETE:
            handleDelete(input);
            return false;

        case COMMAND_TODO:
            handleTodo(input);
            return false;

        case COMMAND_DEADLINE:
            handleDeadline(input);
            return false;

        case COMMAND_EVENT:
            handleEvent(input);
            return false;

        case COMMAND_FIND:
            handleFind(input);
            return false;

        case COMMAND_ALIAS:
            handleAlias(input);
            return false;

        default:
            ui.showError("Unknown command.");
            return false;
        }
    }

    private void handleList() {
        ui.showLine();
        ui.showTasks(tasks.getTasks());
        ui.showLine();
    }

    private void handleMark(String input) {
        int index = Parser.getIndex(input);
        tasks.mark(index);
        saveTasks();
        ui.showMark(tasks.get(index));
    }

    private void handleUnmark(String input) {
        int index = Parser.getIndex(input);
        tasks.unmark(index);
        saveTasks();
        ui.showUnmark(tasks.get(index));
    }

    private void handleDelete(String input) {
        int index = Parser.getIndex(input);
        Task removed = tasks.remove(index);
        saveTasks();
        ui.showDelete(removed, tasks.size());
    }

    private void handleTodo(String input) throws EmptyTaskInputException {
        String description = Parser.getDescription(input);
        if (description.isEmpty()) {
            throw new EmptyTaskInputException("Todo cannot be empty.");
        }
        Task newTask = new Todo(description);
        addTaskAndNotify(newTask);
    }

    private void handleDeadline(String input) {
        String[] deadlineParts = Parser.splitDeadline(input);
        String description = deadlineParts[0].trim();
        LocalDateTime by = LocalDateTime.parse(deadlineParts[1].trim(),
                DateTimeFormatter.ofPattern(DEADLINE_DATE_FORMAT));
        Task newTask = new Deadline(description, by);
        addTaskAndNotify(newTask);
    }

    private void handleEvent(String input) {
        String[] eventParts = Parser.splitEvent(input);
        Task newTask = new Event(eventParts[0].trim(), eventParts[1].trim(),
                eventParts[2].trim());
        addTaskAndNotify(newTask);
    }

    private void handleFind(String input) {
        String keyword = Parser.getDescription(input);
        TaskList foundTasks = new TaskList(tasks.find(keyword));
        ui.showTasks(foundTasks.getTasks());
    }

    private void handleAlias(String input) {
        String description = Parser.getDescription(input);
        if (description.isEmpty()) {
            ui.showError("Usage: alias <alias> <command>");
            return;
        }
        String[] parts = description.split(" ");
        if (parts.length < 2) {
            ui.showError("Usage: alias <alias> <command>");
            return;
        }
        String alias = parts[0];
        String targetCommand = parts[1];
        // Resolve target to its canonical command to avoid chaining aliases
        String canonicalCommand = AliasManager.getInstance()
                .resolve(targetCommand);
        AliasManager.getInstance().addAlias(alias, canonicalCommand);
        ui.showError("Alias added: " + alias + " -> " + canonicalCommand);
    }

    private void saveTasks() {
        storage.save(tasks.getTasks());
    }

    private void addTaskAndNotify(Task task) {
        tasks.add(task);
        saveTasks();
        ui.showAdd(task, tasks.size());
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input message.
     * @return The response string.
     */
    public String getResponse(String input) {
        return "Hal heard: " + input;
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
