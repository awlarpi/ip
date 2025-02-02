package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDosTest {

    @Test
    public void testToDoToString() {
        ToDos todo = new ToDos("Test ToDo");
        assertEquals("[T][ ] Test ToDo", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDos todo = new ToDos("Test ToDo");
        todo.markAsDone();
        assertEquals("[T][X] Test ToDo", todo.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        ToDos todo = new ToDos("Test ToDo");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] Test ToDo", todo.toString());
    }
}