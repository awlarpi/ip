package bob;

import java.io.IOException;

import bob.commands.Command;
import bob.commands.Parser;
import bob.models.TaskList;
import bob.storage.Storage;

/**
 * Represents the main class for the Bob application.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Bob object with the specified file path.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Bob(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "Command should not be null";
            String response = command.execute(tasks);
            storage.save(tasks.getTasks());
            return response;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
