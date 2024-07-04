package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.controller.request.VehicleDTORequest;
import com.imechanic.backend.principal_service.controller.response.VehicleDTOResponse;
import com.imechanic.backend.principal_service.controller.response.VehicleDetailsDTOResponse;
import com.imechanic.backend.principal_service.model.Brand;
import com.imechanic.backend.principal_service.model.Model;
import com.imechanic.backend.principal_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@Slf4j
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<VehicleDTOResponse>> getAllVehicles() {
        List<VehicleDTOResponse> vehicles = vehicleService.getAllMyVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/marcas")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(vehicleService.getAllBrands());
    }

    @GetMapping("/modelos/{brandId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Model>> getAllModels(@PathVariable Long brandId) {
        return ResponseEntity.ok(vehicleService.getAllModelsByBrand(brandId));
    }

    @PostMapping("/crear")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Map<String, String>> createVehicle(@RequestBody VehicleDTORequest vehicleDTORequest) {
        Map<String, String> response = new HashMap<>();
        try {
             response = vehicleService.createVehicle(vehicleDTORequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error creating vehicle: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/placa/{plate}")
    @PreAuthorize("hasAuthority('WORKSHOP')")
    public ResponseEntity<VehicleDetailsDTOResponse> getVehicleDetailsByPlate(@PathVariable("plate") String plate) {
        return ResponseEntity.ok(vehicleService.findVehicleDetailsByPlate(plate));
    }
}