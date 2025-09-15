package com.api.rms.dtos;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseDto {
    private Object data;
    private String message;
    private String responseCode;

    public ResponseDto(Object data, String message, String responseCode) {
        this.data = data;
        this.message = message;
        this.responseCode = responseCode;
    }

    public ResponseDto(String message, String responseCode) {
        this.data = new ArrayList<>();
        this.message = message;
        this.responseCode = responseCode;
    }
}
