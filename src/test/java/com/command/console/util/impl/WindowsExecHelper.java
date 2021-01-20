package com.command.console.util.impl;

import com.command.console.util.SystemExecHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WindowsExecHelper implements SystemExecHelper {

    public static final WindowsExecHelper INSTANCE = new WindowsExecHelper();

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
