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
 * @exception NotADirectoryException
 *  The NotADirectoryException class makes a custom exception that indicates
 *  if a node is a file and not a directory and throws this custom exception.
 *  Thrown if the node being examined is a file.
 */
public class NotADirectoryException extends Exception {
    public NotADirectoryException(){
        super("ERROR: Cannot change directory into a file.");
    }
    public NotADirectoryException(String s) {
        super(s);
    }
}
