package G29740.GoLProject;

/**
 * Exception class for Grid creation when parameters are invalid
 *
 * @author G29740
 */
public class GridException extends Exception {

    /**
     * Generate an exception when given invalid parameters to create grid
     *
     * @param message the exception message to handle
     */
    public GridException(String message) {
        super(message);
    }
}