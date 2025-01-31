package bob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create directories if they do not exist
            ArrayList<Task> tasks = new ArrayList<>();
            try {
                save(tasks);
            } catch (IOException e) {
                throw new IOException("Error creating new file: " + e.getMessage());
            }
            return tasks;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Task>) ois.readObject();
        }
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create directories if they do not exist
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(tasks);
        }
    }
}