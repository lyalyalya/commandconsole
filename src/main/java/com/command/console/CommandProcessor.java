package com.command.console;

import com.command.console.impl.CopyFileCommand;
import com.command.console.impl.CreateDirectoryCommand;
import com.command.console.impl.CreateFileCommand;
import com.command.console.impl.DeleteCommand;
import com.command.console.impl.ExitCommand;
import com.command.console.impl.HelpCommand;
import com.command.console.impl.ListFilesCommand;
import com.command.console.impl.MoveFileCommand;
import com.command.console.impl.RenameFileCommand;
import com.command.console.impl.UnzipCommand;
import com.command.console.impl.ZipCommand;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.command.console.Command processor.
 */
public class CommandProcessor {

    public static final CommandProcessor INSTANCE = new CommandProcessor();

    private final Map<String, Command> commands;

    private CommandProcessor() {
        commands = new HashMap<>();
        commands.put("RENAME", new RenameFileCommand());
        commands.put("TOUCH", new CreateFileCommand());
        commands.put("MKDIR", new CreateDirectoryCommand());
        commands.put("RM", new DeleteCommand());
        commands.put("ZIP", new ZipCommand());
        commands.put("UNZIP", new UnzipCommand());
        commands.put("MV", new MoveFileCommand());
        commands.put("LS", new ListFilesCommand());
        commands.put("HELP", new HelpCommand());
        commands.put("CP", new CopyFileCommand());
        commands.put("EXIT", new ExitCommand());
    }

    /**
     * Executes command with given arguments.
     *
     * @param args arguments for command
     * @throws IllegalArgumentException if command not found
     */
    public void executeCommand(List<String> args) {
        if (!args.isEmpty() && commands.containsKey(args.get(0).toUpperCase())) {
            commands.get(args.get(0).toUpperCase()).execute(args.subList(1, args.size()));
        } else {
            throw new IllegalArgumentException("com.command.console.Command not found");
        }
    }

    /**
     * Returns all supported commands.
     *
     * @return map of supported commands with its names as a key
     */
    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }
}
