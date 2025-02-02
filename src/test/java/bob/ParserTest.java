package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseTodo() {
        ParsedCommand cmd = Parser.parse("todo read book");
        assertEquals(CommandType.TODO, cmd.getCommandType());
        assertEquals("read book", cmd.getDescription());
    }

    @Test
    public void testParseDeadline() {
        ParsedCommand cmd = Parser.parse("deadline submit assignment /by 10/10/2023 1800");
        assertEquals(CommandType.DEADLINE, cmd.getCommandType());
        assertEquals("submit assignment", cmd.getDescription());
        assertEquals("10/10/2023 1800", cmd.getBy());
    }

    @Test
    public void testParseEvent() {
        ParsedCommand cmd = Parser.parse("event project meeting /from 10/10/2023 1800 /to 11/10/2023 1800");
        assertEquals(CommandType.EVENT, cmd.getCommandType());
        assertEquals("project meeting", cmd.getDescription());
        assertEquals("10/10/2023 1800", cmd.getFrom());
        assertEquals("11/10/2023 1800", cmd.getTo());
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("invalid command"));
    }
}