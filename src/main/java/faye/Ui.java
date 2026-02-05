package faye;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________");
        System.out.println(" Yo Wassup my G! Yo friend Faye right here");
        System.out.println("____________________________________________");
    }

    public void showBye() {
        System.out.println("____________________________________________");
        System.out.println(" Im outta here. Peace!");
        System.out.println("____________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________");
    }

    public void showTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showAdd(Task task, int size) {
        System.out.println(" Added:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks.");
    }

    public void showDelete(Task task, int size) {
        System.out.println(" Aight. Removed:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks.");
    }

    public void showMark(Task task) {
        System.out.println(" Marked!");
        System.out.println("   " + task);
    }

    public void showUnmark(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
