package com.command.console.impl;

import com.command.console.AbstractCommand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Command for unzipping archive.
 */
public class UnzipCommand extends AbstractCommand {

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
        if (!(new File(args.get(0))).exists()) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        unzip(args.get(0));
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return "Unzipping a file/directory.\nSYNOPSIS\nunzip[source name] - unzip archive\n";
    }

    private void unzip(final String zipDir) throws IOException {
        ZipFile zipFile = new ZipFile(zipDir, StandardCharsets.UTF_8);
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith("/")) {
                createFolder(entryName);
                continue;
            } else {
                checkFolder(entryName);
            }
            InputStream fis = zipFile.getInputStream(entry);
            FileOutputStream fos = new FileOutputStream(entryName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer, 0, buffer.length);
            fos.write(buffer, 0, buffer.length);
            fis.close();
            fos.close();
        }
        zipFile.close();
        System.out.println("Zip файл разархивирован!");
    }

    private void createDir(final String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void createFolder(final String dirName) {
        if (dirName.endsWith("/")) {
            createDir(dirName.substring(0, dirName.length() - 1));
        }
    }

    private void checkFolder(final String file_path) {
        if (!file_path.endsWith("/") && file_path.contains("/")) {
            String dir = file_path.substring(0, file_path.lastIndexOf("/"));
            createDir(dir);
        }
    }

}
