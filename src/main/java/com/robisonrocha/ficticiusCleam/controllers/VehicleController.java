package com.robisonrocha.ficticiusCleam.controllers;

import com.robisonrocha.ficticiusCleam.dto.VehicleRequest;
import com.robisonrocha.ficticiusCleam.dto.VehicleResponse;
import com.robisonrocha.ficticiusCleam.mappers.VehicleMapper;
import com.robisonrocha.ficticiusCleam.services.VehicleService;
import org.springframework.http

        .HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

public class VehicleController {

    private final VehicleService vehicleService;

    private final VehicleMapper vehicleMapper;

    public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> insert(@RequestBody VehicleRequest vehicle) {
        VehicleResponse savedVehicle = vehicleService.insert(vehicleMapper.toEntity(vehicle));

        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping(value = "/compute")
    public ResponseEntity<List<VehicleResponse>> compute(@RequestParam("price") float price, @RequestParam("city") float city, @RequestParam("highway") float highway) {
        List<VehicleResponse> dto = vehicleService.compute(price, city, highway);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

