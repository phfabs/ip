import  java.util.Scanner;
<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
=======
import java.time.LocalDate;
>>>>>>> branch-Level-8
import java.util.ArrayList;

public class Faye {
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println(" Yo Wassup my G! Yo friend Faye right here");
        System.out.println("____________________________________________");
        System.out.println(" ");
        System.out.println(" Add your tasks: ");
        

        Scanner scnr = new Scanner(System.in);

        Storage storage = new Storage("./data/faye.txt");
        ArrayList<Task> tasks = storage.load();

        while(true){
            String input = scnr.nextLine();

            if(input.equals("bye")) {
                System.out.println("____________________________________________");
                System.out.println(" Im outta here. Peace!");
                System.out.println("____________________________________________");
                break;

            } else if (input.equals("list")) {
                System.out.println("____________________________________________");

                for (int i = 0; i < tasks.size(); i++){
                    System.out.println((1 + i) + "." + tasks.get(i));
                }
                
                System.out.println("____________________________________________");
            
            } else if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new InvalidTaskNumberException("This number is invalid like your personality");
                    }
                    tasks.get(index).mark();
                    storage.save(tasks);
                    System.out.println("____________________________________________");
                    System.out.println(" Marked!");
                    System.out.println("   " + tasks.get(index));
                    System.out.println("____________________________________________");
                } catch (InvalidTaskNumberException e) {
                    System.out.println("____________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println("____________________________________________");
                }

            }
            else if (input.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new InvalidTaskNumberException("This number is invalid like your personality");
                    }
                    tasks.get(index).unmark();
                    storage.save(tasks);
                    System.out.println("____________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                    System.out.println("____________________________________________");
                } catch (InvalidTaskNumberException e) {
                    System.out.println("____________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println("____________________________________________");
                }
            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1; 

                    if (index < 0 || index >= tasks.size()) {
                        throw new InvalidTaskNumberException("This number is invalid like your personality");
                    }

                    Task removedTask = tasks.remove(index); 
                    storage.save(tasks);
                    System.out.println("____________________________________________");
                    System.out.println(" Aight. Removed. ");
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________");

                } catch (InvalidTaskNumberException e) {
                    System.out.println("____________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println("____________________________________________");
                }
            }
            else if (input.startsWith("todo")) {
                try {
                    String todoTask = input.substring(4).trim();
                    if (todoTask.isEmpty()) {
                        throw new EmptyTaskInputException("Dude, Todo Task cant be empty. U gotta do something");
                    }
                    tasks.add(new Todo(todoTask));
                    storage.save(tasks);
                    System.out.println("Now you have " + tasks.size() + " tasks.");
                } catch (EmptyTaskInputException e) {
                    System.out.println("____________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println("____________________________________________");
                }

            } else if (input.startsWith("deadline")) {
                try {
                    String task = input.substring(8).trim();

                    if (task.isEmpty()) {
                        throw new EmptyTaskInputException("Dude, ddl cant be empty.");
                    }

                    String[] temp = task.split("/by");

                    if (temp.length < 2 || temp[0].trim().isEmpty() || temp[1].trim().isEmpty()) {
                        throw new EmptyTaskInputException("Deadline must have format in: [task] /by yyyy-mm-dd");
                    }
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(temp[1].trim(), formatter);
                    tasks.add(new Deadline(temp[0].trim(), dateTime));
                    storage.save(tasks);
                    System.out.println("Now you have " + tasks.size() + " tasks.");

                } catch (EmptyTaskInputException e) {
                    System.out.println("____________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println("____________________________________________");
                }
                
            }

            else if (input.startsWith("event")) {
                try {
                    String task = input.substring(5).trim();

                    if (task.isEmpty()) {
                        throw new EmptyTaskInputException("Dude, event cant be empty.");
                    }

                    String[] temp = task.split("/from|/to");

                    if (temp.length < 3 || 
                        temp[0].trim().isEmpty() || 
                        temp[1].trim().isEmpty() ||
                        temp[2].trim().isEmpty()) {
                        throw new EmptyTaskInputException("Event must have format in: [ddl] /from [time] /to [time]");
                    }
                
                    tasks.add(new Event(temp[0].trim(),temp[1].trim(),temp[2].trim()));
                    storage.save(tasks);
                    System.out.println("Now you have " + tasks.size() + " tasks.");

                } catch (EmptyTaskInputException e) {
                    System.out.println("____________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println("____________________________________________");
                }
            
            } else {
                Task task =  new Task(input);
                tasks.add(task);
                System.out.println("____________________________________________");
                System.out.println(" added: " + input);
                System.out.println("Do it now or ill whoop yo ass");
                System.out.println("____________________________________________");

            }

           
        }


    }
}
