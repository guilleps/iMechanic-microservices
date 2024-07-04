package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.model.Model;
import com.imechanic.backend.principal_service.repository.ModelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;

    @Transactional(readOnly = true)
    public Model findById(Long modelId) {
        return modelRepository.findById(modelId)
                .orElseThrow(() -> new EntityNotFoundException("Model with ID: " + modelId + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Model> getAllModelsByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }
}
