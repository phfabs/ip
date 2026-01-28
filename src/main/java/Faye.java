import  java.util.Scanner;
import java.util.ArrayList;

public class Faye {
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println(" Yo Wassup my G! Yo friend Faye right here");
        System.out.println("____________________________________________");
        System.out.println(" ");
        System.out.println(" Add your tasks: ");
        

        Scanner scnr = new Scanner(System.in);

        //Stores user inputs
        ArrayList<Task> tasks = new ArrayList<>();

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
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).mark();

                System.out.println("____________________________________________");
                System.out.println(" Marked!");
                System.out.println("   " + tasks.get(index));
                System.out.println("____________________________________________");
            }

            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks.get(index).unmark();

                System.out.println("____________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(index));
                System.out.println("____________________________________________");
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
