package org.mytraffic.api.data.exceptions;

import org.mytraffic.api.exception.MyTrafficException;

/**
 * Root exception for all My Traffic data API.
 *
 * @author avasquez
 * @author mariobarque
 */
public class DataApiException extends MyTrafficException {

    private DataApiErrorCode errorCode;

    public DataApiException(DataApiErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DataApiException(DataApiErrorCode errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public DataApiException(DataApiErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);

        this.errorCode = errorCode;
    }

    public DataApiException(DataApiErrorCode errorCode, Throwable cause) {
        super(cause);

        this.errorCode = errorCode;
    }

    public DataApiErrorCode getErrorCode() {
        return errorCode;
    }

}
