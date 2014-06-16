package org.mytraffic.priv.exceptions;

import org.mytraffic.priv.api.exceptions.PrivateApiErrorCode;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.springframework.http.HttpStatus;

/**
 * Thrown when a Private API Service returns an error through a RESTful request.
 *
 * @author avasquez
 * @author mariobarque
 */
public class PrivateApiRestException extends PrivateApiException {

    protected HttpStatus status;

    public PrivateApiRestException(HttpStatus status, PrivateApiErrorCode errorCode, String message) {
        super(errorCode, message);

        this.status = status;
    }

    @Override
    public String getMessage() {
        return "status = " + status + ", errorCode = " + errorCode + ", message = " + super.getMessage();
    }

}
