package com.command.console.util.impl;

import com.command.console.util.SystemExecHelpStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnixExecHelpStrategy implements SystemExecHelpStrategy {

    public static final UnixExecHelpStrategy INSTANCE = new UnixExecHelpStrategy();

    @Override
    public String removeFile() {
        return "rm -rf ";
    }

    @Override
    public String removeDir() {
        return "rm -rf ";
    }

    @Override
    public String createFile() {
        return "touch ";
    }

}
