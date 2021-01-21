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

class CreateFileCommandTest {

    private static final SystemExecHelpStrategy OS_EXEC_HELPER = SystemExecHelpFactory.getOsExecHelpStrategy();
    private static final String TEST_FILE_NAME = "test_file";
    private static final String EXPECTED_ERROR_MSG = "Illegal arguments\n" +
            "Creating a new file\n" +
            "SYNOPSIS\n" +
            "touch [new file name] - creating file in current directory\n" +
            "touch [path/new file name] - creating file in target directory";

    private CreateFileCommand createFileCommand;

    @BeforeEach
    void setUp() {
        createFileCommand = new CreateFileCommand();
    }

    @Test
    public void shouldThrowExceptionIfEmptyArguments() {
        Executable call = () -> createFileCommand.executeOrElseThrow(Collections.emptyList());

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    @Test
    @SneakyThrows
    public void shouldSuccessfulCreateFile() {
        createFileCommand.executeOrElseThrow(singletonList(TEST_FILE_NAME));

        Path actualPath = Paths.get(TEST_FILE_NAME);
        assertTrue(Files.exists(actualPath));
        assertTrue(Files.isRegularFile(actualPath));

        Runtime runtime = Runtime.getRuntime();
        runtime.exec(OS_EXEC_HELPER.removeFile() + TEST_FILE_NAME);
    }

}