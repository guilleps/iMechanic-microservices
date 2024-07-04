package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.controller.request.VehicleDTORequest;
import com.imechanic.backend.principal_service.controller.response.VehicleDTOResponse;
import com.imechanic.backend.principal_service.controller.response.VehicleDetailsDTOResponse;
import com.imechanic.backend.principal_service.model.Brand;
import com.imechanic.backend.principal_service.model.Model;
import com.imechanic.backend.principal_service.model.UserEntity;
import com.imechanic.backend.principal_service.model.Vehicle;
import com.imechanic.backend.principal_service.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final BrandService brandService;
    private final ModelService modelService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @Transactional(readOnly = true)
    public List<Model> getAllModelsByBrand(Long brandId) {
        return modelService.getAllModelsByBrand(brandId);
    }

    @Transactional
    public Map<String, String> createVehicle(VehicleDTORequest vehicleDTORequest) {
        Map<String, String> response = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            Brand brand = brandService.findById(vehicleDTORequest.getBrandId());
            Model model = modelService.findById(vehicleDTORequest.getModelId());

            var vehicle = Vehicle.builder()
                    .plate(vehicleDTORequest.getPlate())
                    .brand(brand)
                    .model(model)
                    .category(vehicleDTORequest.getCategory())
                    .userEntity(userService.findUserByEmail(email))
                    .build();

            vehicleRepository.save(vehicle);

            String message = "The vehicle with plate " + vehicle.getPlate() + " has been created successfully";
            log.info(message);

            response.put("message", message);
        } catch (Exception e) {
            log.error("Unexpected error creating vehicle: {}", e.getMessage());
            response.put("message", "Unexpected error creating vehicle: " + e.getMessage());
        }
        return response;
    }

    public List<VehicleDTOResponse> getAllMyVehicles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity user = userService.findUserByEmail(email);

        List<Vehicle> vehicles = user.getVehicles();

        return vehicles.stream()
                .map(vehicle -> new VehicleDTOResponse(vehicle.getPlate(), vehicle.getBrand().getName(), vehicle.getModel().getName(), vehicle.getCategory().name()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VehicleDetailsDTOResponse findVehicleDetailsByPlate(String plate) {
        Vehicle vehicle = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with plate: '" + plate + "' not found"));

        return VehicleDetailsDTOResponse.builder()
                .nameCustomer(vehicle.getUserEntity().getName())
                .emailCustomer(vehicle.getUserEntity().getEmail())
                .addressCustomer(vehicle.getUserEntity().getAddress())
                .phoneCustomer(vehicle.getUserEntity().getPhone())
                .plate(vehicle.getPlate())
                .brand(vehicle.getBrand().getName())
                .model(vehicle.getModel().getName())
                .category(vehicle.getCategory().name())
                .build();
    }
}
