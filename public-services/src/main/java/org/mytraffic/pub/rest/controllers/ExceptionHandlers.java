package org.mytraffic.pub.rest.controllers;

import org.craftercms.profile.api.exceptions.ProfileException;
import org.craftercms.profile.exceptions.ProfileRestServiceException;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
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

import java.util.Collections;

/**
 * Advice for the REST controllers that includes response handling for all major exceptions.
 *
 * @author avasquez
 */
@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(PrivateApiException.class)
    public ResponseEntity<Object> handlePrivateApiException(PrivateApiException e, WebRequest request) {
        return handleExceptionInternal(e, e.getErrorCode().getDefaultHttpStatus(), request);
    }

    @ExceptionHandler(ProfileRestServiceException.class)
    public ResponseEntity<Object> handleProfileRestServiceException(ProfileRestServiceException e, WebRequest request) {
        return handleExceptionInternal(e, e.getStatus(), request);
    }

    @ExceptionHandler(ProfileException.class)
    public ResponseEntity<Object> handleProfileException(ProfileException e, WebRequest request) {
        return handleExceptionInternal(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new HttpHeaders(), status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        logger.error("Request for " + ((ServletWebRequest) request).getRequest().getRequestURI() + " failed " +
                "with HTTP status " + status, ex);

        String message = ex.getMessage();

        if (ex instanceof ProfileRestServiceException) {
            message = ((ProfileRestServiceException) ex).getDetailMessage();
        }

        return new ResponseEntity<>(Collections.singletonMap("message", message), headers, status);
    }

}
