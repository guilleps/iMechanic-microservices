package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.model.Brand;
import com.imechanic.backend.principal_service.repository.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public Brand findById(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand with ID: " + brandId + "not found"));
    }

    @Transactional(readOnly = true)
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
