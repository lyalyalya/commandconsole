package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command for renaming files.
 */
public class RenameFileCommand extends AbstractCommand {

    /**
     * @inheritDoc
     */
    @Override
    protected boolean isInvalidArgs(List<String> args) {
        return args.size() != 2;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void execute(List<String> args) throws IOException {
        Path source = Paths.get(args.get(0));
        if (!Files.exists(source)) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        Files.move(source, source.resolveSibling(args.get(1)));
        System.out.println("Lucky boy! successful;))");

    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Renaming a file.\nSYNOPSIS\nrename[file name] [new name] - renaming file in current directory\nrename [path/file name] [path/new name] - renaming file in target directory";
    }
}
