public class Parser {

    public static String getCommand(String input) {
        return input.split(" ")[0];  // returns first word
    }

    public static int getIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1; 
    }

    public static String getDescription(String input) {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) return "";
        return input.substring(firstSpace + 1).trim();
    }

    public static String[] splitDeadline(String input) {
        return getDescription(input).split("/by");
    }

    public static String[] splitEvent(String input) {
        return getDescription(input).split("/from|/to");
    }
}
