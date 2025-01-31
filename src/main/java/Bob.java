import java.util.Scanner;
import java.util.function.Consumer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bob {
    private static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasks();

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        Map<CommandType, Consumer<String>> commandHandlers = new HashMap<>();
        commandHandlers.put(CommandType.LIST, input -> listTasks(tasks));
        commandHandlers.put(CommandType.MARK, input -> markTask(tasks, input));
        commandHandlers.put(CommandType.UNMARK, input -> unmarkTask(tasks, input));
        commandHandlers.put(CommandType.TODO, input -> addTodoTask(tasks, input));
        commandHandlers.put(CommandType.DEADLINE, input -> addDeadlineTask(tasks, input));
        commandHandlers.put(CommandType.EVENT, input -> addEventTask(tasks, input));
        commandHandlers.put(CommandType.DELETE, input -> deleteTask(tasks, input));

        while (true) {
            String input = scanner.nextLine();
            CommandType commandType = getCommandType(input);
            try {
                if (commandType == CommandType.BYE) {
                    break;
                }
                commandHandlers
                        .getOrDefault(commandType,
                                cmd -> System.out.println("Hmm, I don't recognize that command. Try again!"))
                        .accept(input);
                saveTasks(tasks);
            } catch (NumberFormatException e) {
                System.out.println("Whoa! That task number format is wacky. Try again, pal!");
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void markTask(ArrayList<Task> tasks, String input) {
        int markTaskNumber = Integer.parseInt(input.substring(5));
        if (markTaskNumber <= 0 || markTaskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.get(markTaskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(markTaskNumber - 1));
    }

    private static void unmarkTask(ArrayList<Task> tasks, String input) {
        int unmarkTaskNumber = Integer.parseInt(input.substring(7));
        if (unmarkTaskNumber <= 0 || unmarkTaskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.get(unmarkTaskNumber - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(unmarkTaskNumber - 1));
    }

    private static void addTodoTask(ArrayList<Task> tasks, String input) {
        String todoDescription = input.substring(5).trim();
        if (todoDescription.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description of a todo cannot be empty. Give me something to do!");
        }
        Task todoTask = new ToDos(todoDescription);
        tasks.add(todoTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + todoTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(ArrayList<Task> tasks, String input) {
        String[] deadlineParts = input.substring(9).split(" /by ");
        if (deadlineParts.length < 2) {
            throw new IllegalArgumentException(
                    "Yikes! The deadline command format is all wrong. Give it another shot!");
        }
        String deadlineDescription = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        if (deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description and deadline date cannot be empty. Give me something to work with!");
        }
        Task deadlineTask = new Deadlines(deadlineDescription, by);
        tasks.add(deadlineTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadlineTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(ArrayList<Task> tasks, String input) {
        String[] eventParts = input.substring(6).split(" /from ");
        if (eventParts.length < 2) {
            throw new IllegalArgumentException(
                    "Yikes! The event command format is all wrong. Give it another shot!");
        }
        String eventDescription = eventParts[0].trim();
        String[] times = eventParts[1].split(" /to ");
        if (times.length < 2) {
            throw new IllegalArgumentException(
                    "Yikes! The event command format is all wrong. Give it another shot!");
        }
        String from = times[0].trim();
        String to = times[1].trim();
        if (eventDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description, start time, and end time cannot be empty. Give me something to work with!");
        }
        Task eventTask = new Events(eventDescription, from, to);
        tasks.add(eventTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + eventTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(ArrayList<Task> tasks, String input) {
        int deleteTaskNumber = Integer.parseInt(input.substring(7));
        if (deleteTaskNumber <= 0 || deleteTaskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        Task removedTask = tasks.remove(deleteTaskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Create directories if they do not exist
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directories if they do not exist
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}