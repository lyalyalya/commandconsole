package com.command.console.util;

/**
 * Helper common for running shell commands depending on OS.
 */
public interface SystemExecHelper {

   /**
    * Returns OS separator.
    */
   default String getSystemSeparator() {
      return System.getProperty("file.separator");
   }

   /**
    * Removes file.
    */
   String removeFile();

   /**
    * Removes directory.
    */
   String removeDir();

   /**
    * Creates new file.
    */
   String createFile();
}
