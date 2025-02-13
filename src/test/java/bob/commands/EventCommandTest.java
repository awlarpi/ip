package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.models.TaskList;

public class EventCommandTest {

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        EventCommand cmd = new EventCommand("Test Event", "10/10/2023 1800", "10/10/2023 2000");
        String result = cmd.execute(tasks);
        assertEquals(
            "Got it. I've added this task:\n  [E][ ] Test Event (from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)\n"
            + "Now you have 1 tasks in the list.",
            result);
        assertEquals(1, tasks.getSize());
        assertEquals("[E][ ] Test Event (from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)",
                tasks.getTask(0).toString());
    }
}
