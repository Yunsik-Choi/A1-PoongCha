package com.poongcha.car.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CarOptionGroupCreateRequest {
    private String carOptionGroupName;
    private long additionalPrice;
    private String summaryDescription;
    private Long[] carOptionIds;
}
