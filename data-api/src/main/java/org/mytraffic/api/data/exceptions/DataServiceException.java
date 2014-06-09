package org.mytraffic.api.data.exceptions;

import org.mytraffic.api.exception.MyTrafficException;

/**
 * Root exception for all My Traffic data services.
 *
 * @author avasquez
 * @author mariobarque
 */
public class DataServiceException extends MyTrafficException {

    private DataErrorCode errorCode;

    public DataServiceException(DataErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DataServiceException(DataErrorCode errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public DataServiceException(DataErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);

        this.errorCode = errorCode;
    }

    public DataServiceException(DataErrorCode errorCode, Throwable cause) {
        super(cause);

        this.errorCode = errorCode;
    }

    public DataErrorCode getErrorCode() {
        return errorCode;
    }

}
