package org.mytraffic.priv.api.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Error codes for My Traffic private API.
 *
 * @author avasquez
 * @author mariobarque
 */
public enum PrivateApiErrorCode {
    /**
     * If no incident was found for an specified ID
     */
    NO_SUCH_INCIDENT(HttpStatus.BAD_REQUEST),
    /**
     * If no favorite route was found for an specified ID
     */
    NO_SUCH_FAVORITE_ROUTE(HttpStatus.BAD_REQUEST),
    /**
     * Code use for any other error.
     */
    OTHER(HttpStatus.INTERNAL_SERVER_ERROR);

    private HttpStatus defaultHttpStatus;

    private PrivateApiErrorCode(HttpStatus defaultHttpStatus) {
        this.defaultHttpStatus = defaultHttpStatus;
    }

    public HttpStatus getDefaultHttpStatus() {
        return defaultHttpStatus;
    }

}
