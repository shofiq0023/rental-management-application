package com.api.rms.interfaces;

public interface EncoderDecoder {
    String encodeString(String actualString, String salt);

    String decodeString(String encodedString, String salt);
}
