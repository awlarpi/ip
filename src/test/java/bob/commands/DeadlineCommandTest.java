package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidDateException;
import bob.exceptions.MissingArgumentException;
import bob.models.TaskList;

public class DeadlineCommandTest {

    @Test
    public void testExecute() throws MissingArgumentException, InvalidDateException {
        TaskList tasks = new TaskList();
        DeadlineCommand cmd = new DeadlineCommand("Test Deadline", "10/10/2023 1800");
        String result = cmd.execute(tasks);
        assertEquals(
                "Got it. I've added this task:\n  [D][ ] Test Deadline (by: 10 Oct 2023, 6:00pm)\n"
                        + "Now you have 1 tasks in the list.",
                result);
        assertEquals(1, tasks.getSize());
        assertEquals("[D][ ] Test Deadline (by: 10 Oct 2023, 6:00pm)", tasks.getTask(0).toString());
    }

    @Test
    public void testThrowsMissingArgumentException() throws InvalidDateException {
        TaskList tasks = new TaskList();
        DeadlineCommand cmd = new DeadlineCommand("Test Deadline", "");
        try {
            cmd.execute(tasks);
        } catch (MissingArgumentException e) {
            assertEquals(
                    "Hey! The description and deadline date cannot be empty. Give me something to work with!",
                    e.getMessage());
        }
    }

    @Test
    public void testThrowsInvalidDateException() throws MissingArgumentException {
        TaskList tasks = new TaskList();
        DeadlineCommand cmd = new DeadlineCommand("Test Deadline", "100pm");
        try {
            cmd.execute(tasks);
        } catch (InvalidDateException e) {
            assertEquals("Hey! The date and time format is invalid. Please use the format: dd/MM/yyyy HHmm",
                    e.getMessage());
        }
    }
}
