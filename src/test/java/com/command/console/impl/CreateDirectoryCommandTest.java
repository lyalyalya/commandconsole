package com.command.console.impl;

import com.command.console.util.SystemExecHelpStrategy;
import com.command.console.util.SystemExecHelpFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateDirectoryCommandTest {

    private static final SystemExecHelpStrategy OS_EXEC_HELPER = SystemExecHelpFactory.getOsExecHelpStrategy();
    private static final String TEST_DIR_NAME = "test_dir";
    private static final String EXPECTED_ERROR_MSG = "Illegal arguments\n" +
            "Creating a new directory\n" +
            "\n" +
            "SYNOPSIS\n" +
            "mkdir [new dir name] - creating directory in current directory\n" +
            "mkdir [path/new dir name] - creating directory in target directory";

    private CreateDirectoryCommand createDirectoryCommand;

    @BeforeEach
    void setUp() {
        createDirectoryCommand = new CreateDirectoryCommand();
    }

    @Test
    public void shouldThrowExceptionIfEmptyArguments() {
        Executable call = () -> createDirectoryCommand.executeOrElseThrow(Collections.emptyList());

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    @Test
    @SneakyThrows
    public void shouldSuccessfulCreateDirectory() {
        createDirectoryCommand.executeOrElseThrow(singletonList(TEST_DIR_NAME));

        Path actualPath = Paths.get(TEST_DIR_NAME);
        assertTrue(Files.exists(actualPath));
        assertTrue(Files.isDirectory(actualPath));

        Runtime runtime = Runtime.getRuntime();
        runtime.exec(OS_EXEC_HELPER.removeDir() + TEST_DIR_NAME);
    }

}