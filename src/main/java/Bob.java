import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
