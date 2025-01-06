package com.api.rms.controllers;

import com.api.rms.interfaces.RentersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RentersController {
    private final RentersService rentersService;
}
