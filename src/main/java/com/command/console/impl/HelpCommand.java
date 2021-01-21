package com.command.console.impl;

import com.command.console.AbstractCommand;
import com.command.console.Command;
import com.command.console.CommandProcessor;

import java.util.List;
import java.util.Map;

/**
 * Command for getting help with program commands.
 */
public class HelpCommand extends AbstractCommand {

    /**
     * @inheritDoc
     */
    @Override
    protected boolean isInvalidArgs(List<String> args) {
        return args.size() > 1;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void execute(List<String> args) {
        Map<String, Command> commands = CommandProcessor.INSTANCE.getCommands();
        if (args.isEmpty()) {
            for (Map.Entry<String, Command> pair : commands.entrySet()) {
                System.out.println(pair.getKey());
                System.out.println(pair.getValue().getDescription());
                System.out.println();
            }
        } else {
            if (!CommandProcessor.INSTANCE.getCommands().containsKey(args.get(0).toUpperCase())) {
                throw new IllegalArgumentException("command not found");
            }
            System.out.println(commands.get(args.get(0).toUpperCase()).getDescription());
        }
    }

    /**
     * @inheritDoc
     */
    public String getDescription() {
        return "I will try to help you;)\n SYNOPSIS\n help [command name] - will show command description\n " +
                "help - will show all commands with it description";
    }
}
