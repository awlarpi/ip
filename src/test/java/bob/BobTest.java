package bob;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class BobTest {

    @Test
    public void testBobInitialization() {
        assertDoesNotThrow(() -> new Bob("data/tasks.txt"));
    }
}
