package bob.commands;

import java.io.IOException;

import bob.models.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}