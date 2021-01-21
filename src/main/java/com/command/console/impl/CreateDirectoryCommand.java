package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command for creating directories.
 */
public class CreateDirectoryCommand extends AbstractCommand {

    /**
     * @inheritDoc
     */
    @Override
    protected boolean isInvalidArgs(List<String> args) {
        return args.size() != 1;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void execute(List<String> args) throws IOException {
        Path newDir = Paths.get(args.get(0));
        if (Files.exists(newDir)) {
            throw new IllegalArgumentException("Directory already exists");
        }
        Files.createDirectories(newDir);
        System.out.println("Successful!");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Creating a new directory\n\nSYNOPSIS\nmkdir [new dir name] - creating directory in current directory\nmkdir [path/new dir name] - creating directory in target directory";
    }
}
