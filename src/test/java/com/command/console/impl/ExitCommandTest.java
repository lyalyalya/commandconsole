package com.command.console.impl;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;

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
        Executable call = () -> exitCommand.executeOrElseThrow(Collections.singletonList("hi"));

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, call);
        assertEquals(EXPECTED_ERROR_MSG, actualException.getMessage());
    }

    @Test
    @SneakyThrows
    public void shouldSuccessfulExit() {
        SystemLambda.catchSystemExit(() -> exitCommand.executeOrElseThrow(Collections.emptyList()));
    }

}