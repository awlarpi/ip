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
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if (input.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(input.substring(5));
                    if (taskNumber <= 0 || taskNumber > tasks.size()) {
                        throw new IndexOutOfBoundsException(
                                "Whoa there! That task number is out of bounds. Try again, buddy!");
                    }
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskNumber - 1));
                } else if (input.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    if (taskNumber <= 0 || taskNumber > tasks.size()) {
                        throw new IndexOutOfBoundsException(
                                "Whoa there! That task number is out of bounds. Try again, buddy!");
                    }
                    tasks.get(taskNumber - 1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskNumber - 1));
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Hey! The description of a todo cannot be empty. Give me something to do!");
                    }
                    Task task = new ToDos(description);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 2) {
                        throw new IllegalArgumentException(
                                "Yikes! The deadline command format is all wrong. Give it another shot!");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Hey! The description and deadline date cannot be empty. Give me something to work with!");
                    }
                    Task task = new Deadlines(description, by);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from ");
                    if (parts.length < 2) {
                        throw new IllegalArgumentException(
                                "Yikes! The event command format is all wrong. Give it another shot!");
                    }
                    String description = parts[0].trim();
                    String[] times = parts[1].split(" /to ");
                    if (times.length < 2) {
                        throw new IllegalArgumentException(
                                "Yikes! The event command format is all wrong. Give it another shot!");
                    }
                    String from = times[0].trim();
                    String to = times[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Hey! The description, start time, and end time cannot be empty. Give me something to work with!");
                    }
                    Task task = new Events(description, from, to);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("Hmm, I don't recognize that command. Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Whoa! That task number format is wacky. Try again, pal!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}