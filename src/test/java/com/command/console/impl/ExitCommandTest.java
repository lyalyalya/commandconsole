package com.command.console.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitCommandTest {

    private static final String EXPECTED_ERROR_MSG = "Illegal arguments\n" +
            "For exit type \"exit\"";

    private ExitCommand exitCommand;

    @BeforeEach
    void setUp() {
        exitCommand = new ExitCommand();
    }

    @Test
    public void shouldThrowExceptionIfNonEmptyArguments() {
        Executable call = () -> exitCommand.execute(Collections.singletonList("hi"));

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    /*@Test
    public void shouldSuccessfulExit() {
        Executable call = () -> exitCommand.execute(Collections.emptyList());

        assertDoesNotThrow(call);
    }*/

}