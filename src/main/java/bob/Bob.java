package bob;

import java.io.IOException;
import bob.commands.ByeCommand;
import bob.commands.Command;
import bob.commands.Parser;
import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the main class for the Bob application.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bob object with the specified file path.
     *
     * @param filePath The file path to load and save tasks.
     */
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

    /**
     * Runs the Bob application.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                if (command instanceof ByeCommand) {
                    break;
                }
                command.execute(tasks, ui, storage);
                storage.save(tasks.getTasks());
            } catch (NumberFormatException e) {
                ui.showError("Whoa! That task number format is wacky. Try again, pal!");
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Error saving tasks: " + e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method to start the Bob application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }
}
