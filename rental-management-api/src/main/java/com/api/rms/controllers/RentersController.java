package com.api.rms.controllers;

import com.api.rms.dtos.RenterReqDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.interfaces.RentersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RentersController {
    private final RentersService rentersService;

    // Get All
    @GetMapping("/renters")
    public ResponseEntity<ResponseDto> getAllRenters() {
        return rentersService.getAllRenters();
    }

    // Get One
    @GetMapping("/renter/{renterId}")
    public ResponseEntity<ResponseDto> getRenter(@PathVariable("renterId") Long id) {
        return rentersService.getRenter(id);
    }

    // Save
    @PostMapping("/renter")
    public ResponseEntity<ResponseDto> createRenter(@RequestBody RenterReqDto reqDto) {
        return rentersService.createRenter(reqDto);
    }

    // Update
    @PutMapping("/renter/{renterId}")
    public ResponseEntity<ResponseDto> updateRenter(@PathVariable("renterId") Long id, @RequestBody RenterReqDto reqDto) {
        return rentersService.updateRenter(id, reqDto);
    }

    // Delete
    @DeleteMapping("/renter")
    public ResponseEntity<ResponseDto> deleteRenter(
            @RequestParam(value = "renterId", required = false) Long renterId,
            @RequestParam(value = "userId", required = false) Long userId)
    {
        return rentersService.deleteRenter(renterId, userId);
    }

    // Get All
    @GetMapping("/renters/simple")
    public ResponseEntity<ResponseDto> getRentersSimpleVer() {
        return rentersService.getRentersSimpleVer();
    }
}
