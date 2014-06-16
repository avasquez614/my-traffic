package org.mytraffic.priv.api.exceptions;

import org.mytraffic.priv.exception.MyTrafficException;

/**
 * Root exception for all My Traffic private API.
 *
 * @author avasquez
 * @author mariobarque
 */
public class PrivateApiException extends MyTrafficException {

    private PrivateApiErrorCode errorCode;

    public PrivateApiException(PrivateApiErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public PrivateApiException(PrivateApiErrorCode errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public PrivateApiException(PrivateApiErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);

        this.errorCode = errorCode;
    }

    public PrivateApiException(PrivateApiErrorCode errorCode, Throwable cause) {
        super(cause);

        this.errorCode = errorCode;
    }

    public PrivateApiErrorCode getErrorCode() {
        return errorCode;
    }

}
