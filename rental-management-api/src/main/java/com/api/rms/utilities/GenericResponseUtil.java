package com.api.rms.utilities;

import com.api.rms.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GenericResponseUtil {
    public ResponseEntity<ResponseDto> createErrorResponse() {
        return createErrorResponse("ERROR!");
    }

    public ResponseEntity<ResponseDto> createErrorResponse(String message) {
        return createErrorResponse(message, "400");
    }

    public ResponseEntity<ResponseDto> createErrorResponse(String message, String resCode) {
        return new ResponseEntity<>(new ResponseDto(message, resCode), HttpStatus.valueOf(resCode));
    }

    public ResponseEntity<ResponseDto> createSuccessResponse(Object data) {
        return createSuccessResponse(data, "SUCCESS!");
    }

    public ResponseEntity<ResponseDto> createSuccessResponse(Object data, String message) {
        return ResponseEntity.ok(new ResponseDto(data, message, "200"));
    }
}
