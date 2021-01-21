package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Command for listing files.
 */
public class ListFilesCommand extends AbstractCommand {

    /**
     * @inheritDoc
     */
    @Override
    protected boolean isInvalidArgs(List<String> args) {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void execute(List<String> args) throws IOException {
        String path;
        int countFiles = 0;
        int countDirectories = 0;
        if (args.size() > 0 && Files.exists(Paths.get(args.get(0)))) {
            path = args.get(0);
        } else {
            path = System.getProperty("user.dir");
        }
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(path))) {
            List<String> ls = new ArrayList<>();
            for (Path p : ds) {
                if (Files.isDirectory(p)) {
                    countDirectories++;
                }
                if (Files.isRegularFile(p)) {
                    countFiles++;
                }
                ls.add(p.getFileName().toString());
            }
            if (args.contains("-sort")) {
                Collections.sort(ls);
            }
            for (String s : ls) {
                System.out.println(s);
            }
            if (args.contains("-count")) {
                System.out.println("Files: " + countFiles);
                System.out.println("Directories: " + countDirectories);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Showing files in directory.\nSYNOPSIS\nls [folder name] - list files in current directory\nls [folder name] - list files in target directory\nOPTIONS\n   [-sort] - sorting by names\n   [-count] - counting files and directories";
    }

}
