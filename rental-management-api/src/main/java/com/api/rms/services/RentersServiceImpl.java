package com.api.rms.services;

import com.api.rms.interfaces.RentersService;
import com.api.rms.repository.RentersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentersServiceImpl implements RentersService {
    private final RentersRepo rentersRepo;
}
