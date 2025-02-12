package bob.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test ToDo");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());
        assertEquals("[T][ ] Test ToDo", taskList.getTask(0).toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test ToDo");
        taskList.addTask(task);
        Task removedTask = taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
        assertEquals("[T][ ] Test ToDo", removedTask.toString());
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test ToDo");
        taskList.addTask(task);
        assertEquals("[T][ ] Test ToDo", taskList.getTask(0).toString());
    }

    @Test
    public void testFindTasksByKeyword() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("read book");
        Task task2 = new ToDo("return book");
        Task task3 = new ToDo("write code");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        List<Task> result = taskList.findTasksByKeyword("book");
        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
    }
}
