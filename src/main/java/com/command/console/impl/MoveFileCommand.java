package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command for moving files.
 */
public class MoveFileCommand extends AbstractCommand {

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
        Files.move(source, Paths.get(args.get(1), args.get(0)));
        System.out.println("Lucky boy! successful;))");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Moving file to the target directory.\nSYNOPSIS\nmv[file name] [path] - moving file to the target directory";
    }
}
