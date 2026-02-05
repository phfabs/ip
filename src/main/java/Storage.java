import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // read data from faye.txt and convert the text to TAsk object
    // return an array of tasks
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
                        LocalDate by = LocalDate.parse(parts[3]);
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

    //convert task object into task and save in faye.txt
    public void save(ArrayList<Task> tasks) {
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
