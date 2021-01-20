package com.command.console.util.impl;

import com.command.console.util.SystemExecHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnixExecHelper implements SystemExecHelper {

    public static final UnixExecHelper INSTANCE = new UnixExecHelper();

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
