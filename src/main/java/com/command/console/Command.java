package com.command.console;

import java.util.List;

/**
 * Common command interface.
 */
public interface Command {

    /**
     * Gets command description.
     *
     * @return info about command
     */
    String getDescription();

    /**
     * Executes command with given arguments.
     *
     * @param args arguments for command
     * @throws IllegalArgumentException if unsuccessful operation
     */
    void executeOrElseThrow(List<String> args);
}
