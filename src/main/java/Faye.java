import  java.util.Scanner;

public class Faye {
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println(" Yo Wassup my G! Yo friend Faye right here");
        System.out.println(" What can I do for you my dawg?");
        System.out.println("____________________________________________");
        System.out.println(" Nah. I can do no nothing other than repeating inputs. SIKE!");
        System.out.println(" Now enter yo mf inputs: ");


        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                System.out.println("____________________________________________");
                System.out.println(" Im outta here. Peace!");
                System.out.println("____________________________________________");
                break;
            }

            System.out.println(input);
            System.out.println("____________________________________________");
        }


    }
}
