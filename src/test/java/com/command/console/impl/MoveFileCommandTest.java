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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveFileCommandTest {

    private static final String ROOT_DIR = "src";
    private static final SystemExecHelpStrategy OS_EXEC_HELPER = SystemExecHelpFactory.getOsExecHelpStrategy();
    private static final String TEST_FILE_NAME = "test_file";
    private static final String EXPECTED_ERROR_MSG = "Illegal arguments\n" +
            "Moving file to the target directory.\n" +
            "SYNOPSIS\n" +
            "mv[file name] [path] - moving file to the target directory";

    private MoveFileCommand moveFileCommand;

    @BeforeEach
    void setUp() {
        moveFileCommand = new MoveFileCommand();
    }

    @Test
    public void shouldThrowExceptionIfEmptyArguments() {
        Executable call = () -> moveFileCommand.executeOrElseThrow(Collections.emptyList());

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    @Test
    @SneakyThrows
    public void shouldSuccessfulMoveFile() {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(OS_EXEC_HELPER.createFile() + TEST_FILE_NAME);

        Thread.sleep(1000);

        moveFileCommand.executeOrElseThrow(Arrays.asList(TEST_FILE_NAME, ROOT_DIR));

        assertTrue(Files.exists(Paths.get("src", TEST_FILE_NAME)));
        assertFalse(Files.exists(Paths.get(TEST_FILE_NAME)));

        runtime.exec(OS_EXEC_HELPER.removeFile() + ROOT_DIR + OS_EXEC_HELPER.getSystemSeparator()
                + TEST_FILE_NAME);
    }

}