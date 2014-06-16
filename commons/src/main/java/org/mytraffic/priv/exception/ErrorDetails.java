package org.mytraffic.priv.exception;

/**
 * Information transmitted to the client when an error occurs.
 *
 * @author avasquez
 */
public class ErrorDetails<E extends Enum<E>> {

    private Enum<E> errorCode;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(Enum<E> errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public Enum<E> getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Enum<E> errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
