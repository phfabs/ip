# Faye User Guide

**Faye** is a task management chatbot that helps you keep track of todos, deadlines, and events. It works both as a command-line (CLI) app and with a graphical user interface (GUI). If you type fast, the CLI lets you manage tasks quickly; the GUI offers a friendly chat-style interface.

---

## Acknowledgements

This project was developed with assistance from AI tools.
Cursor was used to:
- Clarify design decisions
- Explain JavaFX integration
- Suggest refactoring improvements
- Review documentation structure

All code was reviewed and modified by the author to ensure correctness and understanding.

---

## Quick start

1. Ensure you have **Java 17** or above installed.
2. Download the latest `faye.jar` from the [releases page](../../releases).
3. Copy the JAR into the folder you want to use.
4. Run `java -jar faye.jar`. A GUI window will appear.
5. Type a command in the text box and click **Send** (or press Enter). Commands work the same in both GUI and CLI.

Some example commands you can try:

- `list` — Lists all tasks
- `todo buy milk` — Adds a todo
- `mark 1` — Marks the 1st task as done
- `bye` — Exits the app

Refer to [Features](#features) for details of each command.

---

## Features

### :information_source: Notes about command format

- Words in `UPPER_CASE` are parameters you supply.
- Items in square brackets `[ ]` are optional.
- `INDEX` is the 1-based task number from the list (e.g. 1, 2, 3…).
- You can use short aliases instead of full command names (see [Built-in aliases](#built-in-aliases)).

---

### Listing all tasks: `list`

Shows all tasks with their status.

**Format:** `list`

**Example:** `list` or `l`

---

### Adding a todo: `todo`

Adds a simple task with a description.

**Format:** `todo DESCRIPTION`

**Example:** `todo buy groceries` or `t read book`

---

### Adding a deadline: `deadline`

Adds a task with a due date and time.

**Format:** `deadline DESCRIPTION /by yyyy-MM-dd HHmm`

**Example:** `deadline return book /by 2025-12-25 1430` or `ddl submit report /by 2025-02-10 1700`

---

### Adding an event: `event`

Adds a task with a start and end time/description.

**Format:** `event DESCRIPTION /from START /to END`

**Example:** `event team meeting /from Mon 2pm /to Mon 3pm` or `e project review /from 2025-02-15 /to 2025-02-15`

---

### Marking a task as done: `mark`

Marks the task at the given index as completed.

**Format:** `mark INDEX`

**Example:** `mark 1` or `m 2`

---

### Unmarking a task: `unmark`

Marks the task at the given index as not done.

**Format:** `unmark INDEX`

**Example:** `unmark 1` or `u 2`

---

### Deleting a task: `delete`

Removes the task at the given index from the list.

**Format:** `delete INDEX`

**Example:** `delete 3` or `d 1`

---

### Finding tasks: `find`

Shows tasks whose description contains the keyword.

**Format:** `find KEYWORD`

**Example:** `find book` or `f meeting`

---

### Defining custom aliases: `alias`

Adds a custom shortcut for a command. The alias is saved and reused in future sessions.

**Format:** `alias ALIAS COMMAND`

**Example:** `alias hw todo` — then `hw read chapter 3` adds a todo “read chapter 3”.

---

### Exiting the program: `bye`

Exits Faye.

**Format:** `bye`

**Example:** `bye` or `b`

---

### Built-in aliases

You can use these short forms instead of full commands:

| Alias | Command |
|-------|---------|
| `t`   | `todo`  |
| `ddl` | `deadline` |
| `e`   | `event` |
| `m`   | `mark`  |
| `u`   | `unmark` |
| `d`   | `delete` |
| `l`   | `list`  |
| `f`   | `find`  |
| `b`   | `bye`   |

---

## Saving the data

Tasks are saved automatically after any change. No manual save is needed. Data is stored in `./data/faye.txt` relative to where you run the JAR. User-defined aliases are saved in `./data/aliases.txt`.

---

## Editing the data file

Advanced users can edit `./data/faye.txt` directly. Each line represents one task:

- Todo: `T | 0 | description` or `T | 1 | description` (0 = not done, 1 = done)
- Deadline: `D | 0 | description | yyyy-MM-ddTHH:mm` (or `1` if done)
- Event: `E | 0 | description | from | to` (or `1` if done)

⚠️ **Caution:** Invalid changes may cause Faye to discard data or behave unexpectedly. Back up the file before editing.

---

## FAQ

**Q: How do I run only the CLI version?**  
A: Run `java -jar faye.jar`; the default launcher uses the GUI. To use CLI, run the `Faye` class directly (e.g. via your IDE) or configure the main class to `faye.Faye`.

**Q: Where are my tasks stored?**  
A: In `./data/faye.txt` in the folder from which you run the JAR.

**Q: Can I use my own aliases?**  
A: Yes. Use `alias YOUR_ALIAS COMMAND` (e.g. `alias hw todo`). They are saved in `./data/aliases.txt`.

---

## Known issues

None at the moment.

---

## Command summary

| Action        | Format                     | Example                     |
|---------------|----------------------------|-----------------------------|
| List          | `list`                     | `list` or `l`               |
| Todo          | `todo DESCRIPTION`         | `todo buy milk` or `t buy milk` |
| Deadline      | `deadline DESC /by yyyy-MM-dd HHmm` | `deadline report /by 2025-12-25 1430` |
| Event         | `event DESC /from X /to Y` | `event meeting /from 2pm /to 3pm` |
| Mark done     | `mark INDEX`               | `mark 1` or `m 1`           |
| Unmark        | `unmark INDEX`             | `unmark 1` or `u 1`         |
| Delete        | `delete INDEX`             | `delete 2` or `d 2`         |
| Find          | `find KEYWORD`             | `find book` or `f book`     |
| Alias         | `alias ALIAS COMMAND`      | `alias hw todo`             |
| Exit          | `bye`                      | `bye` or `b`                |
