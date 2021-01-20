package com.command.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandProcessorTest {

    private static final String COMMAND_NOT_FOUND_ERROR_MSG = "com.command.console.Command not found";

    private CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        commandProcessor = CommandProcessor.INSTANCE;
    }

    @Test
    public void shouldThrowExceptionIfNullCommand() {
        Executable call = () -> commandProcessor.executeCommand(Collections.emptyList());

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(COMMAND_NOT_FOUND_ERROR_MSG, actualException.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfUnknownCommand() {
        Executable call = () -> commandProcessor.executeCommand(Collections.singletonList("CLEAR"));

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(COMMAND_NOT_FOUND_ERROR_MSG, actualException.getMessage());
    }

    @Test
    public void shouldSuccessIfCorrectCommand() {
        Executable call = () -> commandProcessor.executeCommand(Collections.singletonList("LS"));

        assertDoesNotThrow(call);
    }

}