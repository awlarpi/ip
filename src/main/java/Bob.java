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
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                // grab the number following "mark "
                int taskNumber = Integer.parseInt(input.substring(5));

                // mark the task as done
                tasks.get(taskNumber - 1).markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNumber - 1));
            } else if (input.startsWith("unmark")) {
                // grab the number following "unmark "
                int taskNumber = Integer.parseInt(input.substring(7));

                // mark the task as not done
                tasks.get(taskNumber - 1).markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskNumber - 1));
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + task);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}