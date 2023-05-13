package com.clinic.admin.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ahmed
 */
@Component
public class RestTemplateResponseErrorHandler  implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()){
            InputStream body = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponse errorResponse = objectMapper.readValue(body, ErrorResponse.class);
            throw new ResourceNotFoundException(errorResponse.getMessage());
        }

    }

}