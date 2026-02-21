package faye;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages command aliases to provide a friendlier syntax for commands.
 *
 * <p>Supports both built-in aliases (e.g., {@code t} for {@code todo}) and
 * user-defined aliases loaded from and saved to a file.</p>
 */
public class AliasManager {

    private static final String ALIAS_FILE_PATH = "./data/aliases.txt";
    private static final String ALIAS_COMMENT_PREFIX = "#";
    private static final String ALIAS_KEY_VALUE_SEPARATOR = "=";

    private static final AliasManager INSTANCE = new AliasManager();

    private final Map<String, String> aliases = new HashMap<>();

    private AliasManager() {
        loadDefaultAliases();
        loadUserAliases();
    }

    /**
     * Returns the singleton instance of the alias manager.
     *
     * @return AliasManager instance.
     */
    public static AliasManager getInstance() {
        return INSTANCE;
    }

    private void loadDefaultAliases() {
        // Minimal built-in aliases
        aliases.put("t", "todo");
        aliases.put("ddl", "deadline");
        aliases.put("e", "event");
        aliases.put("m", "mark");
        aliases.put("u", "unmark");
        aliases.put("d", "delete");
        aliases.put("l", "list");
        aliases.put("f", "find");
        aliases.put("b", "bye");
    }

    private void loadUserAliases() {
        File file = new File(ALIAS_FILE_PATH);
        try {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith(ALIAS_COMMENT_PREFIX)) {
                    continue;
                }
                int separatorIndex = line.indexOf(ALIAS_KEY_VALUE_SEPARATOR);
                if (separatorIndex <= 0
                        || separatorIndex == line.length() - 1) {
                    continue;
                }
                String alias = line.substring(0, separatorIndex).trim();
                String command = line.substring(separatorIndex + 1).trim();
                if (!alias.isEmpty() && !command.isEmpty()) {
                    aliases.put(alias, command);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading aliases: " + e.getMessage());
        }
    }

    private void saveUserAliases() {
        File file = new File(ALIAS_FILE_PATH);
        try {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            FileWriter writer = new FileWriter(file);
            for (Map.Entry<String, String> entry : aliases.entrySet()) {
                writer.write(entry.getKey() + ALIAS_KEY_VALUE_SEPARATOR
                        + entry.getValue() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving aliases: " + e.getMessage());
        }
    }

    /**
     * Resolves the given command token to its canonical form using aliases.
     *
     * @param commandToken Raw command token from user input.
     * @return Canonical command after applying aliases, or the original token
     *         if there is no alias.
     */
    public String resolve(String commandToken) {
        return aliases.getOrDefault(commandToken, commandToken);
    }

    /**
     * Adds or updates a user-defined alias mapping and persists it.
     *
     * @param alias Alias token to add.
     * @param command Canonical command the alias maps to.
     */
    public void addAlias(String alias, String command) {
        if (alias == null || command == null || alias.isEmpty()
                || command.isEmpty()) {
            return;
        }
        aliases.put(alias, command);
        saveUserAliases();
    }
}

