package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Command for deleting files and directories.
 */
public class DeleteCommand extends AbstractCommand {

    private static boolean isDirNotEmpty(final Path dir) throws IOException {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir)) {
            return dirStream.iterator().hasNext();
        }
    }

    private static void deleteFolderAndContent(final Path dir) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

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
    protected void execute(List<String> args) {
        Path dir = Paths.get(args.get(0));
        try {
            if (!Files.exists(dir)) {
                throw new IllegalArgumentException("Directory doesn't exist");
            }
            File file = new File(args.get(0));
            if (file.isDirectory() && isDirNotEmpty(dir)) {
                System.out.println("Deleting directory content...");
                deleteFolderAndContent(dir);
            }
            Files.deleteIfExists(dir);
            System.out.println("Successful!");
        } catch (IOException e) {
            throw new IllegalArgumentException("Unsuccessful(\n\n" + getDescription());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Deleting file or directory\nSYNOPSIS\nrm [file name] - deleting file/directory in current directory\n" +
                "rm [path/new file name] - deleting file/directory in target directory";
    }

}
