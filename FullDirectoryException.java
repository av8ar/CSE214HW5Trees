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
 * @exception FullDirectoryException
 *  The FullDirectoryException class makes a custom exception that indicates
 *  that all child references are full.
 *  Thrown if all child references of this directory are occupied.
 */
public class FullDirectoryException extends Exception{
     public FullDirectoryException(){
         super("ERROR: Present directory is full.");
     }
     public FullDirectoryException(String s) {
            super(s);
     }
}
