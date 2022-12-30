package com.robisonrocha.ficticiusCleam.services;

import java.math.BigDecimal;

public interface ConsumptionService {
    BigDecimal computeTotalPrice(BigDecimal fuelPrice, BigDecimal consumedFuel);
    BigDecimal computeUsage(BigDecimal distance, BigDecimal averageConsumption);
}