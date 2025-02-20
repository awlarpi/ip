package bob.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidDateException;

public class DeadlineTest {

    @Test
    public void testDeadlineToString() throws InvalidDateException {
        Deadline deadline = new Deadline("Test Deadline", "10/10/2023 1800");
        assertEquals("[D][ ] Test Deadline (by: 10 Oct 2023, 6:00pm)", deadline.toString());
    }

    @Test
    public void testMarkAsDone() throws InvalidDateException {
        Deadline deadline = new Deadline("Test Deadline", "10/10/2023 1800");
        deadline.markAsDone();
        assertEquals("[D][X] Test Deadline (by: 10 Oct 2023, 6:00pm)", deadline.toString());
    }

    @Test
    public void testMarkAsNotDone() throws InvalidDateException {
        Deadline deadline = new Deadline("Test Deadline", "10/10/2023 1800");
        deadline.markAsDone();
        deadline.markAsNotDone();
        assertEquals("[D][ ] Test Deadline (by: 10 Oct 2023, 6:00pm)", deadline.toString());
    }
}
