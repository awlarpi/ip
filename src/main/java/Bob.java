import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | ClassNotFoundException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        Map<CommandType, Consumer<ParsedCommand>> commandHandlers = new HashMap<>();
        commandHandlers.put(CommandType.LIST, cmd -> listTasks());
        commandHandlers.put(CommandType.MARK, cmd -> markTask(cmd));
        commandHandlers.put(CommandType.UNMARK, cmd -> unmarkTask(cmd));
        commandHandlers.put(CommandType.TODO, cmd -> addTodoTask(cmd));
        commandHandlers.put(CommandType.DEADLINE, cmd -> addDeadlineTask(cmd));
        commandHandlers.put(CommandType.EVENT, cmd -> addEventTask(cmd));
        commandHandlers.put(CommandType.DELETE, cmd -> deleteTask(cmd));

        while (true) {
            try {
                String input = ui.readCommand();
                ParsedCommand parsedCommand = Parser.parse(input);
                if (parsedCommand.getCommandType() == CommandType.BYE) {
                    break;
                }
                commandHandlers
                        .getOrDefault(parsedCommand.getCommandType(),
                                cmd -> ui.showError("Hmm, I don't recognize that command. Try again!"))
                        .accept(parsedCommand);
                storage.save(tasks.getTasks());
            } catch (NumberFormatException e) {
                ui.showError("Whoa! That task number format is wacky. Try again, pal!");
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Error saving tasks: " + e.getMessage());
            }
        }
        ui.showGoodbye();
        ui.cleanup();
    }

    private void listTasks() {
        ui.reply("Here are the tasks in your list:\n" + tasks);
    }

    private void markTask(ParsedCommand cmd) {
        int markTaskNumber = cmd.getTaskNumber();
        if (markTaskNumber <= 0 || markTaskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.getTask(markTaskNumber - 1).markAsDone();
        ui.reply("Nice! I've marked this task as done:\n" + tasks.getTask(markTaskNumber - 1));
    }

    private void unmarkTask(ParsedCommand cmd) {
        int unmarkTaskNumber = cmd.getTaskNumber();
        if (unmarkTaskNumber <= 0 || unmarkTaskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        tasks.getTask(unmarkTaskNumber - 1).markAsNotDone();
        ui.reply("OK, I've marked this task as not done yet:\n" + tasks.getTask(unmarkTaskNumber - 1));
    }

    private void addTodoTask(ParsedCommand cmd) {
        String todoDescription = cmd.getDescription();
        if (todoDescription.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description of a todo cannot be empty. Give me something to do!");
        }
        Task todoTask = new ToDos(todoDescription);
        tasks.addTask(todoTask);
        ui.reply("Got it. I've added this task:\n  " + todoTask + "\nNow you have " + tasks.getSize()
                + " tasks in the list.");
    }

    private void addDeadlineTask(ParsedCommand cmd) {
        String deadlineDescription = cmd.getDescription();
        String by = cmd.getBy();
        if (deadlineDescription.isEmpty() || by.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description and deadline date cannot be empty. Give me something to work with!");
        }
        Task deadlineTask = new Deadlines(deadlineDescription, by);
        tasks.addTask(deadlineTask);
        ui.reply("Got it. I've added this task:\n  " + deadlineTask + "\nNow you have " + tasks.getSize()
                + " tasks in the list.");
    }

    private void addEventTask(ParsedCommand cmd) {
        String eventDescription = cmd.getDescription();
        String from = cmd.getFrom();
        String to = cmd.getTo();
        if (eventDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "Hey! The description, start time, and end time cannot be empty. Give me something to work with!");
        }
        Task eventTask = new Events(eventDescription, from, to);
        tasks.addTask(eventTask);
        ui.reply("Got it. I've added this task:\n  " + eventTask + "\nNow you have " + tasks.getSize()
                + " tasks in the list.");
    }

    private void deleteTask(ParsedCommand cmd) {
        int deleteTaskNumber = cmd.getTaskNumber();
        if (deleteTaskNumber <= 0 || deleteTaskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException(
                    "Whoa there! That task number is out of bounds. Try again, buddy!");
        }
        Task removedTask = tasks.deleteTask(deleteTaskNumber - 1);
        ui.reply("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.getSize()
                + " tasks in the list.");
    }

    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }
}