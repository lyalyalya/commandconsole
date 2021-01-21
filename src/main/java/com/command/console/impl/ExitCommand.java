package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.util.List;

/**
 * Command for exiting program.
 */
public class ExitCommand extends AbstractCommand {

    /**
     * @inheritDoc
     */
    @Override
    protected boolean isInvalidArgs(List<String> args) {
        return !args.isEmpty();
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void execute(List<String> args) {
        System.out.println("Thanks for being with us!");
        System.exit(0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "For exit type \"exit\"";
    }
}
