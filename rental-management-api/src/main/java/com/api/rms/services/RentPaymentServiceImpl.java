package com.api.rms.services;

import com.api.rms.dtos.RentPaymentReqDto;
import com.api.rms.dtos.ResponseDto;
import com.api.rms.entities.RentPaymentEntity;
import com.api.rms.entities.RentersEntity;
import com.api.rms.entities.UserEntity;
import com.api.rms.interfaces.RentPaymentService;
import com.api.rms.repository.RentPaymentRepo;
import com.api.rms.repository.RentersRepo;
import com.api.rms.repository.UserRepo;
import com.api.rms.utilities.GenericResponseUtil;
import com.api.rms.utilities.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentPaymentServiceImpl implements RentPaymentService {
    private final RentPaymentRepo rentPaymentRepo;
    private final GenericResponseUtil resUtil;
    private final UserRepo userRepo;
    private final RentersRepo rentersRepo;

    @Override
    public ResponseEntity<ResponseDto> getAllPayment() {
        try {
            List<RentPaymentEntity> entityList = rentPaymentRepo.findAll();

            return resUtil.createSuccessResponse(entityList, "Rent payments");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> getPayment(Long id) {
        try {
            Optional<RentPaymentEntity> entityOpt = rentPaymentRepo.findById(id);
            if (entityOpt.isEmpty())
                return resUtil.createErrorResponse("No rent payment information found with the given id");

            return resUtil.createSuccessResponse(entityOpt.get(), "Rent payment information found");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> createPayment(RentPaymentReqDto reqDto) {
        try {
            Optional<UserEntity> userEntityOpt = userRepo.findById(reqDto.getUserId());
            if (userEntityOpt.isEmpty())
                return resUtil.createErrorResponse("No user found with the given id");

            Optional<RentersEntity> rentersEntityOpt = rentersRepo.findById(reqDto.getRenterId());
            if (rentersEntityOpt.isEmpty())
                return resUtil.createErrorResponse("No renter found with the given id");

            RentPaymentEntity entity = new RentPaymentEntity();
            entity.setAmount(reqDto.getAmount());
            entity.setPaymentDate(new Date(System.currentTimeMillis()));
            entity.setUtilityBill(reqDto.getUtilityBill());
            entity.setOthersBill(reqDto.getOthersBill());
            entity.setMonthName(Utility.getCurrentMonthName());
            entity.setYearStr(Utility.getCurrentYear());
            entity.setRenter(rentersEntityOpt.get());
            entity.setUser(userEntityOpt.get());

            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            RentPaymentEntity savedEntity = rentPaymentRepo.save(entity);

            return resUtil.createSuccessResponse(savedEntity, "Rent Payment created successfully");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updatePayment(Long id, RentPaymentReqDto reqDto) {
        try {
            Optional<RentPaymentEntity> existingPaymentOpt = rentPaymentRepo.findById(id);
            if (existingPaymentOpt.isEmpty())
                return resUtil.createErrorResponse("No rent payment information found with the given id");

            RentPaymentEntity entity = existingPaymentOpt.get();
            entity.setAmount(reqDto.getAmount());
            entity.setUtilityBill(reqDto.getUtilityBill());
            entity.setOthersBill(reqDto.getOthersBill());

            entity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            RentPaymentEntity savedEntity = rentPaymentRepo.save(entity);

            return resUtil.createSuccessResponse(savedEntity, "Successfully updated renter payment information");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deletePayment(Long id) {
        try {
            Optional<RentPaymentEntity> entityOpt = rentPaymentRepo.findById(id);
            if (entityOpt.isEmpty())
                return resUtil.createErrorResponse("No rent payment information found with the given id");

            rentPaymentRepo.deleteById(id);

            return resUtil.createSuccessResponse("Rent payment information deleted!");
        } catch (Exception e) {
            return resUtil.createErrorResponse();
        }
    }
}
