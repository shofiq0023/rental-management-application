package com.api.rms.services;

import com.api.rms.interfaces.EncoderDecoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncoderDecoderImpl implements EncoderDecoder {
    @Override
    public String encodeString(String actualString, String salt) {
        String encodableString = salt + actualString;
        return Base64.getEncoder().encodeToString(encodableString.getBytes());
    }

    @Override
    public String decodeString(String encodedString, String salt) {
        String decodeFormat = "";
        if (encodedString.length() > salt.length()) {
            byte[] decodedByte = Base64.getDecoder().decode(encodedString);
            String detailString = new String(decodedByte);
            if (detailString.contains(salt)) {
                decodeFormat = detailString.substring(salt.length());
            }
        }
        return decodeFormat;
    }
}
