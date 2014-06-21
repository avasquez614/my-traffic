package org.mytraffic.priv.services.impl;

import org.craftercms.commons.rest.AbstractRestClientBase;
import org.craftercms.commons.rest.RestServiceException;
import org.mytraffic.priv.api.exceptions.PrivateApiErrorCode;
import org.mytraffic.priv.api.exceptions.PrivateApiErrorDetails;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.exceptions.PrivateApiRestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;

/**
 * Base for Private API Services REST clients.
 *
 * @author avasquez
 * @author mariobarque
 */
public abstract class AbstractPrivateServicesRestClientBase extends AbstractRestClientBase {

    protected <T> T doPostForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws PrivateApiException {
        try {
            return restTemplate.postForObject(url, request, responseType, uriVariables);
        } catch (RestServiceException e) {
            handleRestServiceException(e);
        } catch (Exception e) {
            handleException(e);
        }

        return null;
    }

    protected URI doPostForLocation(String url, Object request, Object... uriVariables) throws PrivateApiException {
        try {
            return restTemplate.postForLocation(url, request, uriVariables);
        } catch (RestServiceException e) {
            handleRestServiceException(e);
        } catch (Exception e) {
            handleException(e);
        }

        return null;
    }

    protected <T> T doGetForObject(String url, Class<T> responseType, Object... uriVariables)
            throws PrivateApiException {
        try {
            return restTemplate.getForObject(url, responseType, uriVariables);
        } catch (RestServiceException e) {
            handleRestServiceException(e);
        } catch (Exception e) {
            handleException(e);
        }

        return null;
    }

    protected <T> T doGetForObject(String url, ParameterizedTypeReference<T> responseType, Object... uriVariables)
            throws PrivateApiException {
        try {
            return restTemplate.exchange(url, HttpMethod.GET, null, responseType, uriVariables).getBody();
        } catch (RestServiceException e) {
            handleRestServiceException(e);
        } catch (Exception e) {
            handleException(e);
        }

        return null;
    }

    protected <T> T doGetForObject(URI url, Class<T> responseType) throws PrivateApiException {
        try {
            return restTemplate.getForObject(url, responseType);
        } catch (RestServiceException e) {
            handleRestServiceException(e);
        } catch (Exception e) {
            handleException(e);
        }

        return null;
    }

    protected void doDelete(String url, Object... uriVariables) throws PrivateApiException {
        try {
            restTemplate.delete(url, uriVariables);
        } catch (RestServiceException e) {
            handleRestServiceException(e);
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleRestServiceException(RestServiceException e) throws PrivateApiException {
        if (e.getErrorDetails() instanceof PrivateApiErrorDetails) {
            PrivateApiErrorDetails errorDetails = (PrivateApiErrorDetails) e.getErrorDetails();
            HttpStatus status = e.getResponseStatus();
            PrivateApiErrorCode errorCode = errorDetails.getErrorCode();
            String message = errorDetails.getMessage();

            throw new PrivateApiRestException(status, errorCode, message);
        } else {
            HttpStatus status = e.getResponseStatus();
            String message = e.getErrorDetails().toString();

            throw new PrivateApiRestException(status, PrivateApiErrorCode.OTHER, message);
        }
    }

    protected void handleException(Exception e) throws PrivateApiException {
        throw new PrivateApiException(PrivateApiErrorCode.OTHER, e.getMessage(), e);
    }

}
