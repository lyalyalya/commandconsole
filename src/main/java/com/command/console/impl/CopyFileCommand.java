package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Command for copying files and directories.
 */
public class CopyFileCommand extends AbstractCommand {

    /**
     * @inheritDoc
     */
    @Override
    protected boolean isInvalidArgs(List<String> args) {
        return args.size() != 2 || !Files.exists(Paths.get(args.get(0))) || !Files.exists(Paths.get(args.get(1)));
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void execute(List<String> args) throws IOException {
        final Path source = Paths.get(args.get(0));
        final Path target = Paths.get(args.get(1), args.get(0));
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
                Files.createDirectories(target.resolve(source.relativize(dir)));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(source.relativize(file)));
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("Successful!");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Coping file/directory to the target directory.\nSYNOPSIS\ncp [source] [target `path] - coping file to the target directory";
    }
}
