package com.command.console.util.impl;

import com.command.console.util.SystemExecHelpStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WindowsExecHelpStrategy implements SystemExecHelpStrategy {

    public static final WindowsExecHelpStrategy INSTANCE = new WindowsExecHelpStrategy();

    @Override
    public String removeFile() {
        return "DEL ";
    }

    @Override
    public String removeDir() {
        return "RMDIR ";
    }

    @Override
    public String createFile() {
        return "type NUL > ";
    }

}
