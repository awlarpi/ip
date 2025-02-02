package bob.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private Scanner scanner;
    private String lastReply;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }

    /**
     * Closes the scanner and displays the goodbye message.
     */
    public void close() {
        scanner.close();
        System.out.println("Goodbye, have a nice day!");
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a reply message.
     *
     * @param message The reply message to display.
     */
    public void reply(String message) {
        System.out.println(message);
        this.lastReply = message;
    }

    /**
     * Gets the last reply message.
     *
     * @return The last reply message.
     */
    public String getLastReply() {
        return lastReply;
    }
}
