package com.api.rms.services;

import com.api.rms.dtos.RenterReqDto;
import com.api.rms.dtos.RentersDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.entities.BuildingFlatEntity;
import com.api.rms.entities.RentersBuildingEntity;
import com.api.rms.entities.RentersEntity;
import com.api.rms.entities.UserEntity;
import com.api.rms.interfaces.RentersService;
import com.api.rms.repository.BuildingFlatRepo;
import com.api.rms.repository.RentersBuildingRepo;
import com.api.rms.repository.RentersRepo;
import com.api.rms.repository.UserRepo;
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
public class RentersServiceImpl implements RentersService {
    private final GenericResponseUtil resUtil;
    private final RentersRepo rentersRepo;
    private final UserRepo userRepo;
    private final BuildingFlatRepo buildingFlatRepo;
    private final RentersBuildingRepo rentersBuildingRepo;

    @Override
    public ResponseEntity<ResponseDto> getAllRenters() {
        try {
            List<RentersEntity> entities = new ArrayList<>(rentersRepo.findAll());

            List<RentersDto> dtos = entities.stream().map(b -> Utility.copyProperties(b, RentersDto.class)).toList();

//            if (dtos.isEmpty())
//                return resUtil.createSuccessResponse("No Data found!");
//
//            setFlatsForBuilding(dtos);
//
            return resUtil.createSuccessResponse(dtos, "Renters found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getRenter(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> createRenter(RenterReqDto reqDto) {
        try {
            Long userId = reqDto.getUserId();
            Long buildingFlatId = reqDto.getBuildingFlatId();

            Optional<UserEntity> userEntityOpt = userRepo.findById(userId);
            if (userEntityOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with the given id!");

            Optional<BuildingFlatEntity> buildingFlatEntityOpt = buildingFlatRepo.findById(buildingFlatId);
            if (buildingFlatEntityOpt.isEmpty())
                return resUtil.createErrorResponse("Invalid building and flat id!");

            boolean renterBuildingCreated = createRenterBuilding(userEntityOpt.get(), buildingFlatEntityOpt.get());

            if (!renterBuildingCreated)
                return resUtil.createErrorResponse("The provided flat might be already rented!");

            RentersEntity rentersEntity = new RentersEntity();
            rentersEntity.setUser(userEntityOpt.get());
            rentersEntity.setBuildingFlat(buildingFlatEntityOpt.get());
            rentersEntity.setNidNo(reqDto.getNidNo());
            rentersEntity.setDeal(reqDto.getDeal());
            rentersEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            rentersRepo.save(rentersEntity);

            return resUtil.createSuccessResponse("Renter created successfully!");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    private boolean createRenterBuilding(UserEntity userEntity, BuildingFlatEntity buildingFlatEntity) {
        try {
            Boolean isRented = buildingFlatRepo.findIsRentedStatus(buildingFlatEntity.getId());

            if (isRented)
                return false;

            RentersBuildingEntity rentersBuildingEntity = new RentersBuildingEntity();
            rentersBuildingEntity.setUser(userEntity);
            rentersBuildingEntity.setBuildingFlat(buildingFlatEntity);
            rentersBuildingEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            rentersBuildingRepo.save(rentersBuildingEntity);

            changeFlatStatus(buildingFlatEntity.getId());

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private void changeFlatStatus(Long id) {
        buildingFlatRepo.changeFlatStatus(id);
    }

    @Override
    public ResponseEntity<ResponseDto> updateRenter(Long id, RenterReqDto reqDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteRenter(Long id) {
        return null;
    }
}
