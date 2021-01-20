# **Console command executor.**

For start use method com.command.console.Launcher.main()

For more information type "help"
For exit type "exit"

### **Commands**:

##### RENAME
_Renaming a file._
###### SYNOPSIS
    rename[file name] [new name] - renaming file in current directory
    rename [path/file name] [path/new name] - renaming file in target directory

##### ZIP
_Zipping a file/directory._
###### SYNOPSIS
    zip[source folder name] [archive name] - create archive in current directory

##### HELP
_I will try to help you;)_
###### SYNOPSIS
    help [command name] - will show command description
    help - will show all commands with it description

##### LS
_Showing files in directory._
###### SYNOPSIS
    ls [folder name] - list files in current directory
    ls [folder name] - list files in target directory
###### OPTIONS
    [-sort] - sorting by names
    [-count] - counting files and directories

##### MV
_Moving file to the target directory._
###### SYNOPSIS
    mv[file name] [path] - moving file to the target directory

##### UNZIP
_Unzipping a file/directory._
###### SYNOPSIS
    unzip[source name] - unzip archive

##### TOUCH
_Creating a new file_
###### SYNOPSIS
    touch [new file name] - creating file in current directory
    touch [path/new file name] - creating file in target directory

##### RM
_Deleting file or directory_
###### SYNOPSIS
    rm [file name] - deleting file/directory in current directory
    rm [path/new file name] - deleting file/directory in target directory

##### CP
_Coping file/directory to the target directory._
###### SYNOPSIS
    cp [source] [target `path] - coping file to the target directory

##### EXIT
_For exit type "exit"_

##### MKDIR
_Creating a new directory_
###### SYNOPSIS
    mkdir [new dir name] - creating directory in current directory
    mkdir [path/new dir name] - creating directory in target directory