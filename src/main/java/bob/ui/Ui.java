package bob.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private String lastReply;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }

    public void close() {
        scanner.close();
        System.out.println("Goodbye, have a nice day!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void reply(String message) {
        System.out.println(message);
        this.lastReply = message;
    }

    public String getLastReply() {
        return lastReply;
    }
}