package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command for creating files.
 */
public class CreateFileCommand extends AbstractCommand {

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
        Path newPath = Paths.get(args.get(0));
        if (!Files.exists(newPath)) {
            Files.createFile(newPath);
            System.out.printf("Successful, \"%s\" was created\n", args.get(0));
        } else {
            throw new IllegalArgumentException("File already exists");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Creating a new file\nSYNOPSIS\ntouch [new file name] - creating file in current directory\ntouch [path/new file name] - creating file in target directory";
    }
}
