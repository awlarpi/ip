import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNumber - 1));
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.substring(7));
                tasks.get(taskNumber - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskNumber - 1));
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task task = new ToDos(description);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                Task task = new Deadlines(description, by);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ");
                String description = parts[0];
                String[] times = parts[1].split(" /to ");
                String from = times[0];
                String to = times[1];
                Task task = new Events(description, from, to);
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Invalid command");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}