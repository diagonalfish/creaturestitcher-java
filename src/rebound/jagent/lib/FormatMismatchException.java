/*
 * Created on Jan 12, 2006
 * 	by the wonderful Eclipse(c)
 */
package rebound.jagent.lib;

public class FormatMismatchException
        extends Exception {
    public FormatMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatMismatchException(String message) {
        super(message);
    }
}
