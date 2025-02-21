# Bob User Guide

Bob is a simple chatbot application that helps you manage your tasks. This guide will help you get started with Bob and understand its features.

## Features

### Adding Deadlines

You can add a deadline task using the `deadline` command. The format is:
```
deadline <task description> /by <deadline>
```
Example:
```
deadline Submit assignment /by 10/10/2023 1800
```
Expected output:
```
Got it. I've added this task:
  [D][ ] Submit assignment (by: 10 Oct 2023, 6:00pm)
Now you have X tasks in the list.
```

### Adding Events

You can add an event task using the `event` command. The format is:
```
event <event description> /from <start time> /to <end time>
```
Example:
```
event Team meeting /from 10/10/2023 1000 /to 10/10/2023 1200
```
Expected output:
```
Got it. I've added this task:
  [E][ ] Team meeting (from: 10 Oct 2023, 10:00am to: 10 Oct 2023, 12:00pm)
Now you have X tasks in the list.
```

### Adding Todos

You can add a todo task using the `todo` command. The format is:
```
todo <task description>
```
Example:
```
todo Read book
```
Expected output:
```
Got it. I've added this task:
  [T][ ] Read book
Now you have X tasks in the list.
```

### Marking Tasks

You can mark a task as done using the `mark` command. The format is:
```
mark <task number>
```
Example:
```
mark 1
```
Expected output:
```
Nice! I've marked this task as done:
  [T][X] Read book
```

### Unmarking Tasks

You can unmark a task as not done using the `unmark` command. The format is:
```
unmark <task number>
```
Example:
```
unmark 1
```
Expected output:
```
OK, I've marked this task as not done yet:
  [T][ ] Read book
```

### Deleting Tasks

You can delete a task using the `delete` command. The format is:
```
delete <task number>
```
Example:
```
delete 1
```
Expected output:
```
Noted. I've removed this task:
  [T][ ] Read book
Now you have X tasks in the list.
```

### Listing Tasks

You can list all tasks using the `list` command. The format is:
```
list
```
Expected output:
```
Here are the tasks in your list:
1. [T][ ] Read book
2. [D][ ] Submit assignment (by: 10 Oct 2023, 6:00pm)
3. [E][ ] Team meeting (from: 10 Oct 2023, 10:00am to: 10 Oct 2023, 12:00pm)
```

### Finding Tasks

You can find tasks containing a keyword using the `find` command. The format is:
```
find <keyword>
```
Example:
```
find book
```
Expected output:
```
Here are the matching tasks in your list:
1. [T][ ] Read book
```

### Exiting the Application

You can exit the application using the `bye` command. The format is:
```
bye
```
Expected output:
```
Bye. Hope to see you again soon!
```

## How to Run

To run the Bob chatbot application, you can either double-click the JAR file or use the `java -jar` command in the terminal. Here are the steps:

1. **Double-click the JAR file:**
   - Locate the JAR file in your file explorer.
   - Double-click the JAR file to run the application.

2. **Use the terminal:**
   - Open a terminal window.
   - Navigate to the directory containing the JAR file.
   - Run the following command:
     ```sh
     java -jar Bob.jar
     ```

### Prerequisites

Before running the Bob chatbot application, ensure you have the following prerequisites:

1. **Java 17:**
   - Make sure you have Java 17 installed on your system.
