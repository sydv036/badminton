package com.example.tob.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class ErrorHandlerCustomesize implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    /**
     * Handle the error in the given response.
     * <p>This method is only called when {@link #hasError(ClientHttpResponse)}
     * has returned {@code true}.
     *
     * @param response the response with the error
     * @throws IOException in case of I/O errors
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }

    /**
     * Alternative to {@link #handleError(ClientHttpResponse)} with extra
     * information providing access to the request URL and HTTP method.
     *
     * @param url      the request URL
     * @param method   the HTTP method
     * @param response the response with the error
     * @throws IOException in case of I/O errors
     * @since 5.0
     */
    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        ResponseErrorHandler.super.handleError(url, method, response);
    }


}
