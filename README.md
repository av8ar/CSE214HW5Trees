# CSE214HW5Trees

## BashTerminal
* Includes commands for the Terminal

A single main method which allows a user to interact with a file system
 *  implemented by an instance of DirectoryTree using the following commands
 *  (note that commands are case-sensitive and will always be lower-case):
 *
 * Command	Description
 * =============================================================================
 * pwd	    Print the "present working directory" of the cursor node
 *          (e.g root/home/user/Documents).
 * ls	    List the names of all the child directories or files of the cursor.
 * ls -R	Recursive traversal of the directory tree. Prints the entire tree
 *          starting from the cursor in pre-order traversal.
 * cd {dir}	Moves the cursor to the child directory with the indicated name
 *          (Only consider the direct children of the cursor).
 * cd /	    Moves the cursor to the root of the tree.
 * mkdir {name}	Creates a new directory with the indicated name as a child of
 *              the cursor, as long as there is room.
 * touch {name}	Creates a new file with the indicated name as a child of the
 *              cursor, as long as there is room.
 * exit	    Terminates the program.
 *
 * cd ..    Moves the cursor to the parent directory of the cursor.

## DirectoryNode
* Creates class for node and complimentary methods

## DirectoryTree
* Implements the technical aspect of different methods for the different commands for the Terminal 

## Exceptions
* NotADirectoryNodeException
* FullDirectoryException
