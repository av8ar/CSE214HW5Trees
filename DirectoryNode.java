/**
 * @author Victor Dai
 * @authorID 113206638
 * @email victor.dai.1@stonybrook.edu
 * @HWNumber 5
 * @course CSE 214
 * @recitation R04
 * @TA James Finn, Matthew Shinder
 */

/**
 * DirectoryNode represents a node in the file tree.
 *
 * The DirectoryNode class should contain 3 DirectoryNode references, left,
 * middle, and right. In addition, the class should contain a String member
 * variable name, which indicates the name of the node in the tree. Since
 * DirectoryNodes can be either a file or a folder, the boolean member
 * variable named isFile is used to differentiate between the two. Note that if
 * this value is set to true, then the node is not a directory, and therefore
 * should NOT contain any children. That is, files are not allowed to have
 * children.
 */
public class DirectoryNode {
    private String name; //Name of DirectoryNode
    private DirectoryNode left, middle, right; //children of DirectoryNode
    private boolean isFile; //the status of DirectoryNode as a file
    private DirectoryNode parent;
    private int depth = 0;

    //Constructors
    public DirectoryNode(){}
    //The original parent node is the root.
    public DirectoryNode(String name){
        this.name = name;
        left = middle = right = parent = null;
    }
    public DirectoryNode(String name, boolean isFile){
        this.name = name;
        this.isFile = isFile;
        left = middle = right = parent = null;
    }

    //return name of DirectoryNode
    public String getName() {
        return name;
    }
    //sets name of DirectoryName
    public void setName(String name) {
        this.name = name;
    }
    //returns the left child reference of DirectoryNode
    public DirectoryNode getLeft() {
        return left;
    }
    //returns the middle child reference of DirectoryNode
    public DirectoryNode getMiddle() {
        return middle;
    }
    //returns the right child reference of DirectoryNode
    public DirectoryNode getRight() {
        return right;
    }
    //returns the Parent reference of the DirectoryNode
    public DirectoryNode getParent() { return parent;}
    //returns the boolean determining if the DirectoryNode is a file.
    public boolean isFile() {
        return isFile;
    }

    /**
     * Adds newChild to any of the open child positions of this node
     * (left, middle, or right).
     * NOTE: Children should be added to this node in left-to-right order, i.e.
     * left is filled first, middle is filled second, and right is filled last.
     *
     * <dt> Preconditions:
     *     -   This node is not a file.
     *     -   There is at least one empty position in the children of this node
     * (left, middle, or right). </dt>
     *
     * <dt> Postconditions:
     *     -   newChild has been added as a child of this node. If there is no
     *     room for a new node, throw a FullDirectoryException. </dt>
     *
     * @param newChild - new Child reference being inserted in the tree.
     *
     * @throws FullDirectoryException indicates that current node is a file
     * and file cannot contain DirectoryNode references (i.e. all files are
     * leaves).
     * @throws NotADirectoryException indicates that all child references of
     * this directory are occupied.
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException,
            NotADirectoryException {
        newChild.parent = this;
        if(isFile) throw new NotADirectoryException();
        if(left == null) left = newChild;
        else if(middle == null) middle = newChild;
        else if(right == null) right = newChild;
        else throw new FullDirectoryException();
    }

    /**
     * Prints the directory tree starting from the DirectoryNode object that
     * is calling this method in preorder.
     * Acts as the helper method for printDirectoryTree() in the class
     * DirectoryTree.
     *
     * @param depth is the depth of the DirectoryNode/number of indents
     */
    public void preorder(int depth){
        String format = ("\t").repeat(depth);
        if(isFile) format += " - ";
        else format += "|- ";
        System.out.println(format + name);
        depth++;
        if(left != null) left.preorder(depth);
        if(middle != null) middle.preorder(depth);
        if(right != null) right.preorder(depth);
    }
}
