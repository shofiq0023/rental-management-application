package com.api.rms.services;

import com.api.rms.dtos.BuildingReqDto;
import com.api.rms.dtos.BuildingsDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.entities.BuildingsEntity;
import com.api.rms.interfaces.BuildingsService;
import com.api.rms.repository.BuildingsRepo;
import com.api.rms.utilities.GenericResponseUtil;
import com.api.rms.utilities.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuildingsServiceImpl implements BuildingsService {
    private final BuildingsRepo buildingsRepo;
    private final GenericResponseUtil resUtil;

    @Override
    public ResponseEntity<ResponseDto> getAllBuildings() {
        try {
            List<BuildingsEntity> entities = new ArrayList<>(buildingsRepo.findAll());

            List<BuildingsDto> dto = entities.stream().map(b -> Utility.copyProperties(b, BuildingsDto.class)).toList();

            if (dto.isEmpty()) return resUtil.createSuccessResponse("No Data found!");

            return resUtil.createSuccessResponse(dto, "Buildings found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getBuilding(Long id) {
        try {
            Optional<BuildingsEntity> buildingOpt = buildingsRepo.findById(id);

            if (buildingOpt.isEmpty()) return resUtil.createSuccessResponse("No building found with the given id!");

            BuildingsDto dto = Utility.copyProperties(buildingOpt.get(), BuildingsDto.class);

            return resUtil.createSuccessResponse(dto, "Building found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> createBuilding(BuildingReqDto reqDto) {
        try {
            BuildingsEntity entity = new BuildingsEntity();
            entity.setName(reqDto.getName());
            entity.setAddress(reqDto.getAddress());
            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            BuildingsEntity savedEntity = buildingsRepo.save(entity);

            return resUtil.createSuccessResponse(savedEntity, "Created a new Building");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateBuilding(Long id, BuildingReqDto reqDto) {
        try {
            Optional<BuildingsEntity> entityOpt = buildingsRepo.findById(id);

            if (entityOpt.isEmpty()) return resUtil.createErrorResponse("No building found with the given entity");

            BuildingsEntity existingEntity = entityOpt.get();
            existingEntity.setName(reqDto.getName());
            existingEntity.setAddress(reqDto.getAddress());
            existingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            BuildingsEntity updateEntity = buildingsRepo.save(existingEntity);

            return resUtil.createSuccessResponse(updateEntity, "Building information updated");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBuilding(Long id) {
        try {
            Optional<BuildingsEntity> entityOpt = buildingsRepo.findById(id);

            if (entityOpt.isEmpty()) return resUtil.createErrorResponse("No building found with the given id");

            buildingsRepo.deleteById(id);

            return resUtil.createSuccessResponse("Building deleted successfully");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }
}
