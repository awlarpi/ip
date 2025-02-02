package bob;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        assertDoesNotThrow(ui::showWelcome);
    }

    @Test
    public void testShowGoodbye() {
        Ui ui = new Ui();
        assertDoesNotThrow(ui::showGoodbye);
    }

    @Test
    public void testShowError() {
        Ui ui = new Ui();
        assertDoesNotThrow(() -> ui.showError("Test Error"));
    }

    @Test
    public void testReply() {
        Ui ui = new Ui();
        assertDoesNotThrow(() -> ui.reply("Test Reply"));
    }
}