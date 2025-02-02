package bob;

public class Parser {
    public static ParsedCommand parse(String input) {
        ParsedCommand parsedCommand = new ParsedCommand(getCommandType(input));
        switch (parsedCommand.getCommandType()) {
            case MARK:
                parsedCommand.setTaskNumber(Integer.parseInt(input.substring(5).trim()));
                break;
            case UNMARK:
                parsedCommand.setTaskNumber(Integer.parseInt(input.substring(7).trim()));
                break;
            case TODO:
                parsedCommand.setDescription(input.substring(5).trim());
                break;
            case DEADLINE:
                String[] deadlineParts = input.substring(9).split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new IllegalArgumentException("Yikes! The deadline command format is all wrong. Give it another shot!");
                }
                parsedCommand.setDescription(deadlineParts[0].trim());
                parsedCommand.setBy(deadlineParts[1].trim());
                break;
            case EVENT:
                String[] eventParts = input.substring(6).split(" /from ");
                if (eventParts.length < 2) {
                    throw new IllegalArgumentException("Yikes! The event command format is all wrong. Give it another shot!");
                }
                parsedCommand.setDescription(eventParts[0].trim());
                String[] times = eventParts[1].split(" /to ");
                if (times.length < 2) {
                    throw new IllegalArgumentException("Yikes! The event command format is all wrong. Give it another shot!");
                }
                parsedCommand.setFrom(times[0].trim());
                parsedCommand.setTo(times[1].trim());
                break;
            case DELETE:
                parsedCommand.setTaskNumber(Integer.parseInt(input.substring(7).trim()));
                break;
            default:
                throw new IllegalArgumentException("Yikes! I'm not sure what you're trying to say. Try again!");
        }
        return parsedCommand;
    }

    private static CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}