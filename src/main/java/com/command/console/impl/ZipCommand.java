package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Command for zipping to archive.
 */
public class ZipCommand extends AbstractCommand {

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
        zip(args.get(0), args.get(1));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Zipping a file/directory.\nSYNOPSIS\nzip[source folder name] [archive name] - create archive in current directory\n";
    }

    private void zip(String source, String zipFile) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile), StandardCharsets.UTF_8)) {
            File file = new File(source);
            addDir(zout, file);
            System.out.println("Successful!");
        }
    }

    private void addDir(ZipOutputStream zout, File file) throws IOException {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    addDir(zout, f);
                    continue;
                }
                FileInputStream fis = new FileInputStream(f);
                zout.putNextEntry(new ZipEntry(f.getPath()));
                byte[] buffer = new byte[4048];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zout.write(buffer, 0, length);
                }
                zout.closeEntry();
                fis.close();
            }
        }
    }
}
