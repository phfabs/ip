import  java.util.Scanner;
import java.util.ArrayList;

public class Faye {
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println(" Yo Wassup my G! Yo friend Faye right here");
        System.out.println(" What can I do for you my dawg?");
        System.out.println("____________________________________________");
        System.out.println(" As a skilled person, i can decument yo inputs");
        System.out.println(" Now enter yo mf inputs if u wna test me out: ");


        Scanner scanner = new Scanner(System.in);

        //Stores user inputs
        ArrayList<String> inputList = new ArrayList<>();

        while(true){
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                System.out.println("____________________________________________");
                System.out.println(" Im outta here. Peace!");
                System.out.println("____________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________");

                for (int i = 0; i < inputList.size(); i++){
                    System.out.println((1 + i) + "." + inputList.get(i));
                }

                System.out.println("____________________________________________");
            } else {
                inputList.add(input);
                System.out.println("____________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________");

            }

           
        }


    }
}
