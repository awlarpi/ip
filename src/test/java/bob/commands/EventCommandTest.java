package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidDateException;
import bob.exceptions.MissingArgumentException;
import bob.models.TaskList;

public class EventCommandTest {

    @Test
    public void testExecute() throws MissingArgumentException, InvalidDateException {
        TaskList tasks = new TaskList();
        EventCommand cmd = new EventCommand("Test Event", "10/10/2023 1800", "10/10/2023 2000");
        String result = cmd.execute(tasks);
        assertEquals(
                "Got it. I've added this task:\n  [E][ ] Test Event "
                        + "(from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)\n"
                        + "Now you have 1 tasks in the list.",
                result);
        assertEquals(1, tasks.getSize());
        assertEquals("[E][ ] Test Event (from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)",
                tasks.getTask(0).toString());
    }

    @Test
    public void testExecute_missingDescription() throws InvalidDateException {
        TaskList tasks = new TaskList();
        EventCommand cmd = new EventCommand("", "10/10/2023 1800", "10/10/2023 2000");
        try {
            cmd.execute(tasks);
        } catch (MissingArgumentException e) {
            assertEquals(
                    "Hey! The description, start time, and end time cannot be empty. "
                            + "Give me something to work with!",
                    e.getMessage());
        }
    }

    @Test
    public void testExecute_missingStartDateTime() throws MissingArgumentException, InvalidDateException {
        TaskList tasks = new TaskList();
        EventCommand cmd = new EventCommand("Test Event", "", "10/10/2023 2000");
        try {
            cmd.execute(tasks);
        } catch (MissingArgumentException e) {
            assertEquals(
                    "Hey! The description, start time, and end time cannot be empty. "
                            + "Give me something to work with!",
                    e.getMessage());
        }
    }

    @Test
    public void testInvalidDate() throws MissingArgumentException {
        TaskList tasks = new TaskList();
        EventCommand cmd = new EventCommand("Test Event", "10/10/20800", "10/123 2000");
        try {
            cmd.execute(tasks);
        } catch (InvalidDateException e) {
            assertEquals("Hey! The date and time format is invalid."
                    + " Please use the format: dd/MM/yyyy HHmm",
                    e.getMessage());
        }
    }
}
