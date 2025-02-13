package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.models.TaskList;

public class DeadlineCommandTest {

    @Test
    public void testExecute() {
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
}
