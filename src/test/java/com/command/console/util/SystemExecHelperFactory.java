package com.command.console.util;

import com.command.console.util.impl.UnixExecHelper;
import com.command.console.util.impl.WindowsExecHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Factory for creating helper for running shell commands depending on OS.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemExecHelperFactory {

    /**
     * Returns OS shell command helper.
     */
    public static SystemExecHelper getOsExecHelper() {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        if (isWindows) {
            return WindowsExecHelper.INSTANCE;
        }
        return UnixExecHelper.INSTANCE;
    }
}
