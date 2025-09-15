package com.api.rms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentedBuildingDto {
    private String buildingName;
    private String address;
    private RentedFlats rentedFlats;
}
