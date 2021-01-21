package com.command.console.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ListFilesCommandTest {

    private ListFilesCommand listFilesCommand;

    @BeforeEach
    void setUp() {
        listFilesCommand = new ListFilesCommand();
    }

    @Test
    public void shouldSuccessfulListFiles() {
        Executable call = () -> listFilesCommand.executeOrElseThrow(Collections.emptyList());

        assertDoesNotThrow(call);
    }
}