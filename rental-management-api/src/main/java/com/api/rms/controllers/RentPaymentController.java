package com.api.rms.controllers;

import com.api.rms.dtos.RentPaymentReqDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.interfaces.RentPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RentPaymentController {
    private final RentPaymentService rentPaymentService;

    // Get All
    @GetMapping("/payments")
    public ResponseEntity<ResponseDto> getAllPayment() {
        return rentPaymentService.getAllPayment();
    }

    // Get One
    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<ResponseDto> getPayment(@PathVariable("paymentId") Long id) {
        return rentPaymentService.getPayment(id);
    }

    // Save
    @PostMapping("/payment")
    public ResponseEntity<ResponseDto> createPayment(@RequestBody RentPaymentReqDto reqDto) {
        return rentPaymentService.createPayment(reqDto);
    }

    // Update
    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<ResponseDto> updatePayment(@PathVariable("paymentId") Long id, @RequestBody RentPaymentReqDto reqDto) {
        return rentPaymentService.updatePayment(id, reqDto);
    }

    // Delete
    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<ResponseDto> deletePayment(@PathVariable("paymentId") Long id) {
        return rentPaymentService.deletePayment(id);
    }
}
