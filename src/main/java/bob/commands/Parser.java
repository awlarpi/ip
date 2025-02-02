package bob.commands;

public class Parser {
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String firstWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (firstWord) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(arguments.trim()));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(arguments.trim()));
            case "todo":
                return new TodoCommand(arguments.trim());
            case "deadline":
                String[] deadlineParts = arguments.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new IllegalArgumentException(
                            "Yikes! The deadline command format is all wrong. Give it another shot!");
                }
                return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
            case "event":
                String[] eventParts = arguments.split(" /from ");
                if (eventParts.length < 2) {
                    throw new IllegalArgumentException(
                            "Yikes! The event command format is all wrong. Give it another shot!");
                }
                String[] times = eventParts[1].split(" /to ");
                if (times.length < 2) {
                    throw new IllegalArgumentException(
                            "Yikes! The event command format is all wrong. Give it another shot!");
                }
                return new EventCommand(eventParts[0].trim(), times[0].trim(), times[1].trim());
            case "delete":
                return new DeleteCommand(Integer.parseInt(arguments.trim()));
            default:
                throw new IllegalArgumentException("Yikes! I'm not sure what you're trying to say. Try again!");
        }
    }
}