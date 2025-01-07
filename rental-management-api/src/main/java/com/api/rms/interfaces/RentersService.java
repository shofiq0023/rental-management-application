package com.api.rms.interfaces;

import com.api.rms.dtos.RenterReqDto;
import com.api.rms.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface RentersService {
    ResponseEntity<ResponseDto> getAllRenters();

    ResponseEntity<ResponseDto> getRenter(Long id);

    ResponseEntity<ResponseDto> createRenter(RenterReqDto reqDto);

    ResponseEntity<ResponseDto> updateRenter(Long id, RenterReqDto reqDto);

    ResponseEntity<ResponseDto> deleteRenter(Long id);
}
