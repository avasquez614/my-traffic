package org.mytraffic.api.exception;

/**
 * Root exception for all My Traffic services.
 *
 * @author avasquez
 * @author mariobarque
 */
public class ServiceException extends MyTrafficException {

    private ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);

        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, Throwable cause) {
        super(cause);

        this.errorCode = errorCode;
    }

}
