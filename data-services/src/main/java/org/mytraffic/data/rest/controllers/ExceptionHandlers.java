package org.mytraffic.data.rest.controllers;

import org.mytraffic.api.data.exceptions.DataApiErrorCode;
import org.mytraffic.api.data.exceptions.DataApiException;
import org.mytraffic.api.exception.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Advice for the REST controllers that includes response handling for all major exceptions.
 *
 * @author avasquez
 */
@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(DataApiException.class)
    public ResponseEntity<Object> handleDataServiceException(DataApiException e, WebRequest request) {
        return handleExceptionInternal(e, e.getErrorCode().getDefaultHttpStatus(), e.getErrorCode(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, DataApiErrorCode.OTHER, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status,
                                                             DataApiErrorCode errorCode, WebRequest request) {
        return handleExceptionInternal(ex, new HttpHeaders(), status, errorCode, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status,
                                                             DataApiErrorCode errorCode, WebRequest request) {
        logger.error("Request for " + ((ServletWebRequest) request).getRequest().getRequestURI() + " failed " +
                "with HTTP status " + status, ex);

        ErrorDetails<DataApiErrorCode> errorDetails = new ErrorDetails<>(errorCode, ex.getLocalizedMessage());

        return new ResponseEntity<>(errorDetails, headers, status);
    }

}
