package com.command.console.util;

import com.command.console.util.impl.UnixExecHelpStrategy;
import com.command.console.util.impl.WindowsExecHelpStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Factory for creating helper strategy depending on OS for running shell commands.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemExecHelpFactory {

    /**
     * Returns OS shell command helper.
     */
    public static SystemExecHelpStrategy getOsExecHelpStrategy() {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        if (isWindows) {
            return WindowsExecHelpStrategy.INSTANCE;
        }
        return UnixExecHelpStrategy.INSTANCE;
    }
}
