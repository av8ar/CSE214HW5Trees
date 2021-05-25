/**
 * @author Victor Dai
 * @authorID 113206638
 * @email victor.dai.1@stonybrook.edu
 * @HWNumber 5
 * @course CSE 214
 * @recitation R04
 * @TA James Finn, Matthew Shinder
 */
import java.util.*;

/**
 *  A single main method which allows a user to interact with a file system
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
 */
public class BashTerminal {
    public static void main(String[] args){
        String user = "113206638"; //My NetID for Stony Brook
        String host = "Binary1100101"; //host name
        String prompter = user + "@" + host; //user@host
        System.out.println("Starting bash terminal.");
        DirectoryTree ternaryFileSystem = new DirectoryTree(); //tree initialize
        String input = "";
        String command = "";
        String secondCommand = "";
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){ //start of bash terminal
            secondCommand = ""; //resets secondCommand to length 0
            System.out.print("[" + prompter + "]: $ ");
            input = in.nextLine();
            if(input.indexOf(" ") >= 0) {
                command = input.substring(0, input.indexOf(" "));
                secondCommand = input.substring(input.indexOf(" ") + 1);
            }
            else command = input;
            switch(command){
                case "pwd" : //presentWorkingDirectory() is used
                    System.out.println(ternaryFileSystem.presentWorkingDirectory
                            ());
                    break;
                case "ls" : //listDirectory() is used
                    if(secondCommand.equals("-R"))
                        ternaryFileSystem.printDirectoryTree();
                    else
                        System.out.println(ternaryFileSystem.listDirectory());
                    break;
                case "cd" :
                    if(secondCommand.equals("/")){ //resetCursor() is used
                        ternaryFileSystem.resetCursor();
                    }
                    else if(secondCommand.equals("..")) { //setCursorToParent
                        // is used
                        ternaryFileSystem.setCursorToParent();
                    }
                    else{ //changeDirectory(name) is used
                        try {
                            ternaryFileSystem.changeDirectory(secondCommand);
                        } catch (NotADirectoryException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "mkdir" : //makeDirectory(name) is used
                    try {
                        if(secondCommand.length() < 1)
                            throw new IllegalArgumentException();
                        ternaryFileSystem.makeDirectory(secondCommand);
                    } catch (FullDirectoryException e) {
                        System.out.println(e.getMessage());
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Invalid name for directory.");
                    }
                    break;
                case "touch" : //makeFile(name) is used
                    try {
                        if(secondCommand.length() < 1)
                            throw new IllegalArgumentException();
                        ternaryFileSystem.makeFile(secondCommand);
                    } catch (FullDirectoryException e) {
                        System.out.println(e.getMessage());
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Invalid name for file.");
                    }
                    break;
                case "exit" : //exits the while loop and ends program
                    System.out.println("Program terminating normally");
                    exit = true;
                    break;
                default: //handles exceptions in user input
                    System.out.println("Please enter a valid command " +
                            "(Commands are case-sensitive).");
                    break;
            }//end of command options
        }//end of Bash terminal
    }//end of main method
}
