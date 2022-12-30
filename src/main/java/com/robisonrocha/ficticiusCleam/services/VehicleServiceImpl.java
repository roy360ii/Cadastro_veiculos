package com.robisonrocha.ficticiusCleam.services;

import com.robisonrocha.ficticiusCleam.dto.VehicleResponse;
import com.robisonrocha.ficticiusCleam.mappers.VehicleMapper;
import com.robisonrocha.ficticiusCleam.models.Vehicle;
import com.robisonrocha.ficticiusCleam.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final VehicleMapper vehicleMapper;

    private final ConsumptionService consumptionService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper,
                              ConsumptionService consumptionService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.consumptionService = consumptionService;
    }

    @Override
    public VehicleResponse insert(Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toDto(savedVehicle);
    }

    @Override
    public List<VehicleResponse> compute(BigDecimal price, BigDecimal city, BigDecimal highway) {
        List<Vehicle> allVehicles = vehicleRepository.findAll();

        List<VehicleResponse> computedVehicles = allVehicles.stream().map(vehicle -> {
            float consumedHighway = consumptionService.computeUsage(highway, vehicle.getHighwayConsumption());
            float consumedCity = consumptionService.computeUsage(city, vehicle.getCityConsumption());
            float totalPrice = consumptionService.computeTotalPrice(price, (consumedCity + consumedHighway));

            VehicleResponse vehicleResponse = vehicleMapper.toDto(vehicle);
            vehicleResponse.setTotal(totalPrice);
            vehicleResponse.setFuelConsumed(consumedCity + consumedHighway);

            return vehicleResponse;
        }).collect(Collectors.toList());

        Collections.sort(computedVehicles);

        return computedVehicles;
    }
}

