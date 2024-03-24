package com.pitang.cars.error;

import com.pitang.cars.model.CarEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ErrorResponse {
    private String message;
    private int errorCode;

}
