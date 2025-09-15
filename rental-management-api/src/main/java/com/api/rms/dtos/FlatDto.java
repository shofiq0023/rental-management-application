package com.api.rms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlatDto {
    private Long buildingFlatId;
    private String flatNo;
    private boolean isRented;
    private Long renterId;
}
