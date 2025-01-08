package com.api.rms.services;

import com.api.rms.dtos.*;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            List<RentersQueryResDto> entities = new ArrayList<>(rentersRepo.findAllRenters());

            return resUtil.createSuccessResponse(getRentersDtoList(entities), "Renters found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    private List<RentersDto> getRentersDtoList(List<RentersQueryResDto> entities) {
        Map<Long, List<RentersQueryResDto>> groupedData = entities.stream().collect(Collectors.groupingBy(RentersQueryResDto::getUserId));

        return groupedData.values().stream().map(rentersQueryResDtos -> {
            RentersQueryResDto resDto = rentersQueryResDtos.get(0);
            RentersDto dto = new RentersDto();
            dto.setUserId(resDto.getUserId());
            dto.setNidNo(resDto.getNidNo());
            dto.setDeal(resDto.getDeal());
            dto.setRenterName(resDto.getRenterName());
            dto.setRenterUsername(resDto.getRenterUsername());
            dto.setRenterEmail(resDto.getRenterEmail());
            dto.setRenterPhone(resDto.getRenterPhone());
            dto.setRenterAddress(resDto.getRenterAddress());
            dto.setRenterDateOfBirth(resDto.getRenterDateOfBirth());
            dto.setBuildingFlatId(resDto.getBuildingFlatId());
            dto.setRenterIds(rentersQueryResDtos.stream().map(RentersQueryResDto::getRenterId).collect(Collectors.toList()));

            Map<String, List<RentersQueryResDto>> groupedForBuilding = rentersQueryResDtos.stream().collect(Collectors.groupingBy(RentersQueryResDto::getBuildingName));

            dto.setRentedBuildings(groupedForBuilding.values().stream().map(queryResDtos -> {
                RentersQueryResDto buildingData = queryResDtos.get(0);

                List<FlatDto> flatDtos = getFlats(queryResDtos);
                RentedFlats rentedFlats = new RentedFlats();
                rentedFlats.setFlats(flatDtos);

                RentedBuildingDto rentedBuildingDto = new RentedBuildingDto();
                rentedBuildingDto.setBuildingName(buildingData.getBuildingName());
                rentedBuildingDto.setAddress(buildingData.getBuildingAddress());
                rentedBuildingDto.setRentedFlats(rentedFlats);

                return rentedBuildingDto;
            }).collect(Collectors.toList()));

            return dto;
        }).collect(Collectors.toList());
    }

    public List<FlatDto> getFlats(List<RentersQueryResDto> entities) {
        List<FlatDto> flatDtoList = new ArrayList<>();

        for (RentersQueryResDto entity : entities) {
            FlatDto dto = new FlatDto();
            dto.setFlatNo(entity.getFlatNo());
            dto.setBuildingFlatId(entity.getBuildingFlatId());
            dto.setRented(true);
            dto.setRenterId(entity.getRenterId());

            flatDtoList.add(dto);
        }

        return flatDtoList;
    }

    @Override
    public ResponseEntity<ResponseDto> getRenter(Long id) {
        try {
            List<RentersQueryResDto> entities = new ArrayList<>(rentersRepo.findRenter(id));

            return resUtil.createSuccessResponse(getRentersDtoList(entities), "Renters found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
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
        try {
            return resUtil.createErrorResponse("Under construction!");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteRenter(Long renterId, Long userId) {
        try {
            if (renterId == null && userId == null)
                return resUtil.createErrorResponse("At least one parameter is required");

            rentersRepo.deleteByIdOrUserId(renterId, userId);

            return resUtil.createSuccessResponse("Renter deleted successfully!");
        } catch (Exception e) {
            return resUtil.createErrorResponse("There were some error while deleting the renter");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getRentersSimpleVer() {
        try {
            List<RentersQueryResDto> queryRes = rentersRepo.findAllRenters();

            return resUtil.createSuccessResponse(queryRes, "Simpler version of renters fetched!");
        } catch (Exception e) {
            return resUtil.createErrorResponse("There were some error while deleting the renter");
        }
    }
}
