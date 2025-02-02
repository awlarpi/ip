package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EventsTest {

    @Test
    public void testEventToString() {
        Events event = new Events("Test Event", "10/10/2023 1800", "10/10/2023 2000");
        assertEquals("[E][ ] Test Event (from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)", event.toString());
    }

    @Test
    public void testMarkAsDone() {
        Events event = new Events("Test Event", "10/10/2023 1800", "10/10/2023 2000");
        event.markAsDone();
        assertEquals("[E][X] Test Event (from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)", event.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        Events event = new Events("Test Event", "10/10/2023 1800", "10/10/2023 2000");
        event.markAsDone();
        event.markAsNotDone();
        assertEquals("[E][ ] Test Event (from: 10 Oct 2023, 6:00pm to: 10 Oct 2023, 8:00pm)", event.toString());
    }
}