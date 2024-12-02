package com.api.rms.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDto {
    private List<Object> data = new ArrayList<>();
    private String message;
    private String responseCode;

    public ResponseDto(Object data, String message, String responseCode) {
        this.data.add(data);
        this.message = message;
        this.responseCode = responseCode;
    }

    public ResponseDto(String message, String responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }
}
