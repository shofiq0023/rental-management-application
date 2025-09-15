package com.api.rms.controllers;

import com.api.rms.dtos.BuildingReqDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.interfaces.BuildingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BuildingsController {
    private final BuildingsService buildingsService;

    // Get All
    @GetMapping("/buildings")
    public ResponseEntity<ResponseDto> getAllBuildings() {
        return buildingsService.getAllBuildings();
    }

    // Get One
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<ResponseDto> getBuilding(@PathVariable("buildingId") Long id) {
        return buildingsService.getBuilding(id);
    }

    // Save
    @PostMapping("/building")
    public ResponseEntity<ResponseDto> createBuilding(@RequestBody BuildingReqDto reqDto) {
        return buildingsService.createBuilding(reqDto);
    }

    // Update
    @PutMapping("/building/{buildingId}")
    public ResponseEntity<ResponseDto> updateBuilding(@PathVariable("buildingId") Long id, @RequestBody BuildingReqDto reqDto) {
        return buildingsService.updateBuilding(id, reqDto);
    }

    // Delete
    @DeleteMapping("/building/{buildingId}")
    public ResponseEntity<ResponseDto> deleteBuilding(@PathVariable("buildingId") Long id) {
        return buildingsService.deleteBuilding(id);
    }
}
