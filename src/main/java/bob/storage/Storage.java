package bob.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import bob.models.Task;

/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directories if they do not exist
            ArrayList<Task> tasks = new ArrayList<>();
            save(tasks);
            return tasks;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ArrayList<Task> tasks = new ArrayList<>();
            save(tasks);
            return tasks;
        }
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create directories if they do not exist
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(tasks);
        }
    }
}
