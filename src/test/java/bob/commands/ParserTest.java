package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.exceptions.MissingArgumentException;
import bob.exceptions.UnknownCommandException;

public class ParserTest {

    @Test
    public void testParseTodo() throws MissingArgumentException, UnknownCommandException {
        Command cmd = Parser.parse("todo read book");
        assertEquals(TodoCommand.class, cmd.getClass());
        TodoCommand todoCmd = (TodoCommand) cmd;
        assertEquals("read book", todoCmd.getDescription());
    }

    @Test
    public void testParseDeadline() throws MissingArgumentException, UnknownCommandException {
        Command cmd = Parser.parse("deadline submit assignment /by 10/10/2023 1800");
        assertEquals(DeadlineCommand.class, cmd.getClass());
        DeadlineCommand deadlineCmd = (DeadlineCommand) cmd;
        assertEquals("submit assignment", deadlineCmd.getDescription());
        assertEquals("10/10/2023 1800", deadlineCmd.getBy());
    }

    @Test
    public void testParseEvent() throws MissingArgumentException, UnknownCommandException {
        Command cmd = Parser.parse("event project meeting /from 10/10/2023 1800 /to 11/10/2023 1800");
        assertEquals(EventCommand.class, cmd.getClass());
        EventCommand eventCmd = (EventCommand) cmd;
        assertEquals("project meeting", eventCmd.getDescription());
        assertEquals("10/10/2023 1800", eventCmd.getFrom());
        assertEquals("11/10/2023 1800", eventCmd.getTo());
    }

    @Test
    public void testParseList() throws MissingArgumentException, UnknownCommandException {
        Command cmd = Parser.parse("list");
        assertEquals(ListCommand.class, cmd.getClass());
    }
}
