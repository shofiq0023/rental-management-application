package com.api.rms.interfaces;

import com.api.rms.dtos.BuildingReqDto;
import com.api.rms.dtos.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface BuildingsService {
    ResponseEntity<ResponseDto> getAllBuildings();

    ResponseEntity<ResponseDto> getBuilding(Long id);

    ResponseEntity<ResponseDto> createBuilding(BuildingReqDto reqDto);

    ResponseEntity<ResponseDto> updateBuilding(Long id, BuildingReqDto reqDto);

    ResponseEntity<ResponseDto> deleteBuilding(Long id);
}
