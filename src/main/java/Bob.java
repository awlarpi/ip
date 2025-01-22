import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
