package com.api.rms.utilities;

import com.api.rms.dtos.ResponseDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class GenericResponseUtil {
    public ResponseEntity<ResponseDto> createErrorResponse() {
        return createErrorResponse("ERROR!");
    }

    public ResponseEntity<ResponseDto> createErrorResponse(String message) {
        return createErrorResponse(message, "500");
    }

    public ResponseEntity<ResponseDto> createErrorResponse(String message, String resCode) {
        return new ResponseEntity<>(new ResponseDto(message, resCode), HttpStatus.valueOf(Integer.parseInt(resCode)));
    }

    public ResponseEntity<ResponseDto> createSuccessResponse(Object data) {
        return createSuccessResponse(data, "SUCCESS!");
    }

    public ResponseEntity<ResponseDto> createSuccessResponse(Object data, String message) {
        return ResponseEntity.ok(new ResponseDto(data, message, "200"));
    }

    public ResponseEntity<ResponseDto> createDuplicateKeyResponse(DataIntegrityViolationException e) {
        Throwable rootCause = e.getCause();

        if (rootCause instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) rootCause;
            if (ex.getSQLState().equals("23505")) {
                // Getting the index of the error message where the Key and values are shown
                // e.g Key (email)=(value) in error message
                int trimmedMessage = ex.getErrorMessage().indexOf("(");
                String errorMsg = ex.getErrorMessage().substring(trimmedMessage, ex.getErrorMessage().length() - 2);


                return createErrorResponse(errorMsg, "400");
            }
        }

        return createErrorResponse("Data integrity violation!");
    }
}
