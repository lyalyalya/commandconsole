package com.command.console;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Abstract command class.
 */
public abstract class AbstractCommand implements Command {

    /**
     * @inheritDoc
     */
    @Override
    public void execute(List<String> args) {
        if (isNull(args) || isInvalidArgs(args)) {
            throw new IllegalArgumentException("Illegal arguments\n" + getDescription());
        }
        try {
            executeCommand(args);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unsuccessful\n\n" + getDescription());
        }
    }

    /**
     * Validates args.
     *
     * @param args given arguments
     * @return true if invalid args given
     */
    protected abstract boolean isInvalidArgs(List<String> args);

    /**
     * Executes command.
     *
     * @param args given arguments
     */
    protected abstract void executeCommand(List<String> args) throws IOException;
}
