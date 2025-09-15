package com.api.rms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RenterReqDto {
    private String id;
    private Long userId;
    private Long buildingFlatId;
    private String nidNo;
    private String deal;
}
