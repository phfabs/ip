package faye;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistent storage of tasks to and from a file.
 *
 * <p>This class manages reading tasks from a data file and writing tasks
 * back to the file in a specific format.</p>
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath Path to the data file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from the storage file and converts it to Task objects.
     *
     * <p>Creates the file and parent directory if they don't exist.
     * Parses each line to reconstruct Todo, Deadline, or Event objects.</p>
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> load() { 
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // create ./data folder if missing
            file.createNewFile(); // create faye.txt if missing

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3]);
                        tasks.add(new Deadline(description, isDone, by));
                        break;
                    case "E":
                        tasks.add(new Event(description, isDone, parts[3], parts[4]));
                        break;
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Converts task objects into storage format and saves them to the file.
     *
     * @param tasks List of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Task list to save must not be null";
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                fw.write(t.toStorageString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
