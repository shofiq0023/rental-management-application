package com.api.rms.interfaces;

import com.api.rms.dtos.RentPaymentReqDto;
import com.api.rms.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface RentPaymentService {
    ResponseEntity<ResponseDto> getAllPayment();

    ResponseEntity<ResponseDto> getPayment(Long id);

    ResponseEntity<ResponseDto> createPayment(RentPaymentReqDto reqDto);

    ResponseEntity<ResponseDto> updatePayment(Long id, RentPaymentReqDto reqDto);

    ResponseEntity<ResponseDto> deletePayment(Long id);
}
