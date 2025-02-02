package bob.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDosTest {

    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals("[T][ ] Test ToDo", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("Test ToDo");
        todo.markAsDone();
        assertEquals("[T][X] Test ToDo", todo.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        ToDo todo = new ToDo("Test ToDo");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] Test ToDo", todo.toString());
    }
}