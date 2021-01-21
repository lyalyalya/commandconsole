package com.command.console.impl;

import com.command.console.util.SystemExecHelpStrategy;
import com.command.console.util.SystemExecHelpFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CopyFileCommandTest {

    private static final SystemExecHelpStrategy OS_EXEC_HELPER = SystemExecHelpFactory.getOsExecHelpStrategy();
    private static final String ROOT_DIR = "src";
    private static final String TEST_FILE_NAME = "test_file";
    private static final String EXPECTED_ERROR_MSG = "Illegal arguments\n" +
            "Coping file/directory to the target directory.\n" +
            "SYNOPSIS\n" +
            "cp [source] [target `path] - coping file to the target directory";

    private CopyFileCommand copyFileCommand;

    @BeforeEach
    void setUp() {
        copyFileCommand = new CopyFileCommand();
    }

    @Test
    public void shouldThrowExceptionIfEmptyArguments() {
        Executable call = () -> copyFileCommand.executeOrElseThrow(Collections.emptyList());

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfFileNotExists() {
        Executable call = () -> copyFileCommand.executeOrElseThrow(Arrays.asList("not_existing_file.txt", ROOT_DIR));

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    @Test
    @SneakyThrows
    public void shouldSuccessfulCopyFile() {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(OS_EXEC_HELPER.createFile() + TEST_FILE_NAME);

        Thread.sleep(1000);

        copyFileCommand.executeOrElseThrow(Arrays.asList(TEST_FILE_NAME, "src"));

        assertTrue(Files.exists(Paths.get("src", TEST_FILE_NAME)));

        runtime.exec(OS_EXEC_HELPER.removeFile() + TEST_FILE_NAME);
        runtime.exec(OS_EXEC_HELPER.removeFile() + ROOT_DIR + OS_EXEC_HELPER.getSystemSeparator()
                + TEST_FILE_NAME);
    }

}