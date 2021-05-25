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
 * DirectoryTree implements a ternary (3-child) tree of DirectoryNodes. The
 * class should contain a reference to the root of the tree, a cursor for the
 * present working directory, and various methods for insertion and deletion.
 * The class should contain methods for moving the cursor through the file
 * system, printing the filepath of the present working directory
 * (cursor location), listing the directories and files in the present working
 * directory, printing the entire file system, and finding a file in the file
 * system.
 */
public class DirectoryTree {
    private DirectoryNode root, cursor, temp, cursorMem;

    /**
     * Initializes a DirectoryTree object with a single DirectoryNode named
     * "root".
     *
     * <dt> Postconditions:
     * The tree contains a single DirectoryNode named "root", and both cursor
     * and root reference this node.
     * NOTE: Do not confuse the name of the directory with the name of the
     * reference variable. The DirectoryNode member variable of DirectoryTree
     * named root should reference a DirectoryNode who's name is "root", i.e.
     * root.getName().equals("root") is true. </dt>
     */
    public DirectoryTree(){
        root = new DirectoryNode("root");
        cursor = temp = root;
    }

    /**
     * Moves the cursor to the root node of the tree.
     *
     * <dt> Postconditions:
     *      The cursor now references the root node of the tree.</dt>
     */
    public void resetCursor(){
        cursor = root;
    }

    /**
     * Moves the cursor to its parent node. In this BashTerminal, cursor
     * will be expected to be moved to its parent node reference when the
     * user inputs the command : cd ..
     */
    public void setCursorToParent(){
        if(cursor != root) cursor = cursor.getParent();
        else System.out.println("ERROR: Already at root directory.");
    }

    /**
     * Moves the cursor to the directory with the name indicated by name.
     *
     * <dt> Preconditions:
     * 'name' references a valid directory ('name' cannot reference a file).
     * </dt>
     *
     * <dt> Postconditions:
     * The cursor now references the directory with the name indicated by name.
     * If a child could not be found with that name, then the user is prompted
     * to enter a different directory name. If the name was not a directory, a
     * NotADirectoryException has been thrown. </dt>
     *
     * Throws:
     * @Exception NotADirectoryException: Thrown if the node with the indicated
     * name is a file, as files cannot be selected by the cursor, or cannot be
     * found.
     *
     * NOTE: In modern operating systems, the change directory command
     * (cd {path}) allows the user to jump from a current directory to any other
     * directory in the file system given an absolute or relative path.
     * In this assignment, you will only be required to change directory to
     * direct children of the cursor (cd {dir}).
     *
     * @param name - name of Directory for the cursor to be on.
     * @throws NotADirectoryException if Directory is a file.
     */
    public void changeDirectory(String name) throws NotADirectoryException {
        if (cursor.getLeft() != null && cursor.getLeft().getName().equals(name))
        {
            if(cursor.getLeft().isFile()) throw new NotADirectoryException();
            cursor = cursor.getLeft();
            return;
        }
        else if (cursor.getMiddle() != null &&
                cursor.getMiddle().getName().equals(name)) {
            if(cursor.getMiddle().isFile()) throw new NotADirectoryException();
            cursor = cursor.getMiddle();
            return;
        } else if(cursor.getRight() != null &&
                cursor.getRight().getName().equals(name)){
            if(cursor.getRight().isFile()) throw new NotADirectoryException();
            cursor = cursor.getRight();
            return;
        } else {
            System.out.print("ERROR: No such directory named '" + name + "'. " +
                    "Enter a different directory name: ");
            Scanner key = new Scanner(System.in);
            name = key.nextLine();
            changeDirectory(name);
        }
    }

    /**
     * Returns a String containing the path of directory names from the root
     * node of the tree to the cursor, with each name separated by a forward
     * slash "/".
     *
     * <dt> Postconditions:
     *      The cursor remains at the same DirectoryNode. </dt>
     *
     * @return path of directory at cursor.
     */
    public String presentWorkingDirectory(){
        String path = "";
        DirectoryNode tracer = cursor;
        while(tracer != root){
            path = "/" + tracer.getName() + path;
            tracer = tracer.getParent();
        }
        path = "root" + path;
        return path;
    }

    /**
     * Returns a String containing a space-separated list of names of all the
     * child directories or files of the cursor.
     *
     * @return the list of all files and directories of the children of
     * the cursor reference.
     */
    public String listDirectory(){
        String list = "";
        if(cursor.getLeft() != null) list += cursor.getLeft().getName() + " ";
        if(cursor.getMiddle() != null) list += cursor.getMiddle().getName() +
                " ";
        if(cursor.getRight() != null) list+= cursor.getRight().getName();
        return list;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory
     * tree, starting from the cursor.
     *
     * <dt> Postconditions: Cursor remains on the same DirectoryNode. </dt>
     */
    public void printDirectoryTree(){
        cursor.preorder(0);
    }

    /**
     * Creates a directory with the indicated name and adds it to the children
     * of the cursor node. Children of a node are added in
     * left-to-right order.
     *
     * <dt> Preconditions:
     *      'name' is a legal argument (does not contain spaces " " or forward
     *      slashes "/"). </dt>
     *
     * <dt> Postconditions:
     *      A new DirectoryNode has been added to the children of the cursor, or
     *      an exception has been thrown. </dt>
     *
     * @param name - The name of the directory to add.
     *
     * @throws IllegalArgumentException
     *      Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException
     *      Thrown if all child references of this directory are occupied.
     */
    public void makeDirectory(String name) throws IllegalArgumentException,
            FullDirectoryException{
        if(name.indexOf(" ") >= 0 || name.indexOf("/") >= 0){
            throw new IllegalArgumentException();
        }
        DirectoryNode newDirectory = new DirectoryNode(name, false);
        try {
            cursor.addChild(newDirectory);
        } catch (NotADirectoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the
     * cursor node. Children of a node are added in left to right.
     *
     * <dt> Preconditions:
     *      'name' is a legal argument (does not contain spaces " " or forward
     *      slashes "/"). </dt>
     * <dt> Postconditions:
     *      A new DirectoryNode has been added to the children of the cursor, or
     *      an exception has been thrown. </dt>
     *
     * @param name - name of the file to add.
     *
     * @throws IllegalArgumentException
     * @throws FullDirectoryException
     */
    public void makeFile(String name) throws IllegalArgumentException,
            FullDirectoryException {
        if (name.indexOf(" ") >= 0 || name.indexOf("/") >= 0) {
            throw new IllegalArgumentException();
        }
        DirectoryNode newFile = new DirectoryNode(name, true);
        try {
            cursor.addChild(newFile);
        } catch (NotADirectoryException e) {
            e.printStackTrace();
        }
    }

}//end of Directory Tree class