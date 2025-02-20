package bob.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.exceptions.InvalidDateException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");
    private LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date.
     * @throws InvalidDateException If the date format is invalid.
     */
    public Deadline(String description, String by) throws InvalidDateException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}
