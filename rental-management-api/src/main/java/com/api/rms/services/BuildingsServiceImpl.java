package com.api.rms.services;

import com.api.rms.dtos.BuildingReqDto;
import com.api.rms.dtos.BuildingsDto;
import com.api.rms.dtos.FlatDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.entities.BuildingFlatEntity;
import com.api.rms.entities.BuildingsEntity;
import com.api.rms.interfaces.BuildingsService;
import com.api.rms.repository.BuildingFlatRepo;
import com.api.rms.repository.BuildingsRepo;
import com.api.rms.utilities.GenericResponseUtil;
import com.api.rms.utilities.Utility;
import jakarta.transaction.Transactional;
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
    private final BuildingFlatRepo buildingFlatRepo;

    @Override
    public ResponseEntity<ResponseDto> getAllBuildings() {
        try {
            List<BuildingsEntity> entities = new ArrayList<>(buildingsRepo.findAll());

            List<BuildingsDto> dtos = entities.stream().map(b -> Utility.copyProperties(b, BuildingsDto.class)).toList();

            if (dtos.isEmpty())
                return resUtil.createSuccessResponse("No Data found!");

            setFlatsForBuilding(dtos);

            return resUtil.createSuccessResponse(dtos, "Buildings found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    private void setFlatsForBuilding(List<BuildingsDto> dtos) {
        for (BuildingsDto buildingsDto : dtos) {
            getFlatsData(buildingsDto);
        }
    }

    private void getFlatsData(BuildingsDto buildingsDto) {
        Long buildingId = buildingsDto.getId();

        List<BuildingFlatEntity> buildingFlatEntities = buildingFlatRepo.findByBuildingIdOrderByFlatNo(buildingId);

        if (!buildingFlatEntities.isEmpty()) {
            List<FlatDto> flats = new ArrayList<>();

            for (BuildingFlatEntity entity : buildingFlatEntities) {
                FlatDto flatDto = new FlatDto();
                flatDto.setBuildingFlatId(entity.getId());
                flatDto.setFlatNo(entity.getFlatNo());
                flatDto.setRented(entity.isRented());

                flats.add(flatDto);
            }

            buildingsDto.setFlats(flats);
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getBuilding(Long id) {
        try {
            Optional<BuildingsEntity> buildingOpt = buildingsRepo.findById(id);

            if (buildingOpt.isEmpty()) return resUtil.createSuccessResponse("No building found with the given id!");

            BuildingsDto dto = Utility.copyProperties(buildingOpt.get(), BuildingsDto.class);

            getFlatsData(dto);

            return resUtil.createSuccessResponse(dto, "Building found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> createBuilding(BuildingReqDto reqDto) {
        try {
            BuildingsEntity entity = new BuildingsEntity();
            entity.setName(reqDto.getName());
            entity.setAddress(reqDto.getAddress());
            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            BuildingsEntity savedEntity = buildingsRepo.save(entity);

            if (!reqDto.getFlats().isEmpty())
                saveFlats(reqDto.getFlats(), savedEntity);

            return resUtil.createSuccessResponse(savedEntity, "Created a new Building");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    private void saveFlats(List<FlatDto> flatDtos, BuildingsEntity savedEntity) {
        List<BuildingFlatEntity> entitiesToBeSaved = new ArrayList<>();

        for (FlatDto dto : flatDtos) {
            BuildingFlatEntity entity = new BuildingFlatEntity();
            entity.setBuilding(savedEntity);
            entity.setRented(dto.isRented());
            entity.setFlatNo(dto.getFlatNo());
            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            entitiesToBeSaved.add(entity);
        }

        buildingFlatRepo.saveAll(entitiesToBeSaved);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> updateBuilding(Long id, BuildingReqDto reqDto) {
        try {
            Optional<BuildingsEntity> entityOpt = buildingsRepo.findById(id);

            if (entityOpt.isEmpty())
                return resUtil.createErrorResponse("No building found with the given entity");

            BuildingsEntity existingEntity = entityOpt.get();
            existingEntity.setName(reqDto.getName());
            existingEntity.setAddress(reqDto.getAddress());
            existingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            BuildingsEntity updateEntity = buildingsRepo.save(existingEntity);

            if (!reqDto.getFlats().isEmpty())
                updateFlats(updateEntity, reqDto.getFlats());

            return resUtil.createSuccessResponse(updateEntity, "Building information updated");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    private void updateFlats(BuildingsEntity updateEntity, List<FlatDto> flats) {
        Long buildingId = updateEntity.getId();
        List<BuildingFlatEntity> existingBuildingFlatEntities = buildingFlatRepo.findByBuildingId(buildingId);

        for (FlatDto flatDto : flats) {
            BuildingFlatEntity entity = new BuildingFlatEntity();
            entity.setBuilding(updateEntity);
            entity.setFlatNo(flatDto.getFlatNo());
            entity.setRented(flatDto.isRented());
            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            existingBuildingFlatEntities.add(entity);
        }

        buildingFlatRepo.saveAll(existingBuildingFlatEntities);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBuilding(Long id) {
        try {
            Optional<BuildingsEntity> entityOpt = buildingsRepo.findById(id);

            if (entityOpt.isEmpty()) return resUtil.createErrorResponse("No building found with the given id");

            buildingFlatRepo.deleteByBuildingId(id);

            buildingsRepo.deleteById(id);

            return resUtil.createSuccessResponse("Building deleted successfully");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }
}
