package org.mytraffic.exception;

/**
 * Root exception for all errors in My Traffic system.
 *
 * @author avasquez
 * @author mariobarque
 */
public class MyTrafficException extends Exception {

    public MyTrafficException() {
    }

    public MyTrafficException(String message) {
        super(message);
    }

    public MyTrafficException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyTrafficException(Throwable cause) {
        super(cause);
    }

}
