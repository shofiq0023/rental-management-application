package com.api.rms.utilities;

import com.api.rms.dtos.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ResponseWriter {
    private final GenericResponseUtil resUtil;

    public void writeResponse(HttpServletResponse response, int status, String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<ResponseDto> responseEntity = resUtil.createErrorResponse(message);
        String jsonResponse = objectMapper.writeValueAsString(responseEntity.getBody());

        response.setContentType("application/json");
        response.setStatus(status);
        response.getWriter().write(jsonResponse);
    }
}
