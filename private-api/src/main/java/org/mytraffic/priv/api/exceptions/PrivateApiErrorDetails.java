package org.mytraffic.priv.api.exceptions;

/**
 * Information transmitted to the client when a service error occurs.
 *
 * @author avasquez
 * @author mariobarque
 */
public class PrivateApiErrorDetails {

    private PrivateApiErrorCode errorCode;
    private String message;

    public PrivateApiErrorDetails() {
    }

    public PrivateApiErrorDetails(PrivateApiErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public PrivateApiErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(PrivateApiErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
