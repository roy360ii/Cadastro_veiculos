package com.robisonrocha.ficticiusCleam.services;

import com.robisonrocha.ficticiusCleam.dto.VehicleResponse;
import com.robisonrocha.ficticiusCleam.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {

    VehicleResponse insert(Vehicle
                                   vehicle);
    List<VehicleResponse> compute(BigDecimal price, BigDecimal city, BigDecimal highway);
}

